package demo.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import demo.dto.ErrorHolder;

@ControllerAdvice(annotations = RestController.class)
public class RestErrorAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        return new ErrorHolder(HttpStatus.INTERNAL_SERVER_ERROR, "サーバー側で問題が発生した可能性があります。").result();
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException e) {
        return new ErrorHolder(HttpStatus.NOT_FOUND, "情報が見つかりませんでした。").result();
    }

    @ExceptionHandler(IncorrectResultSizeDataAccessException.class)
    public ResponseEntity<Object> handleIncorrectResultSizeDataAccessException(IncorrectResultSizeDataAccessException e) {
        return handleException(e);
    }
}
