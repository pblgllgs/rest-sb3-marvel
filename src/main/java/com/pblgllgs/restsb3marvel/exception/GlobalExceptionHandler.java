package com.pblgllgs.restsb3marvel.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;

/*
 *
 * @author pblgl
 * Created on 20-12-2023
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorDto> handleGeneralExceptions(
            Exception exception,
            HttpServletRequest httpServletRequest,
            WebRequest webRequest
    ) {
        if (exception instanceof HttpClientErrorException) {
            return this.handleHttpClientErrorException((HttpClientErrorException) exception, httpServletRequest, webRequest);
        } else if (exception instanceof AccessDeniedException) {
            return this.handleAccessDeniedException((AccessDeniedException) exception, httpServletRequest, webRequest);
        } else if (exception instanceof AuthenticationCredentialsNotFoundException) {
            return this.handleAuthenticationCredentialsNotFoundException((AuthenticationCredentialsNotFoundException) exception, httpServletRequest, webRequest);
        } else {
            return this.handleGenericException(exception, httpServletRequest, webRequest);
        }
    }

    private ResponseEntity<ApiErrorDto> handleGenericException(
            Exception exception, HttpServletRequest httpServletRequest, WebRequest webRequest
    ) {
        ApiErrorDto dto = new ApiErrorDto(
                "Error inesperado, vuelva a intentarlo",
                exception.getMessage(),
                httpServletRequest.getMethod(),
                httpServletRequest.getRequestURL().toString()
        );
        return ResponseEntity.internalServerError().body(dto);
    }

    private ResponseEntity<ApiErrorDto> handleAuthenticationCredentialsNotFoundException(
            AuthenticationCredentialsNotFoundException exception, HttpServletRequest httpServletRequest, WebRequest webRequest
    ) {
        ApiErrorDto dto = new ApiErrorDto(
                "No tienes acceso a este recurso",
                exception.getMessage(),
                httpServletRequest.getMethod(),
                httpServletRequest.getRequestURL().toString()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(dto);
    }

    private ResponseEntity<ApiErrorDto> handleAccessDeniedException(
            AccessDeniedException exception, HttpServletRequest httpServletRequest, WebRequest webRequest
    ) {
        ApiErrorDto dto = new ApiErrorDto(
                "No tienes acceso a este recurso",
                exception.getMessage(),
                httpServletRequest.getMethod(),
                httpServletRequest.getRequestURL().toString()
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(dto);
    }

    private ResponseEntity<ApiErrorDto> handleHttpClientErrorException(
            HttpClientErrorException exception, HttpServletRequest httpServletRequest, WebRequest webRequest
    ) {
        String message =null;
        if (exception instanceof HttpClientErrorException.Forbidden){
            message = "No tienes acceso a este recurso";
        }else if (exception instanceof HttpClientErrorException.Unauthorized){
            message = "No has iniciado sesion para acceder a este recurso";
        } else if (exception instanceof HttpClientErrorException.NotFound){
            message = "Recurso no encontrado";
        } else if (exception instanceof HttpClientErrorException.Conflict){
            message = "Conflicto en el procesamiento de la peticion";
        }
        ApiErrorDto dto = new ApiErrorDto(
                message,
                exception.getMessage(),
                httpServletRequest.getMethod(),
                httpServletRequest.getRequestURL().toString()
        );
        return ResponseEntity.status(exception.getStatusCode()).body(dto);
    }
}
