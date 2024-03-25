package com.example.habittrackr.exeption;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(JdbcSQLIntegrityConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleIntegrityConstraintViolationException(JdbcSQLIntegrityConstraintViolationException ex) {
        String errorMessage = "Произошло нарушение ссылочной целостности при выполнении запроса к базе данных. ";

        String databaseErrorMessage = ex.getMessage();
        errorMessage += "Дополнительная информация: " + databaseErrorMessage;
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}
