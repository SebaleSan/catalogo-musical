package com.duoc.music.error;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validacion(MethodArgumentNotValidException ex,
                                                       HttpServletRequest req) {
        Map<String,String> fields = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            fields.putIfAbsent(error.getField(), error.getDefaultMessage()));
        return response(HttpStatus.BAD_REQUEST,"Solicitud invalida",req,fields);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> inexistente(ResourceNotFoundException ex,
                                                       HttpServletRequest req) {
        return response(HttpStatus.NOT_FOUND,ex.getMessage(),req,Map.of());
    }
    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> jsonIncorrecto(Exception ex,HttpServletRequest req) {
        return response(HttpStatus.BAD_REQUEST,"JSON con formato incorrecto",req,Map.of());
    }
    private ResponseEntity<ErrorResponse> response(HttpStatus status,String msg,
                                   HttpServletRequest req,Map<String,String> fields) {
        return ResponseEntity.status(status).body(new ErrorResponse(
            Instant.now(),status.value(),msg,req.getRequestURI(),fields));
    }

    
}
