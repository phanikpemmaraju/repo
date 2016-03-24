package uk.gov.dwp.esf.mi.common;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

public class WhiteTest {
	
	@Test
    public void assertWhiteEnum() {        
        assertThat(White.ENGLISH, is(notNullValue()));
        assertThat(White.WELSH, is(notNullValue()));
        assertThat(White.SCOTTISH, is(notNullValue()));
        assertThat(White.NORTHERS_IRISH, is(notNullValue()));
        assertThat(White.BRITISH, is(notNullValue()));
        // Just to instantiate for View class
        assertNotNull(new View());
    }

}
