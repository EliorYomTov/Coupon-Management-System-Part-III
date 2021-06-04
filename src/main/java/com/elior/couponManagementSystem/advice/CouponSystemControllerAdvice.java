package com.elior.couponManagementSystem.advice;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.elior.couponManagementSystem.exception.CouponSystemException;
import com.elior.couponManagementSystem.exception.LoginFailedException;
import com.elior.couponManagementSystem.exception.SecuritySystemException;

@RestController
@ControllerAdvice // AOP for Spring Controller like a boss!
public class CouponSystemControllerAdvice {

	@ExceptionHandler({CouponSystemException.class, LoginFailedException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDetails handlError(Exception e) {
		return new ErrorDetails(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")),"BAD_REQUEST", e.getMessage(), 400);
	}
	
	@ExceptionHandler(SecuritySystemException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ErrorDetails securityHandlError(Exception e) {
		return new ErrorDetails(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")), "UNAUTHORIZED", e.getMessage(), 401);
	}
	
	@ExceptionHandler(ConversionFailedException.class)
    public ErrorDetails handleConvert(Exception e) {
		return new ErrorDetails(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")), "BAD_REQUEST", "Illegal value - Category not found!", 400);
    }
}
