package uk.gov.dwp.esf.mi.resources;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.time.LocalDate;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import uk.gov.dwp.esf.mi.common.RecordState;
import uk.gov.dwp.esf.mi.dtos.ParticipantDTO;

public class ParticipantResourceTest {
	
	private SimpleDateFormat dateFormat;
	
	@Before
	public void setup(){
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}
	
	@Test
	public void resourceTest() throws ParseException{
		ParticipantResource resource = new ParticipantResource();		
		assertNotNull(resource);
		resource.setParticipant(null);
		assertNull(resource.getParticipant());
		
		ParticipantDTO participant = new ParticipantDTO();
		participant.setParticipantId("56dffaa6097d9818d8b455a7");participant.setProviderId(1111111);
		participant.setContractId(4001115);participant.setNino("SJ1960589B");participant.setDob(dateFormat.parse("1982-12-16"));
		participant.setCreationDate(dateFormat.parse(dateFormat.format(new Date())));participant.setUpdatedDate(dateFormat.parse(dateFormat.format(new Date())));participant.setRecordState(RecordState.INCOMPLETE);
		participant.setAlcoholUser(false);
		resource = new ParticipantResource(participant);
		assertNotNull(resource);
	}

}

