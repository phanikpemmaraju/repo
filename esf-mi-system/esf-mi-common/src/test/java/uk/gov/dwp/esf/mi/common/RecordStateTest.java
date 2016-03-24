package uk.gov.dwp.esf.mi.common;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class RecordStateTest {
	
	@Test
    public void assertRecordStateEnum() {        
        assertThat(RecordState.INCOMPLETE, is(notNullValue()));
        assertThat(RecordState.COMPLETE, is(notNullValue()));
        assertThat(RecordState.ASSERTED, is(notNullValue()));
        assertThat(RecordState.VALIDATED, is(notNullValue()));
        assertThat(RecordState.STARTED, is(notNullValue()));
        assertThat(RecordState.ENDED, is(notNullValue()));
    }

}
