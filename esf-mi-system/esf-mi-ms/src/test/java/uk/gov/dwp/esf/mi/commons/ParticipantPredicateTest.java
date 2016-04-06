package uk.gov.dwp.esf.mi.commons;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import com.mysema.query.types.expr.BooleanExpression;

public class ParticipantPredicateTest {
	
	private ParticipantPredicate predicate;
	
	@Before
	public void setUp(){
		predicate = new ParticipantPredicate();
	}
	
	
	@Test
	public void testGetPredicate(){
		SearchCriteria criteria = new SearchCriteria("dummyId","=","1234,4567");
		predicate.setCriteria(criteria);
		
		BooleanExpression expression = predicate.getPredicate();
		assertNotNull(expression);
		assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.dummyId = 1234 || participant.dummyId = 4567");
		
		criteria = new SearchCriteria("dummyId","!=","1234"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria);
		expression = predicate.getPredicate();
		assertNotNull(expression);
		assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.dummyId != 1234");
		
		criteria = new SearchCriteria("dummyId","!!=","1234"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria);
		expression = predicate.getPredicate();
		assertNull(expression);		
		
		criteria = new SearchCriteria("dummyId","!=","1234,4567"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria);
		expression = predicate.getPredicate();
		assertNotNull(expression);
		assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.dummyId != 1234 && participant.dummyId != 4567");
		
		criteria = new SearchCriteria("dummyId","<>","1234,4567"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria);
		expression = predicate.getPredicate();
		assertNotNull(expression);
				
		criteria = new SearchCriteria("participantId","!=","569e0fed07d46fd2f7fc525a,569e0fed07d46fd2f7fc525b"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria);
		expression = predicate.getPredicate();
		assertNotNull(expression);
		assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.participantId != 569e0fed07d46fd2f7fc525a && participant.participantId != 569e0fed07d46fd2f7fc525b");
		
		criteria = new SearchCriteria("participantId","<>","569e0fed07d46fd2f7fc525a,569e0fed07d46fd2f7fc525b"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria);
		expression = predicate.getPredicate();
		assertNotNull(expression);
		
		criteria = new SearchCriteria("participantId","!=","569e0fed07d46fd2f7fc525a"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria);
		expression = predicate.getPredicate();
		assertNotNull(expression);
		assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.participantId != 569e0fed07d46fd2f7fc525a");
		
		criteria = new SearchCriteria("providerId",">","1111111"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria);
		expression = predicate.getPredicate();
		assertNotNull(expression);
		assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.providerId > 1111111");
		
		criteria = new SearchCriteria("providerId",">=","1111111"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria);
		expression = predicate.getPredicate();
		assertNotNull(expression);
		assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.providerId >= 1111111");

		criteria = new SearchCriteria("providerId","_gt","1111111"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria);
		expression = predicate.getPredicate();
		assertNotNull(expression);
		assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.providerId > 1111111");
		
		criteria = new SearchCriteria("providerId","_gt=","1111111"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria);
		expression = predicate.getPredicate();
		assertNotNull(expression);
		assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.providerId >= 1111111");
		
		criteria = new SearchCriteria("providerId","<","1111111"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria);
		expression = predicate.getPredicate();
		assertNotNull(expression);
		assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.providerId < 1111111");

		criteria = new SearchCriteria("providerId","<=","1111111"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria);
		expression = predicate.getPredicate();
		assertNotNull(expression);
		assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.providerId <= 1111111");

		criteria = new SearchCriteria("providerId","_lt","1111111"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria);
		expression = predicate.getPredicate();
		assertNotNull(expression);
		assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.providerId < 1111111");
		
		criteria = new SearchCriteria("providerId","_lt=","1111111"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria);
		expression = predicate.getPredicate();
		assertNotNull(expression);
		assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.providerId <= 1111111");
		
		criteria = new SearchCriteria("fundingAggrement","_like=","funds"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria);
		expression = predicate.getPredicate();
		assertNotNull(expression);
		assertEquals("failure - expected are actual are not equal", expression.toString() , "contains(participant.fundingAggrement,funds)");
		
		criteria = new SearchCriteria("providerId","_in=","1111111,1111112"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria);
		expression = predicate.getPredicate();
		assertNotNull(expression);
		assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.providerId = 1111111 || participant.providerId = 1111112");
		
		criteria = new SearchCriteria("gender","_in=","MALE,FEMALE"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria);
		expression = predicate.getPredicate();
		assertNotNull(expression);
		assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.gender = MALE || participant.gender = FEMALE");
		
		criteria = new SearchCriteria("gender","_any=","MALE,FEMALE"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria);
		expression = predicate.getPredicate();
		assertNull(expression);
		
		criteria = new SearchCriteria("gender","!=","MALE"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria);
		expression = predicate.getPredicate();
		assertNotNull(expression);
		assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.gender != MALE");

		
		criteria = new SearchCriteria("gender","<>","MALE"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria);
		expression = predicate.getPredicate();
		assertNotNull(expression);
		assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.gender != MALE");
		
		
		criteria = new SearchCriteria("creationDate","_between=","1991-01-12:2011-11-04"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria);
		expression = predicate.getPredicate();
		assertNotNull(expression);
		//assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.creationDate between 1991-01-12 and 2011-11-04");
		assertNotNull(predicate.toString());
		
	}
	
	
}
