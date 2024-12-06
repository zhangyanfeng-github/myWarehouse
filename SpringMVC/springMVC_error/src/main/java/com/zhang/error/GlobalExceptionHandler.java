package com.zhang.error;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//全局异常处理器，程序中出现异常，就会执行此类中的相关方法
@RestControllerAdvice
public class GlobalExceptionHandler {

    //若出现空指针异常，就会执行此方法
    @ExceptionHandler(NullPointerException.class)
    public Object NullPointerExceptionHandler(NullPointerException e) {
        String message = e.getMessage();
        System.out.println("message = " + e);
        return message;
    }

    //除数为0异常
    @ExceptionHandler(ArithmeticException.class)
    public Object ArithmeticException(ArithmeticException e) {
        String message = e.getMessage();
        System.out.println("message = " + e);
        return message;
    }

    //父类异常
    @ExceptionHandler(Exception.class)
    public Object ExceptionHandler(Exception e) {
        String message = e.getMessage();
        System.out.println("message = " + e);
        return message;
    }

}
