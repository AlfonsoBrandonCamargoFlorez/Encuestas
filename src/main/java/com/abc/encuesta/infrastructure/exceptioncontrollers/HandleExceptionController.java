package com.abc.encuesta.infrastructure.exceptioncontrollers;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.dao.DataIntegrityViolationException;
import com.abc.encuesta.infrastructure.models.ErrorCustom;

@RestControllerAdvice
public class HandleExceptionController {

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorCustom> handleNoResourceFoundException(NoResourceFoundException ex) {
        ErrorCustom error = new ErrorCustom();
        error.setDate(new Date());
        error.setError("Pagina No encontrada.");
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorCustom> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ErrorCustom error = new ErrorCustom();
        error.setDate(new Date());
        error.setError("Error de validación.");
        error.setMessage("Se encontraron errores en los datos enviados.");
        error.setStatus(HttpStatus.BAD_REQUEST.value());

        StringBuilder errors = new StringBuilder();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.append(fieldError.getField()).append(": ").append(fieldError.getDefaultMessage()).append(". ");
        }
        error.setMessage(errors.toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorCustom> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ErrorCustom error = new ErrorCustom();
        error.setDate(new Date());
        error.setError("Error de integridad de datos.");
        error.setMessage("Se ha producido un error al acceder a la base de datos.");
        error.setStatus(HttpStatus.CONFLICT.value());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorCustom> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        ErrorCustom error = new ErrorCustom();
        error.setDate(new Date());
        error.setError("Método HTTP no soportado.");
        error.setMessage("El método HTTP no es soportado para esta solicitud.");
        error.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorCustom> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorCustom error = new ErrorCustom();
        error.setDate(new Date());
        error.setError("Argumento no válido.");
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorCustom> handleException(Exception ex) {
        ErrorCustom error = new ErrorCustom();
        error.setDate(new Date());
        error.setError("Error interno del servidor.");
        error.setMessage("Se ha producido un error inesperado. Por favor, inténtelo de nuevo más tarde.");
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
