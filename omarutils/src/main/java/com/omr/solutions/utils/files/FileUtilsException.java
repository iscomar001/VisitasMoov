package com.omr.solutions.utils.files;

/**
 * Created by OmarAlberto on 8/1/2015.
 */
public class FileUtilsException extends Exception {

    public FileUtilsException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public FileUtilsException(String detailMessage) {
        super(detailMessage);
    }

    public FileUtilsException() {
    }
}
