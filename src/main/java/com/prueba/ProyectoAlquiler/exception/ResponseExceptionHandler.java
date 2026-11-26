package com.prueba.ProyectoAlquiler.exception;

import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorTemplate> handleDefaultException(Exception ex, WebRequest request) {
        CustomErrorTemplate error = new CustomErrorTemplate(
                LocalDateTime.now(),
                "Error interno del servidor",
                request.getDescription(false)
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<CustomErrorTemplate> handleModelNotFoundException(ModelNotFoundException ex, WebRequest request) {
        CustomErrorTemplate error = new CustomErrorTemplate(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(org.springframework.security.core.AuthenticationException.class)
    public ResponseEntity<CustomErrorTemplate> auth(Exception ex, WebRequest r){return error(401,"Credenciales invalidas o usuario deshabilitado",r);}
    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
    public ResponseEntity<CustomErrorTemplate> denied(Exception ex, WebRequest r){return error(403,"Acceso denegado",r);}
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CustomErrorTemplate> invalid(Exception ex, WebRequest r){return error(400,ex.getMessage(),r);}
    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    public ResponseEntity<CustomErrorTemplate> conflict(Exception ex, WebRequest r){return error(409,"Datos duplicados, incompletos o referencias invalidas",r);}
    private ResponseEntity<CustomErrorTemplate> error(int status,String message,WebRequest r){return ResponseEntity.status(status).body(new CustomErrorTemplate(LocalDateTime.now(),message,r.getDescription(false)));}
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<CustomErrorTemplate> handleArithmeticException(ArithmeticException ex, WebRequest request) {
        CustomErrorTemplate error = new CustomErrorTemplate(
                LocalDateTime.now(),
                "Error interno del servidor",
                request.getDescription(false)
        );
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error);
    }

    @Override
    protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String msg = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(","));

        CustomErrorTemplate error = new CustomErrorTemplate(
                LocalDateTime.now(),
                msg,
                request.getDescription(false)
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
