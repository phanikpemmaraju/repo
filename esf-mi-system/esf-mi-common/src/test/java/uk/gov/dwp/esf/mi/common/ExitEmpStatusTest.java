package uk.gov.dwp.esf.mi.common;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

public class ExitEmpStatusTest {
	
	@Test
    public void assertExitEmpStatusEnum() {        
        assertThat(ExitEmpStatus.INACTIVE, is(notNullValue()));
        assertThat(ExitEmpStatus.JOBSEEKING, is(notNullValue()));
        assertThat(ExitEmpStatus.TRAINING, is(notNullValue()));
    }

}
