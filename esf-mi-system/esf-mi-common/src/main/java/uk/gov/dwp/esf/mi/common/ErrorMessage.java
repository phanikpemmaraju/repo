package uk.gov.dwp.esf.mi.common;

//import java.util.ArrayList;
/*
 *  @Author : Phani Krishna
 *  @Description : ESFErrorMessage class for wrapping error details in an error object
 *  @Version : 1.0
 */


public class ErrorMessage {
	
	private String code;
	private String message;
	private String description;
		
	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public String getDescription() {
		return description;
	}
	
	private ErrorMessage(ErrorBuilder builder){		
		this.code = builder.code;
		this.message = builder.message;
		this.description = builder.description;
	}

	@Override
	public String toString() {
		return "ErrorMessage [code=" + code + ", message=" + message + ", description=" + description + "]";
	}

	public static class ErrorBuilder{
		private final String code;
		private final String message;
		private final String description;
		
		public ErrorBuilder(String code,String message,String description){
			this.code = code;
			this.message = message;
			this.description = description;
		}
		
		public ErrorMessage build() {
			ErrorMessage error = new ErrorMessage(this);
			return error;
		}
	}
}