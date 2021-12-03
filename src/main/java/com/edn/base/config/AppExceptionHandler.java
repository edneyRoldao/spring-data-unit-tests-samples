package com.edn.base.config;

import com.edn.base.resizeImage.exception.InvalidImageFormatException;
import com.edn.base.resizeImage.exception.InvalidImageSizeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@SuppressWarnings("unused")
public class AppExceptionHandler {

    @ExceptionHandler(InvalidImageFormatException.class)
    public final ResponseEntity<String> handleException(InvalidImageFormatException e, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid image format");
    }

    @ExceptionHandler(InvalidImageSizeException.class)
    public final ResponseEntity<String> handleException(InvalidImageSizeException e, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid image size. " + e.getMessage());
    }

}
