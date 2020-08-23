/**
 * FileUtils
 *
 * @author Laurean Ray
 * @version 1.0
 * @since 8/23/20
 */

package tech.laureanray.utils;

import java.io.File;

public class FileUtils {
    public static String getFileExt(String path) {
        var fileName = new File(path).getName();

        String extension = "";

        int i = fileName.lastIndexOf('.');
        int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));

        if (i > p) {
            extension = fileName.substring(i+1);
        }

        return extension;
    }
}
