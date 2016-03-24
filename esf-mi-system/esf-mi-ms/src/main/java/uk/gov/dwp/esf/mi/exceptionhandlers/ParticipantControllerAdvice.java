package uk.gov.dwp.esf.mi.exceptionhandlers;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import uk.gov.dwp.esf.mi.common.ErrorMessage;
import uk.gov.dwp.esf.mi.exceptions.DataException;
import uk.gov.dwp.esf.mi.exceptions.PredicateException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ParticipantControllerAdvice {
	
	//protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ExceptionMapper exceptionMapper;	
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<ErrorMessage>> handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException) {
		List<ErrorMessage> errors = new ArrayList<>();
		methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach(fieldError -> errors.add(exceptionMapper.getException(fieldError, HttpStatus.BAD_REQUEST)));
        return new ResponseEntity<List<ErrorMessage>>(errors,HttpStatus.BAD_REQUEST);
    }

	@ExceptionHandler(PredicateException.class)
	public ResponseEntity<List<ErrorMessage>> handlePredicateException(PredicateException predicateException) {
        List<ErrorMessage> errors = new ArrayList<>();
        predicateException.getExceptions().forEach(exception -> errors.add(exceptionMapper.getException(exception, HttpStatus.BAD_REQUEST)));
        return new ResponseEntity<List<ErrorMessage>>(errors,HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(DataException.class)
	public ResponseEntity<List<ErrorMessage>> handleDataException(DataException dataException) {
		//logger.info("> handlePredicateException - : "+ predicateException.getClass().getSimpleName());
        List<ErrorMessage> errors = new ArrayList<>();
        errors.add(exceptionMapper.getException(dataException, HttpStatus.BAD_REQUEST));
        return new ResponseEntity<List<ErrorMessage>>(errors,HttpStatus.BAD_REQUEST);
    }

}
