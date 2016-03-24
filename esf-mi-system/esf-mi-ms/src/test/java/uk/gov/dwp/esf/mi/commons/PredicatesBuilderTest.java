package uk.gov.dwp.esf.mi.commons;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.mysema.query.types.expr.BooleanExpression;

import uk.gov.dwp.esf.mi.exceptions.PredicateException;

public class PredicatesBuilderTest {
	
	private PredicatesBuilder builder;
	
	@Before
	public void setUp(){
		builder = new PredicatesBuilder();
	}
	
	@Test
	public void testCreatePredicateBuilder() throws PredicateException{
		String filter = "providerId=3456&participantId=569e0fed07d46fd2f7fc525a";
		BooleanExpression expression = builder.createPredicateBuilder(filter);
		
		assertNotNull(expression);
		assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.providerId = 3456 && participant.participantId = 569e0fed07d46fd2f7fc525a");
		
		filter = "providerId=3456,5146&participantId=569e0fed07d46fd2f7fc525a";
		expression = builder.createPredicateBuilder(filter);
		assertNotNull(expression);
		assertEquals("failure - expected are actual are not equal", expression.toString() , "(participant.providerId = 3456 || participant.providerId = 5146) && participant.participantId = 569e0fed07d46fd2f7fc525a");		
				
	}
	
	@Test(expected=PredicateException.class)
	public void testCreatePredicateBuilderException() throws PredicateException{
		final String filter = "dummyIdabc=3456&participantId=569e0fed07d46fd2f7fc525a";
		BooleanExpression expression = builder.createPredicateBuilder(filter);		
		assertNull(expression);				
	}

}
