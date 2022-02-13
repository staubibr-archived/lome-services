package com.lifecycle.components;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class Controller {

	
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestResponse> handleException(Exception e, HttpServletRequest r) {
    	RestResponse error = RestResponse.builder()
    							   .message(e.getMessage())
    							   .path(r.getRequestURI())
    							   .timestamp(new Date())
    							   .status(HttpStatus.BAD_REQUEST.value())
    							   .build();
    	    	
        return new ResponseEntity<RestResponse>(error, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<RestResponse> handleSuccess() {
    	ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
    	    	
    	RestResponse resp = RestResponse.builder()
    							  		.message("Success")
    							  		.path(attr.getRequest().getRequestURI())
    							  		.timestamp(new Date())
    							  		.status(HttpStatus.OK.value())
    							  		.build();
    	    	
        return new ResponseEntity<RestResponse>(resp, HttpStatus.OK);
    }
}
