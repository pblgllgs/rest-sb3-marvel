package com.pblgllgs.restsb3marvel.exception;
/*
 *
 * @author pblgl
 * Created on 19-12-2023
 *
 */

public class ApiErrorException extends RuntimeException{

    public ApiErrorException() {
    }

    public ApiErrorException(String message) {
        super(message);
    }

    public ApiErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiErrorException(Throwable cause) {
        super(cause);
    }
}
