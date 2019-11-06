package com.fan.wang.controller;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public Object exceptionHandler(HttpServletRequest request,Exception e){
        if(e instanceof MethodArgumentNotValidException){

            MethodArgumentNotValidException ex =
                    (MethodArgumentNotValidException) e;
            BindingResult bindingResult = ex.getBindingResult();
            String msg = handlerErrors(bindingResult);
            return msg;
        }else if(e instanceof BindException){

            BindException ex = (BindException) e;
            BindingResult bindingResult = ex.getBindingResult();
            String msg = handlerErrors(bindingResult);
            return msg;
        }
        return null;
    }

    private String handlerErrors(BindingResult bindingResult){
        List<ObjectError> errors = bindingResult.getAllErrors();
        ObjectError error = errors.get(0);
        String msg = error.getDefaultMessage();
        return msg;
    }
}
