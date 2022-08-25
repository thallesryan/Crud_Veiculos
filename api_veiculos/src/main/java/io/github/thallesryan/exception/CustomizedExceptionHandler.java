package io.github.thallesryan.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(WebExchangeBindException.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(
			WebExchangeBindException ex, WebRequest request) {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(),
				ex.getAllErrors().get(0).getDefaultMessage(),
				request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	 @ExceptionHandler(ResourceNotFoundException.class)
	    public final ResponseEntity<ExceptionResponse> handleContentNotFoundException(Exception ex, WebRequest req){

	        ExceptionResponse exceptionResponse =
	                new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false));

	        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
	    }
}
