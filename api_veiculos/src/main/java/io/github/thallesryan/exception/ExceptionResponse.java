package io.github.thallesryan.exception;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class ExceptionResponse implements Serializable{

	 private Date timestamp;
	    private String message;
	    private String details;
}
