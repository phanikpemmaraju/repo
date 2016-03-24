package uk.gov.dwp.esf.mi.exceptionhandlers;

import uk.gov.dwp.esf.mi.common.ErrorMessage;
import uk.gov.dwp.esf.mi.exceptions.DataException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

@Component
public class ExceptionMapper{
	

	public ErrorMessage getException(DataException exception, HttpStatus httpStatus) {
		// TODO Auto-generated method stub
		ErrorMessage error = mapError(exception,httpStatus);
		return error;
	}
			
	public ErrorMessage getException(FieldError fieldError, HttpStatus httpStatus) {
		// TODO Auto-generated method stub
		ErrorMessage error = mapError(fieldError,httpStatus);
		return error;
	}
		
	/**
     * Adds the status and error attribute values from the {@link HttpStatus}
     * value.
     * @param exception Exception
     * @param httpStatus HttpStatus
     * @param error ESFErrorMessage
     */
    private ErrorMessage mapError(DataException exception,HttpStatus httpStatus) {
    	//final ErrorMessage error = new ErrorMessage.ErrorBuilder(String.valueOf(httpStatus.value()),exception.getMessage(),httpStatus.getReasonPhrase()).build();
    	final ErrorMessage error = new ErrorMessage.ErrorBuilder(exception.getCode(),exception.getMessage(),httpStatus.getReasonPhrase()).build();
    	return error;
    }

	
    /**
     * Adds the status and error attribute values from the {@link HttpStatus}
     * value.
     * @param fieldError FieldError
     * @param httpStatus HttpStatus
     * @param error ESFErrorMessage
     */
	private ErrorMessage mapError(FieldError fieldError,HttpStatus httpStatus) {
    	//final ErrorMessage error = new ErrorMessage.ErrorBuilder(String.valueOf(httpStatus.value()),fieldError.getDefaultMessage(),httpStatus.getReasonPhrase()).build();
		final ErrorMessage error = new ErrorMessage.ErrorBuilder(fieldError.getDefaultMessage(),"Field Error on field " + fieldError.getField(),httpStatus.getReasonPhrase()).build();
    	return error;
    }
    
    
}
