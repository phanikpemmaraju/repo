package uk.gov.dwp.esf.mi.resources;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import uk.gov.dwp.esf.mi.common.RecordState;
import uk.gov.dwp.esf.mi.dtos.ParticipantDTO;

public class ParticipantResourceTest {
	
	@Before
	public void setup(){
	}
	
	@Test
	public void resourceTest(){
		ParticipantResource resource = new ParticipantResource();		
		assertNotNull(resource);
		resource.setParticipant(null);
		assertNull(resource.getParticipant());
		
		ParticipantDTO participant = new ParticipantDTO();
		participant.setParticipantId("56dffaa6097d9818d8b455a7");participant.setProviderId(1111111);
		participant.setContractId(4001115);participant.setNino("SJ1960589B");participant.setDob(LocalDate.of(1982, 12, 16));
		participant.setCreationDate(LocalDate.now());participant.setUpdatedDate(LocalDate.now());participant.setRecordState(RecordState.INCOMPLETE);
		participant.setAlcoholUser(false);
		resource = new ParticipantResource(participant);
		assertNotNull(resource);
	}

}

