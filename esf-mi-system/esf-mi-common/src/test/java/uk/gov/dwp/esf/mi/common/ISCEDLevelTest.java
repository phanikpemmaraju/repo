package uk.gov.dwp.esf.mi.common;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

public class ISCEDLevelTest {
	
	@Test
    public void assertISCEDLevelEnum() {      
		ISCEDLevel zeroLevel = ISCEDLevel.ZERO;		
        assertEquals(zeroLevel.getValue(),0);
        assertThat(ISCEDLevel.ONE, is(notNullValue()));
        assertThat(ISCEDLevel.TWO, is(notNullValue()));
        assertThat(ISCEDLevel.THREE, is(notNullValue()));
        assertThat(ISCEDLevel.FOUR, is(notNullValue()));
        assertThat(ISCEDLevel.FIVE, is(notNullValue()));
        assertThat(ISCEDLevel.SIX, is(notNullValue()));
        assertThat(ISCEDLevel.SEVEN, is(notNullValue()));
        assertThat(ISCEDLevel.EIGHT, is(notNullValue()));
    }

}
