package uk.gov.dwp.esf.mi.common;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.hamcrest.Matchers.*;

public class EntryEmpStatusTest {
	
	@Test
    public void assertEntryEmpStatusEnum() {        
        assertThat(EntryEmpStatus.INACTIVE, is(notNullValue()));
        assertThat(EntryEmpStatus.JOBSEEKING, is(notNullValue()));
    }

}
