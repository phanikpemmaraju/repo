package uk.gov.dwp.esf.mi.exceptions;

public class DataException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String code;
	private String message;
	
	public DataException(){		
	}
	
	public DataException(String code,String message){
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
