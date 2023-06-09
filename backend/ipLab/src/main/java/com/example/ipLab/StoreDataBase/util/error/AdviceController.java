package com.example.ipLab.StoreDataBase.util.error;

import com.example.ipLab.StoreDataBase.Exceptions.CustomerNotFoundException;
import com.example.ipLab.StoreDataBase.Exceptions.OrderedNotFoundException;
import com.example.ipLab.StoreDataBase.Exceptions.ProductNotFoundException;
import com.example.ipLab.StoreDataBase.Exceptions.StoreNotFoundException;
import com.example.ipLab.StoreDataBase.util.validation.ValidationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;
import java.util.stream.Collectors;

@ControllerAdvice(annotations = RestController.class)
public class AdviceController {
    @ExceptionHandler({
            CustomerNotFoundException.class,
            OrderedNotFoundException.class,
            ProductNotFoundException.class,
            StoreNotFoundException.class
    })
    public ResponseEntity<Object> handleException(Throwable e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleBindException(MethodArgumentNotValidException e) {
        final ValidationException validationException = new ValidationException(
                e.getBindingResult().getAllErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toSet()));
        return handleException(validationException);
    }

    @ExceptionHandler({
            AccessDeniedException.class
    })
    public ResponseEntity<Object> handleAccessDeniedException(Throwable e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnknownException(Throwable e) {
        e.printStackTrace();
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
