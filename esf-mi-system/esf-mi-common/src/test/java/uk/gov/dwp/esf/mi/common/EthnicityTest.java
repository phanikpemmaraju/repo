package uk.gov.dwp.esf.mi.common;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

public class EthnicityTest {
	
	@Test
    public void assertEthnicityEnum() {      
        assertThat(Ethnicity.BRITISH_AFRICAN_BLACK, is(notNullValue()));
        assertThat(Ethnicity.BRITISH_BANGLADESI, is(notNullValue()));
        assertThat(Ethnicity.BRITISH_CARIBBEAN_BLACK, is(notNullValue()));
        assertThat(Ethnicity.BRITISH_CHINESE_ASIAN, is(notNullValue()));
        assertThat(Ethnicity.BRITISH_INDIAN, is(notNullValue()));
        assertThat(Ethnicity.BRITISH_OTHER, is(notNullValue()));
        assertThat(Ethnicity.BRITISH_OTHER_BLACK, is(notNullValue()));
        assertThat(Ethnicity.BRITISH_PAKISTANI, is(notNullValue()));
        
        assertThat(Ethnicity.MIXED_WHITE_BLACK_AFRICAN, is(notNullValue()));
        assertThat(Ethnicity.MIXED_WHITE_BLACK_CARIBBEAN, is(notNullValue()));
        
        assertThat(Ethnicity.OTHER_ASIAN, is(notNullValue()));
        assertThat(Ethnicity.OTHER_ETHNIC, is(notNullValue()));
        assertThat(Ethnicity.OTHER_ETHNIC_ARAB, is(notNullValue()));
        
        assertThat(Ethnicity.WHITE_ASIAN, is(notNullValue()));
        assertThat(Ethnicity.WHITE_BRITISH, is(notNullValue()));
        assertThat(Ethnicity.WHITE_ENGLISH, is(notNullValue()));
        assertThat(Ethnicity.WHITE_GYPSY, is(notNullValue()));
        assertThat(Ethnicity.WHITE_IRISH, is(notNullValue()));
        assertThat(Ethnicity.WHITE_IRISH_TRAVELLER, is(notNullValue()));
        assertThat(Ethnicity.WHITE_NORTHERN_IRISH, is(notNullValue()));
        assertThat(Ethnicity.WHITE_OTHER, is(notNullValue()));
        assertThat(Ethnicity.WHITE_SCOTTISH, is(notNullValue()));
        assertThat(Ethnicity.WHITE_WELSH, is(notNullValue()));
    }

}
