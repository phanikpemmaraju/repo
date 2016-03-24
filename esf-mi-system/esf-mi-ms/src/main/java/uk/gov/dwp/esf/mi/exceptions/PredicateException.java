package uk.gov.dwp.esf.mi.exceptions;

import java.util.List;

public class PredicateException extends DataException{

	public PredicateException(String code, String message) {
		super(code, message);
	}

	public PredicateException() {
	}

	private static final long serialVersionUID = 1L;
	private List<DataException> exceptions;
	
	public List<DataException> getExceptions() {
		return exceptions;
	}

	public void setExceptions(List<DataException> exceptions) {
		this.exceptions = exceptions;
	}
	

}	
