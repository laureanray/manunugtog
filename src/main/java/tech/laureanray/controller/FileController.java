package tech.laureanray.controller;

public class FileController {
    private static FileController instance;

    private FileController() { }

    public static FileController getInstance() {
        if (FileController.instance == null) {
            FileController.instance = new FileController();
        }

        return instance;
    }
}
