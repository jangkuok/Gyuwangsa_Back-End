package com.gywangsa.controller.advice;

import com.gywangsa.util.CustomJWTException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.NoSuchElementException;

//예외 처리
@RestControllerAdvice
public class CustomAdviceController {

    //상품이 없을 경우
    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<?> notExist(NoSuchElementException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("msg",e.getMessage()));
    }

    //리스트 잘못 조회했을 경우
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> notExist(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(Map.of("msg",e.getMessage()));
    }

    //JWT 에러
    @ExceptionHandler(CustomJWTException.class)
    protected ResponseEntity<?> handleJWTException(CustomJWTException e){
        String msg = e.getMessage();

        return ResponseEntity.ok().body(Map.of("error",msg));
    }
}
