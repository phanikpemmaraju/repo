package uk.gov.dwp.esf.mi.commons;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SearchCriteriaTest {
	
	private SearchCriteria criteria;
	
	@Before
	public void setup(){		
	}
	
	@Test
	public void SearchCriteriaDTOTest(){
		criteria = new SearchCriteria("dummyId","<>","1234,4567");
		assertNotNull(criteria);
		
		assertEquals("dummyId", criteria.getKey());
		assertEquals("<>", criteria.getOperation());
		assertEquals("1234,4567", criteria.getValue());
		
	}

}
