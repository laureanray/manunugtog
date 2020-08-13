/**
 * ExtendedDecoder
 *
 * @author Laurean Ray
 * @version 1.0
 * @since 8/12/20
 */

package tech.laureanray.player;

import org.jflac.*;
import org.jflac.frame.*;
import org.jflac.io.BitInputStream;
import org.jflac.io.RandomFileInputStream;
import org.jflac.metadata.*;
import org.jflac.util.ByteData;
import org.jflac.util.CRC16;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class ExtendedDecoder  {
    private static final int FRAME_FOOTER_CRC_LEN = 16;
    private static final byte[] ID3V2_TAG = new byte[]{73, 68, 51};
    private BitInputStream bitStream;
    private ChannelData[] channelData = new ChannelData[8];
    private int outputCapacity = 0;
    private int outputChannels = 0;
    private long samplesDecoded = 0L;
    private StreamInfo streamInfo = null;
    private SeekTable seekTable = null;
    private Frame frame = new Frame();
    private byte[] headerWarmup = new byte[2];
    private int channels;
    private InputStream inputStream = null;
    private int badFrames;
    private boolean eof = false;
    private CustomFrameListeners frameListeners = new CustomFrameListeners();
    private CustomPCMProcessors pcmProcessors = new CustomPCMProcessors();

    public ExtendedDecoder(InputStream inputStream) {
        this.inputStream = inputStream;
        this.bitStream = new BitInputStream(inputStream);
        this.samplesDecoded = 0L;
    }

    public StreamInfo getStreamInfo() {
        return this.streamInfo;
    }

    public ChannelData[] getChannelData() {
        return this.channelData;
    }

    public BitInputStream getBitInputStream() {
        return this.bitStream;
    }

    public void addFrameListener(FrameListener listener) {
        this.frameListeners.addFrameListener(listener);
    }

    public void removeFrameListener(FrameListener listener) {
        this.frameListeners.removeFrameListener(listener);
    }

    public void addPCMProcessor(PCMProcessor processor) {
        this.pcmProcessors.addPCMProcessor(processor);
    }

    public void removePCMProcessor(PCMProcessor processor) {
        this.pcmProcessors.removePCMProcessor(processor);
    }

    private void callPCMProcessors(Frame frame) {
        ByteData bd = this.decodeFrame(frame, (ByteData)null);
        this.pcmProcessors.processPCM(bd);
    }

    public ByteData decodeFrame(Frame frame, ByteData pcmData) {
        int byteSize = frame.header.blockSize * this.channels * ((this.streamInfo.getBitsPerSample() + 7) / 2);
        if (pcmData != null && pcmData.getData().length >= byteSize) {
            pcmData.setLen(0);
        } else {
            pcmData = new ByteData(byteSize);
        }

        int i;
        int channel;
        if (this.streamInfo.getBitsPerSample() == 8) {
            for(i = 0; i < frame.header.blockSize; ++i) {
                for(channel = 0; channel < this.channels; ++channel) {
                    pcmData.append((byte)(this.channelData[channel].getOutput()[i] + 128));
                }
            }
        } else if (this.streamInfo.getBitsPerSample() == 16) {
            for(i = 0; i < frame.header.blockSize; ++i) {
                for(channel = 0; channel < this.channels; ++channel) {
                    short val = (short)this.channelData[channel].getOutput()[i];
                    pcmData.append((byte)(val & 255));
                    pcmData.append((byte)(val >> 8 & 255));
                }
            }
        } else if (this.streamInfo.getBitsPerSample() == 24) {
            for(i = 0; i < frame.header.blockSize; ++i) {
                for(channel = 0; channel < this.channels; ++channel) {
                    int val = this.channelData[channel].getOutput()[i];
                    pcmData.append((byte)(val & 255));
                    pcmData.append((byte)(val >> 8 & 255));
                    pcmData.append((byte)(val >> 16 & 255));
                }
            }
        }

        return pcmData;
    }

    public StreamInfo readStreamInfo() throws IOException {
        this.readStreamSync();
        Metadata metadata = this.readNextMetadata();
        if (!(metadata instanceof StreamInfo)) {
            throw new IOException("StreamInfo metadata block missing");
        } else {
            return (StreamInfo)metadata;
        }
    }

    public Metadata[] readMetadata() throws IOException {
        this.readStreamSync();
        Vector metadataList = new Vector();

        Metadata metadata;
        do {
            metadata = this.readNextMetadata();
            metadataList.add(metadata);
        } while(!metadata.isLast());

        return (Metadata[])((Metadata[])metadataList.toArray(new Metadata[0]));
    }

    public Metadata[] readMetadata(StreamInfo streamInfo) throws IOException {
        if (streamInfo.isLast()) {
            return new Metadata[0];
        } else {
            Vector metadataList = new Vector();

            Metadata metadata;
            do {
                metadata = this.readNextMetadata();
                metadataList.add(metadata);
            } while(!metadata.isLast());

            return (Metadata[])((Metadata[])metadataList.toArray(new Metadata[0]));
        }
    }

    public void decode() throws IOException {
        this.readMetadata();

        try {
            while(true) {
                this.findFrameSync();

                try {
                    this.readFrame();
                    this.frameListeners.processFrame(this.frame);
                    this.callPCMProcessors(this.frame);
                } catch (FrameDecodeException var2) {
                    ++this.badFrames;
                }
            }
        } catch (EOFException var3) {
            this.eof = true;
        }
    }


    public int getDuration() {
        // FIXME: refactor and move this
        StreamInfo streamInfo = this.getStreamInfo();
        if (streamInfo != null) {
            return (int) (streamInfo.getTotalSamples() / streamInfo.getSampleRate());
        }

        return 0;
    }

    public void play() throws IOException {
        this.readMetadata();
        long samples = 0;
        try {
            System.out.println(this.getStreamInfo().getAudioFormat());
            System.out.println(this.getStreamInfo().getTotalSamples());
            System.out.println(this.getStreamInfo().getSampleRate());
            System.out.println(this.getStreamInfo().getBitsPerSample());
            while(true) {
                samples++;
                this.findFrameSync();
                System.out.println(samples);
                try {
                    this.readFrame();
                    this.frameListeners.processFrame(this.frame);
                    this.callPCMProcessors(this.frame);
                } catch (FrameDecodeException var2) {
                    ++this.badFrames;
                }
            }
        } catch (EOFException var3) {
            this.eof = true;
        }
    }

    public void decodeFrames() throws IOException {
        try {
            while(true) {
                this.findFrameSync();

                try {
                    this.readFrame();
                    this.frameListeners.processFrame(this.frame);
                    this.callPCMProcessors(this.frame);
                } catch (FrameDecodeException var2) {
                    ++this.badFrames;
                }
            }
        } catch (EOFException var3) {
            this.eof = true;
        }
    }

    public void decode(SeekPoint from, SeekPoint to) throws IOException {
        if (!(this.inputStream instanceof RandomFileInputStream)) {
            throw new IOException("Not a RandomFileInputStream: " + this.inputStream.getClass().getName());
        } else {
            ((RandomFileInputStream)this.inputStream).seek(from.getStreamOffset());
            this.bitStream.reset();
            this.samplesDecoded = from.getSampleNumber();

            try {
                do {
                    this.findFrameSync();

                    try {
                        this.readFrame();
                        this.frameListeners.processFrame(this.frame);
                        this.callPCMProcessors(this.frame);
                    } catch (FrameDecodeException var4) {
                        ++this.badFrames;
                    }
                } while(to == null || this.samplesDecoded < to.getSampleNumber());

            } catch (EOFException var5) {
                this.eof = true;
            }
        }
    }

    public long seek(long samplesAbsolute) throws IOException {
        if (!(this.inputStream instanceof RandomFileInputStream)) {
            throw new IOException("Not a RandomFileInputStream: " + this.inputStream.getClass().getName());
        } else {
            ((RandomFileInputStream)this.inputStream).seek(0L);
            this.bitStream.reset();
            this.samplesDecoded = 0L;
            long seekDelta = samplesAbsolute - this.samplesDecoded;
            this.readMetadata();
            if (this.streamInfo == null) {
                throw new IOException("Could not obtain stream info required for seeking");
            } else if (samplesAbsolute >= 0L && samplesAbsolute < this.streamInfo.getTotalSamples()) {
                if (seekDelta == 0L) {
                    return this.samplesDecoded;
                } else {
                    int estimatedFrameSize = this.streamInfo.getMaxFrameSize();
                    this.findFrameSync();
                    long bytePositionCurrentFrame = (long)(this.bitStream.getTotalBytesRead() - 2);
                    long samplePositionUpper = this.streamInfo.getTotalSamples();
                    long bytePositionUpper = ((RandomFileInputStream)this.inputStream).getLength() - (long)estimatedFrameSize - bytePositionCurrentFrame;
                    SeekPoint beforeSeekPosition = new SeekPoint(0L, 0L, estimatedFrameSize);
                    SeekPoint afterSeekPosition = new SeekPoint(samplePositionUpper, bytePositionUpper, this.streamInfo.getMinBlockSize());
                    if (this.seekTable != null) {
                        for(int i = 0; i < this.seekTable.numberOfPoints(); ++i) {
                            SeekPoint currentSeekPoint = this.seekTable.getSeekPoint(i);
                            if (currentSeekPoint.getSampleNumber() < samplesAbsolute) {
                                if (currentSeekPoint.getSampleNumber() > beforeSeekPosition.getSampleNumber()) {
                                    beforeSeekPosition = currentSeekPoint;
                                    if (samplesAbsolute < currentSeekPoint.getSampleNumber() + (long)currentSeekPoint.getFrameSamples()) {
                                        afterSeekPosition = currentSeekPoint;
                                        break;
                                    }
                                }
                            } else if (currentSeekPoint.getSampleNumber() > samplesAbsolute) {
                                if (currentSeekPoint.getSampleNumber() < afterSeekPosition.getSampleNumber()) {
                                    afterSeekPosition = currentSeekPoint;
                                }
                            } else if (currentSeekPoint.getSampleNumber() == samplesAbsolute) {
                                beforeSeekPosition = currentSeekPoint;
                                afterSeekPosition = currentSeekPoint;
                                break;
                            }
                        }
                    }

                    long bytePositionEstimate = bytePositionCurrentFrame + beforeSeekPosition.getStreamOffset();
                    if (afterSeekPosition.getSampleNumber() > beforeSeekPosition.getSampleNumber()) {
                        double percentBetweenSeekPositions = (double)(samplesAbsolute - beforeSeekPosition.getSampleNumber()) / (double)(afterSeekPosition.getSampleNumber() - beforeSeekPosition.getSampleNumber());
                        long bytePositionBetweenSeekPositions = (long)((double)(afterSeekPosition.getStreamOffset() - beforeSeekPosition.getStreamOffset()) * percentBetweenSeekPositions) - (long)estimatedFrameSize;
                        if (bytePositionBetweenSeekPositions > 0L) {
                            bytePositionEstimate += bytePositionBetweenSeekPositions;
                        }
                    }

                    ((RandomFileInputStream)this.inputStream).seek(bytePositionEstimate);
                    this.bitStream.reset();
                    int framesToSeekBack = 1;
                    new HashMap();

                    while(seekDelta != 0L) {
                        this.findFrameSync();
                        bytePositionCurrentFrame = bytePositionEstimate + (long)this.bitStream.getTotalBytesRead() - 2L;

                        try {
                            this.readFrame();
                        } catch (FrameDecodeException var20) {
                            ++this.badFrames;
                            continue;
                        }

                        bytePositionEstimate += (long)this.bitStream.getTotalBytesRead();
                        Map seekBackwardsResults;
                        if (this.frame == null) {
                            if (!this.eof) {
                                throw new IOException("Fatal seek data frame reading error");
                            }

                            seekBackwardsResults = this.seekBackwards(bytePositionCurrentFrame, framesToSeekBack, (long)estimatedFrameSize);
                            bytePositionEstimate = (Long)seekBackwardsResults.get("bytePositionEstimate");
                            framesToSeekBack = (Integer)seekBackwardsResults.get("framesToSeekBack");
                        } else {
                            this.samplesDecoded = this.frame.header.sampleNumber;
                            seekDelta = samplesAbsolute - this.samplesDecoded;
                            if (seekDelta < 0L) {
                                seekBackwardsResults = this.seekBackwards(bytePositionCurrentFrame, framesToSeekBack, (long)estimatedFrameSize);
                                bytePositionEstimate = (Long)seekBackwardsResults.get("bytePositionEstimate");
                                framesToSeekBack = (Integer)seekBackwardsResults.get("framesToSeekBack");
                            } else {
                                if (seekDelta < (long)this.frame.header.blockSize) {
                                    ((RandomFileInputStream)this.inputStream).seek(bytePositionCurrentFrame);
                                    this.bitStream.reset();
                                    break;
                                }

                                if (seekDelta < (long)this.frame.header.blockSize) {
                                    throw new IOException("Fatal seek logic error");
                                }

                                framesToSeekBack = 0;
                                ((RandomFileInputStream)this.inputStream).seek(bytePositionEstimate);
                                this.bitStream.reset();
                            }
                        }
                    }

                    return this.samplesDecoded;
                }
            } else {
                throw new IllegalArgumentException("Invalid sample position for seek");
            }
        }
    }

    private Map<String, Object> seekBackwards(long bytePositionEstimate, int framesToSeekBack, long estimatedFrameSize) throws IOException {
        if (framesToSeekBack > 0) {
            bytePositionEstimate -= (long)framesToSeekBack * estimatedFrameSize;
            if (bytePositionEstimate < 0L) {
                bytePositionEstimate = 0L;
                framesToSeekBack = 0;
            } else {
                ++framesToSeekBack;
            }

            ((RandomFileInputStream)this.inputStream).seek(bytePositionEstimate);
            this.bitStream.reset();
            Map<String, Object> returnVariables = new HashMap();
            returnVariables.put("bytePositionEstimate", bytePositionEstimate);
            returnVariables.put("framesToSeekBack", framesToSeekBack);
            return returnVariables;
        } else {
            throw new IOException("Fatal seek error: sample position not found");
        }
    }

    public Frame readNextFrame() throws IOException {
        try {
            while(true) {
                this.findFrameSync();

                try {
                    this.readFrame();
                    return this.frame;
                } catch (FrameDecodeException var2) {
                    ++this.badFrames;
                }
            }
        } catch (EOFException var3) {
            this.eof = true;
            return null;
        }
    }

    public long getTotalBytesRead() {
        return (long)this.bitStream.getTotalBytesRead();
    }

    private void allocateOutput(int size, int channels) {
        if (size > this.outputCapacity || channels > this.outputChannels) {
            int i;
            for(i = 0; i < 8; ++i) {
                this.channelData[i] = null;
            }

            for(i = 0; i < channels; ++i) {
                this.channelData[i] = new ChannelData(size);
            }

            this.outputCapacity = size;
            this.outputChannels = channels;
        }
    }

    private void readStreamSync() throws IOException {
        int id = 0;
        int i = 0;

        while(i < 4) {
            int x = this.bitStream.readRawUInt(8);
            if (x == Constants.STREAM_SYNC_STRING[i]) {
                ++i;
                id = 0;
            } else {
                if (x != ID3V2_TAG[id]) {
                    throw new IOException("Could not find Stream Sync");
                }

                ++id;
                i = 0;
                if (id == 3) {
                    this.skipID3v2Tag();
                    id = 0;
                }
            }
        }

    }

    public Metadata readNextMetadata() throws IOException {
        Metadata metadata = null;
        boolean isLast = this.bitStream.readRawUInt(1) != 0;
        int type = this.bitStream.readRawUInt(7);
        int length = this.bitStream.readRawUInt(24);
        if (type == 0) {
            this.streamInfo = new StreamInfo(this.bitStream, length, isLast);
            metadata = this.streamInfo;
            this.pcmProcessors.processStreamInfo((StreamInfo)metadata);
        } else if (type == 3) {
            this.seekTable = new SeekTable(this.bitStream, length, isLast);
            metadata = this.seekTable;
        } else if (type == 2) {
            metadata = new Application(this.bitStream, length, isLast);
        } else if (type == 1) {
            metadata = new Padding(this.bitStream, length, isLast);
        } else if (type == 4) {
            metadata = new VorbisComment(this.bitStream, length, isLast);
        } else if (type == 5) {
            metadata = new CueSheet(this.bitStream, length, isLast);
        } else if (type == 6) {
            metadata = new Picture(this.bitStream, length, isLast);
        } else {
            metadata = new Unknown(this.bitStream, length, isLast);
        }

        this.frameListeners.processMetadata((Metadata)metadata);
        return (Metadata)metadata;
    }

    private void skipID3v2Tag() throws IOException {
        int verMajor = this.bitStream.readRawInt(8);
        int verMinor = this.bitStream.readRawInt(8);
        int flags = this.bitStream.readRawInt(8);
        int skip = 0;

        for(int i = 0; i < 4; ++i) {
            int x = this.bitStream.readRawUInt(8);
            skip <<= 7;
            skip |= x & 127;
        }

        this.bitStream.readByteBlockAlignedNoCRC((byte[])null, skip);
    }

    private void findFrameSync() throws IOException {
        boolean first = true;
        if (this.streamInfo == null || this.streamInfo.getTotalSamples() <= 0L || this.samplesDecoded < this.streamInfo.getTotalSamples()) {
            if (!this.bitStream.isConsumedByteAligned()) {
                this.bitStream.readRawUInt(this.bitStream.bitsLeftForByteAlignment());
            }

            try {
                while(true) {
                    int x = this.bitStream.readRawUInt(8);
                    if (x == 255) {
                        this.headerWarmup[0] = (byte)x;
                        x = this.bitStream.peekRawUInt(8);
                        if (x >> 2 == 62) {
                            this.headerWarmup[1] = (byte)this.bitStream.readRawUInt(8);
                            return;
                        }
                    }

                    if (first) {
                        this.frameListeners.processError("FindSync LOST_SYNC: " + Integer.toHexString(x & 255));
                        first = false;
                    }
                }
            } catch (EOFException var4) {
                if (!first) {
                    this.frameListeners.processError("FindSync LOST_SYNC: Left over data in file");
                }

            }
        }
    }

    public void readFrame() throws IOException, FrameDecodeException {
        boolean gotAFrame = false;
        short frameCRC = 0;
        frameCRC = CRC16.update(this.headerWarmup[0], frameCRC);
        frameCRC = CRC16.update(this.headerWarmup[1], frameCRC);
        this.bitStream.resetReadCRC16(frameCRC);

        try {
            this.frame.header = new Header(this.bitStream, this.headerWarmup, this.streamInfo);
        } catch (BadHeaderException var10) {
            this.frameListeners.processError("Found bad header: " + var10);
            throw new FrameDecodeException("Bad Frame Header: " + var10);
        }

        this.allocateOutput(this.frame.header.blockSize, this.frame.header.channels);

        int channel;
        int j;
        for(channel = 0; channel < this.frame.header.channels; ++channel) {
            j = this.frame.header.bitsPerSample;
            switch(this.frame.header.channelAssignment) {
                case 0:
                default:
                    break;
                case 1:
                    if (channel == 1) {
                        ++j;
                    }
                    break;
                case 2:
                    if (channel == 0) {
                        ++j;
                    }
                    break;
                case 3:
                    if (channel == 1) {
                        ++j;
                    }
            }

            try {
                this.readSubframe(channel, j);
            } catch (IOException var9) {
                this.frameListeners.processError("ReadSubframe: " + var9);
                throw var9;
            }
        }

        this.readZeroPadding();
        frameCRC = this.bitStream.getReadCRC16();
        this.frame.setCRC((short)this.bitStream.readRawUInt(16));
        if (frameCRC == this.frame.getCRC()) {
            int i;
            label81:
            switch(this.frame.header.channelAssignment) {
                case 0:
                default:
                    break;
                case 1:
                    i = 0;

                    while(true) {
                        if (i >= this.frame.header.blockSize) {
                            break label81;
                        }

                        this.channelData[1].getOutput()[i] = this.channelData[0].getOutput()[i] - this.channelData[1].getOutput()[i];
                        ++i;
                    }
                case 2:
                    i = 0;

                    while(true) {
                        if (i >= this.frame.header.blockSize) {
                            break label81;
                        }

                        int[] var10000 = this.channelData[0].getOutput();
                        var10000[i] += this.channelData[1].getOutput()[i];
                        ++i;
                    }
                case 3:
                    for(i = 0; i < this.frame.header.blockSize; ++i) {
                        int mid = this.channelData[0].getOutput()[i];
                        int side = this.channelData[1].getOutput()[i];
                        mid <<= 1;
                        mid |= side & 1;
                        this.channelData[0].getOutput()[i] = mid + side >> 1;
                        this.channelData[1].getOutput()[i] = mid - side >> 1;
                    }
            }

            gotAFrame = true;
        } else {
            this.frameListeners.processError("CRC Error: " + Integer.toHexString(frameCRC & '\uffff') + " vs " + Integer.toHexString(this.frame.getCRC() & '\uffff'));

            for(channel = 0; channel < this.frame.header.channels; ++channel) {
                for(j = 0; j < this.frame.header.blockSize; ++j) {
                    this.channelData[channel].getOutput()[j] = 0;
                }
            }
        }

        this.channels = this.frame.header.channels;
        this.samplesDecoded += (long)this.frame.header.blockSize;
    }

    private void readSubframe(int channel, int bps) throws IOException, FrameDecodeException {
        int x = this.bitStream.readRawUInt(8);
        boolean haveWastedBits = (x & 1) != 0;
        x &= 254;
        int wastedBits = 0;
        if (haveWastedBits) {
            wastedBits = this.bitStream.readUnaryUnsigned() + 1;
            bps -= wastedBits;
        }

        if ((x & 128) != 0) {
            this.frameListeners.processError("ReadSubframe LOST_SYNC: " + Integer.toHexString(x & 255));
            throw new FrameDecodeException("ReadSubframe LOST_SYNC: " + Integer.toHexString(x & 255));
        } else {
            if (x == 0) {
                this.frame.subframes[channel] = new ChannelConstant(this.bitStream, this.frame.header, this.channelData[channel], bps, wastedBits);
            } else if (x == 2) {
                this.frame.subframes[channel] = new ChannelVerbatim(this.bitStream, this.frame.header, this.channelData[channel], bps, wastedBits);
            } else {
                if (x < 16) {
                    throw new FrameDecodeException("ReadSubframe Bad Subframe Type: " + Integer.toHexString(x & 255));
                }

                if (x <= 24) {
                    this.frame.subframes[channel] = new ChannelFixed(this.bitStream, this.frame.header, this.channelData[channel], bps, wastedBits, x >> 1 & 7);
                } else {
                    if (x < 64) {
                        throw new FrameDecodeException("ReadSubframe Bad Subframe Type: " + Integer.toHexString(x & 255));
                    }

                    this.frame.subframes[channel] = new ChannelLPC(this.bitStream, this.frame.header, this.channelData[channel], bps, wastedBits, (x >> 1 & 31) + 1);
                }
            }

            if (haveWastedBits) {
                x = this.frame.subframes[channel].getWastedBits();

                for(int i = 0; i < this.frame.header.blockSize; ++i) {
                    int[] var10000 = this.channelData[channel].getOutput();
                    var10000[i] <<= x;
                }
            }

        }
    }

    private void readZeroPadding() throws IOException, FrameDecodeException {
        if (!this.bitStream.isConsumedByteAligned()) {
            int zero = this.bitStream.readRawUInt(this.bitStream.bitsLeftForByteAlignment());
            if (zero != 0) {
                this.frameListeners.processError("ZeroPaddingError: " + Integer.toHexString(zero));
                throw new FrameDecodeException("ZeroPaddingError: " + Integer.toHexString(zero));
            }
        }

    }

    public long getSamplesDecoded() {
        return this.samplesDecoded;
    }

    public int getBadFrames() {
        return this.badFrames;
    }

    public boolean isEOF() {
        return this.eof;
    }
}
