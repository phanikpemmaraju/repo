package uk.gov.dwp.esf.mi.assemblers;

import uk.gov.dwp.esf.mi.common.RecordState;
import uk.gov.dwp.esf.mi.dtos.ParticipantDTO;
import uk.gov.dwp.esf.mi.model.Participant;
import uk.gov.dwp.esf.mi.resources.ParticipantResource;
import uk.gov.dwp.esf.mi.resources.ParticipantResources;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ParticipantResourceAssemblerTest {
	
	@InjectMocks
	private ParticipantResourceAssembler participantResourceAssembler;
	
	@Mock
	private Page<Participant> participants;
	
	private ModelMapper modelMapper;
	private SimpleDateFormat dateFormat;
	
	@Before
	public void setUp(){
		modelMapper = new ModelMapper();
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}
	
	/*
     * Test toResource ResourceAssembler method
     * A self link is also populated. 
   	*/
	@Test
	public void testToResource() throws ParseException{
		
		Participant participant = new Participant.ParticipantBuilder("56dffaa6097d9818d8b455a7", 1111111, "SJ196058B", dateFormat.parse("1982-12-16"), 4001115)
				.creationDate(dateFormat.parse(dateFormat.format(new Date()))).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.INCOMPLETE).build();
    	
		ParticipantResource participantResource = participantResourceAssembler.toResource(modelMapper.map(participant, ParticipantDTO.class));				
		assertThat(participantResource.getLinks().get(0).getHref(),is("/participants/56dffaa6097d9818d8b455a7"));
		assertThat(participantResource.getParticipant().getParticipantId(),is("56dffaa6097d9818d8b455a7"));
		
		participantResource = participantResourceAssembler.instantiateResource(modelMapper.map(participant, ParticipantDTO.class));
		assertNotNull(participantResource);
		assertThat(participantResource.getParticipant().getParticipantId(),is("56dffaa6097d9818d8b455a7"));
		assertThat(participantResource.getParticipant().getProviderId(),is(1111111));
	}
	
	
	/*
     * Test toResourcesForAParticipant ResourceAssembler method
     * A self link is also populated. 
   	*/
	@Test
	public void testToResourcesForAParticipant() throws ParseException{
		final Participant participant = new Participant.ParticipantBuilder("56dffaa6097d9818d8b455a7", 1111111, "SL123457B", dateFormat.parse(dateFormat.format(new Date())),4001115)
				.creationDate(dateFormat.parse(dateFormat.format(new Date()))).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.INCOMPLETE).build();
    	
		final List<Participant> participantList = new ArrayList<>();
		participantList.add(participant);		
		final Page<Participant> participants = new PageImpl<>(participantList);
		
		List<ParticipantResource> participantResources = participantResourceAssembler.toResourcesForAParticipant(modelMapper,participants, "/providers/1111111/participants/56dffaa6097d9818d8b455a7");
		assertNotNull(participantResources);
		assertThat(participantResources.size(),is(1));
		assertThat(participantResources.get(0).getLinks().get(0).getHref(),is("/providers/1111111/participants/56dffaa6097d9818d8b455a7"));
		
		participantResources = participantResourceAssembler.toResourcesForAParticipant(modelMapper,participants, "/providers/1111111/participants/56dffaa6097d9818d8b455a7");
		assertNotNull(participantResources);
		assertThat(participantResources.size(),is(1));
		assertThat(participantResources.get(0).getLinks().get(0).getHref(),is("/providers/1111111/participants/56dffaa6097d9818d8b455a7"));
	}
	
	
	/*
     * Test toResourcesForAParticipant ResourceAssembler method
     * A self link is also populated. 
   	*/
	@Test
	public void testToResourcesForAParticipantSingle() throws ParseException{
		final ParticipantDTO participant = new ParticipantDTO();
		participant.setParticipantId("56dffaa6097d9818d8b455a7");participant.setProviderId(1111111);
		participant.setContractId(4001115);participant.setNino("SJ1960589B");participant.setDob(dateFormat.parse("1982-12-16"));
		participant.setCreationDate(dateFormat.parse(dateFormat.format(new Date())));participant.setUpdatedDate(dateFormat.parse(dateFormat.format(new Date())));participant.setRecordState(RecordState.INCOMPLETE);

		List<ParticipantResource> participantResources = participantResourceAssembler.toResourcesForAParticipant(modelMapper,participant,participant.getParticipantId(), "/providers/1111111/participants");
		assertNotNull(participantResources);
		assertThat(participantResources.size(),is(1));
		assertThat(participantResources.get(0).getLinks().get(0).getHref(),is("/providers/1111111/participants/56dffaa6097d9818d8b455a7"));
		
		participantResources = participantResourceAssembler.toResourcesForAParticipant(modelMapper,participant,participant.getParticipantId(), "/providers/1111111/participants");
		assertNotNull(participantResources);
		assertThat(participantResources.size(),is(1));
		assertThat(participantResources.get(0).getLinks().get(0).getHref(),is("/providers/1111111/participants/56dffaa6097d9818d8b455a7"));
	}
	
	
		
	/*
     * Test toResourcesForParticipants ResourceAssembler method
     * A self link is also populated. 
   	*/
	@Test
	public void testToResourcesForParticipants() throws ParseException{
		Pageable pageable = new PageRequest(0,1,Direction.ASC,"providerId");
		
		final Participant participant1 = new Participant.ParticipantBuilder(null, 1111111, "SJ196058B", dateFormat.parse("1982-12-16"), 4001115)
				.creationDate(dateFormat.parse(dateFormat.format(new Date()))).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.INCOMPLETE).build();
    	
		final Participant participant2 = new Participant.ParticipantBuilder(null, 1111111, "AB196058B", dateFormat.parse("1979-08-07"), 4001116)
				.creationDate(dateFormat.parse(dateFormat.format(new Date()))).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.INCOMPLETE).build();
    	
		final Participant participant3 = new Participant.ParticipantBuilder(null, 1111111, "SL123457B", dateFormat.parse("1991-05-10"),4001112)
				.creationDate(dateFormat.parse(dateFormat.format(new Date()))).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.INCOMPLETE).build();

		final List<Participant> participantList = new ArrayList<>();
		participantList.add(participant1);	participantList.add(participant2);participantList.add(participant3);	
		
		when(participants.getContent()).thenReturn(participantList);
		when(participants.hasNext()).thenReturn(true);
		when(participants.nextPageable()).thenReturn(new PageRequest(1,1,Direction.ASC,"providerId"));
				
		ParticipantResources participantResources = participantResourceAssembler.toResourcesForParticipants(modelMapper,participants, pageable, "/providers/1111111/participants");
		assertNotNull(participantResources);
		
		participantResources = participantResourceAssembler.toResourcesForParticipants(modelMapper,participants, null, "/providers/1111111/participants");
		assertNotNull(participantResources);
		
		pageable = new PageRequest(1,1);
		when(participants.hasPrevious()).thenReturn(true);
		when(participants.previousPageable()).thenReturn(new PageRequest(0,1,Direction.ASC,"providerId"));
		participantResources = participantResourceAssembler.toResourcesForParticipants(modelMapper,participants, pageable, "/providers/1111111/participants");
		assertNotNull(participantResources);
		
		pageable = new PageRequest(0,1);
		participantResources = participantResourceAssembler.toResourcesForParticipants(modelMapper,participants, pageable, "/providers/1111111/participants");
		assertNotNull(participantResources);
		
		pageable = null;
		participantResources = participantResourceAssembler.toResourcesForParticipants(modelMapper,participants, pageable, "/providers/1111111/participants");
		assertNotNull(participantResources);
	}

}
