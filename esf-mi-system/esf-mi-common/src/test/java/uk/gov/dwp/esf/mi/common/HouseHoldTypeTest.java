package uk.gov.dwp.esf.mi.common;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;


public class HouseHoldTypeTest {
	
	@Test
    public void assertHouseHoldTypeEnum() {        
        assertThat(HouseHoldType.JOBLESS_HOUSEHOLD, is(notNullValue()));
        assertThat(HouseHoldType.JOBLESS_HOUSEHOLD_DEP_CHILDREN, is(notNullValue()));
        assertThat(HouseHoldType.SINGLE_ADULT_DEP_CHILDREN, is(notNullValue()));
    }

}
