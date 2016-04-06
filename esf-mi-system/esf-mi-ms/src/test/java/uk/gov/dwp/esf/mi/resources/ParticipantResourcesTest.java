package uk.gov.dwp.esf.mi.resources;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.hateoas.Link;

import uk.gov.dwp.esf.mi.common.RecordState;
import uk.gov.dwp.esf.mi.dtos.ParticipantDTO;
import static org.hamcrest.Matchers.*;

public class ParticipantResourcesTest {
	
	private ParticipantResources participantResources;
	private SimpleDateFormat dateFormat;
	
	@Before
	public void setup(){
		participantResources = new ParticipantResources();
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}
	
	
	@Test
	public void resourcesTest() throws ParseException{
		Link selfLink = new Link("/participants/56dffaa6097d9818d8b455a7");
		List<ParticipantResource> participants = new ArrayList<>();
		List<Link> links = new ArrayList<>();
		ParticipantDTO participant = new ParticipantDTO();
		participant.setParticipantId("56dffaa6097d9818d8b455a7");participant.setProviderId(1111111);
		participant.setContractId(4001115);participant.setNino("SJ1960589B");participant.setDob(dateFormat.parse("1982-12-16"));
		participant.setCreationDate(dateFormat.parse(dateFormat.format(new Date())));participant.setUpdatedDate(dateFormat.parse(dateFormat.format(new Date())));participant.setRecordState(RecordState.INCOMPLETE);
		participant.setAlcoholUser(false);
		ParticipantResource resource = new ParticipantResource(participant);
		assertNotNull(resource);
		
		resource.add(new Link("/participants/56dffaa6097d9818d8b455a7","self"));
		participants.add(resource);
		
		links.add(new Link("/participants","self"));
		
		participantResources.setParticipants(participants);participantResources.setLinks(links);
		assertThat(participantResources.getLinks(), hasSize(1));
		assertThat(participantResources.getParticipants().get(0).getLink(Link.REL_SELF), is(selfLink));

	}

}
