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
	private String url;
		
	public String getUrl() {
		return url;
	}

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
		this.url = builder.url;
	}

	@Override
	public String toString() {
		return "ErrorMessage [code=" + code + ", message=" + message + ", description=" + description + ", url=" + url + "]";
	}

	public static class ErrorBuilder{
		private final String code;
		private final String message;
		private final String description;
		private final String url;
		
		public ErrorBuilder(String code,String message,String description,String url){
			this.code = code;
			this.message = message;
			this.description = description;
			this.url = url;
		}
		
		public ErrorMessage build() {
			ErrorMessage error = new ErrorMessage(this);
			return error;
		}
	}
}