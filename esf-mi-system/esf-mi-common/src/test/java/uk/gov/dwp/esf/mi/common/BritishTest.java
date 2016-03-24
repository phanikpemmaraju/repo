package uk.gov.dwp.esf.mi.common;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.hamcrest.Matchers.*;

public class BritishTest {
	
	@Test
    public void assertBritishEnum() {        
        assertThat(British.INDIAN_ASIAN, is(notNullValue()));
        assertThat(British.AFRICAN_BLACK, is(notNullValue()));
        assertThat(British.BANGLADESI_ASIAN, is(notNullValue()));
        assertThat(British.CARIBBEAN_BLACK, is(notNullValue()));
        assertThat(British.CHINESE_ASIAN, is(notNullValue()));
        assertThat(British.PAKISTANI_ASIAN, is(notNullValue()));
        assertThat(British.OTHER_BLACK, is(notNullValue()));
        assertThat(British.OTHER, is(notNullValue()));
    }

}
