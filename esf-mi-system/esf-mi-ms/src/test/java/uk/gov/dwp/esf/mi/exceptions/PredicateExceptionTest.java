package uk.gov.dwp.esf.mi.exceptions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PredicateExceptionTest {
	
	private PredicateException predicateException;
	
	@Before
	public void setup(){
		predicateException = new PredicateException();
	}
	
	
	@Test
	public void predicateExceptionTest(){
		final List<DataException> exceptions = new ArrayList<>();
		assertNotNull(predicateException);
		assertNull(predicateException.getCode());
		assertNull(predicateException.getMessage());
		
		predicateException = new PredicateException("error.nino.not.found","field nino1 doesn't exist");
		assertNotNull(predicateException);		
		assertThat(predicateException.getCode(), is("error.nino.not.found"));
		assertThat(predicateException.getMessage(), is("field nino1 doesn't exist"));
		
		DataException dataException = new DataException("error.nino.not.found","field dob1 not found");
		exceptions.add(dataException);exceptions.add(predicateException);
		
		predicateException.setExceptions(exceptions);
		assertNotNull(predicateException.getExceptions());		
		assertThat(predicateException.getExceptions().size(), is(2));
		
	}

}
