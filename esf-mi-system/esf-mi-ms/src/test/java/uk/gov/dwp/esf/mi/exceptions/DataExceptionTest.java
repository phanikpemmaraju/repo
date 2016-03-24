package uk.gov.dwp.esf.mi.exceptions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class DataExceptionTest {
	
	private DataException dataException;
	
	@Before
	public void setup(){
		dataException = new DataException();
	}
	
	
	@Test
	public void dataExceptionTest(){
		assertNotNull(dataException);
		assertNull(dataException.getCode());
		assertNull(dataException.getMessage());
		
		dataException = new DataException("error.nino.not.found","Nino SJ196058B not found");
		assertNotNull(dataException);		
		assertThat(dataException.getCode(), is("error.nino.not.found"));
		assertThat(dataException.getMessage(), is("Nino SJ196058B not found"));		
		
	}

}
