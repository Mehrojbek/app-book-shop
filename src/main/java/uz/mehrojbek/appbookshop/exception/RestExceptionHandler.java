package uz.mehrojbek.appbookshop.exception;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.mehrojbek.appbookshop.payload.ApiResult;


@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {RestException.class})
    public HttpEntity<?> handleException(RestException ex){
        return new ResponseEntity<>(new ApiResult<>(ex.getMessage(),false),ex.getStatus());
    }

    @ExceptionHandler(value = {Exception.class})
    public HttpEntity<?> handleException(Exception ex){
        return new ResponseEntity<>(new ApiResult<>(ex.getMessage(),false), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
