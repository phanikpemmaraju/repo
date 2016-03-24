package uk.gov.dwp.esf.mi.assemblers;

/*
 *  @Author : Phani Krishna
 *  @Description :  A Helper class which extends Spring Hateoas ResourceAssemblerSupport class to reduce 
 *  				the amount of code needed to write for mapping from ESFParticipant entity
 *                  to a resource type and adding respective links.
 *  @Version : 1.0
 * 
 */

import java.util.ArrayList;
import java.util.List;

import uk.gov.dwp.esf.mi.controllers.ParticipantController;
import uk.gov.dwp.esf.mi.dtos.ParticipantDTO;
import uk.gov.dwp.esf.mi.model.Participant;
import uk.gov.dwp.esf.mi.resources.ParticipantResource;
import uk.gov.dwp.esf.mi.resources.ParticipantResources;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

@Component
public class ParticipantResourceAssembler extends ResourceAssemblerSupport<ParticipantDTO, ParticipantResource>{	
		
	public ParticipantResourceAssembler() {
		super(ParticipantController.class, ParticipantResource.class);
	}
	
	/*
	 * Overridden default method to generate a ESFParticipant Resource object with a Self link for the participant Id.
	 *  
	 */
	
	@Override
	public ParticipantResource toResource(ParticipantDTO participant) {
		ParticipantResource participantResource = new ParticipantResource(participant);		
		// The below will add a link with href for pointing to provider's provider Id.		
		participantResource.add(new Link("/participants/"+ participant.getParticipantId(), "self"));
		return participantResource;
	}
	
	
	/*
	 * Method to generate a collection of ESFParticipant Resource objects along with 
	 * Self,Previous and Next links to the Pageable collection.
	 *  
	 */
	public List<ParticipantResource> toResourcesForAParticipant(ModelMapper modelMapper,Page<Participant> participants,final String requestURI) {
		
		final List<ParticipantResource> participantResources = new ArrayList<>();
		for(Participant participant:participants.getContent()){
			ParticipantDTO participantDTO = modelMapper.map(participant, ParticipantDTO.class);
			participantResources.add(new ParticipantResource(participantDTO));
		}				
		participantResources.forEach(participantResource -> participantResource.add(new Link(requestURI , "self")));				
		return participantResources;
		
	}	
	
	
	/*
	 * Method to generate a collection of ESFParticipant Resource objects along with 
	 * Self,Previous and Next links to the Pageable collection.
	 *  
	 */	
	public List<ParticipantResource> toResourcesForAParticipant(ModelMapper modelMapper,ParticipantDTO participant,final String participantId,final String requestURI) {		
		final List<ParticipantResource> participantResources = new ArrayList<>();
		participant.setParticipantId(participantId);
		participantResources.add(new ParticipantResource(participant));				
		participantResources.forEach(participantResource -> participantResource.add(new Link(requestURI +  "/" + participant.getParticipantId(), "self")));				
		return participantResources;
		
	}
	
	/*
	 * Method to generate a collection of ESFParticipant Resource objects along with 
	 * Self,Previous and Next links to the Pageable collection.
	 *  
	 */	
	public ParticipantResources toResourcesForParticipants(ModelMapper modelMapper,Page<Participant> participants,Pageable pageable,final String requestURI) {
		
		final List<ParticipantResource> participantResourcesList = new ArrayList<>();
		for(Participant participant:participants.getContent()){
			participantResourcesList.add(toResource(modelMapper.map(participant, ParticipantDTO.class)));
		}
		final List<Link> links = new ArrayList<>();
		StringBuilder builder= new StringBuilder();
				
		// Create the Pageable Self Link		
		if(pageable != null){
			builder.append(requestURI);
			builder.append("?page="); builder.append(pageable.getPageNumber());
			builder.append("&offset="); builder.append(pageable.getOffset());
			builder.append("&size="); builder.append(pageable.getPageSize());
			
			if(pageable.getSort() != null){
				builder.append("&sort=");
				builder.append(pageable.getSort().toString());
			}
		}
		
		if(builder.toString().trim().length() > 1)
			links.add(new Link(builder.toString() , "self"));
		
		builder.setLength(0); builder = new StringBuilder();
		
		
		// Create the Pageable Next Link
		if(participants.hasNext()){		
			builder.append(requestURI);			
			builder.append("?page="); builder.append(participants.nextPageable().getPageNumber());
			builder.append("&offset="); builder.append(participants.nextPageable().getOffset());
			builder.append("&size="); builder.append(participants.nextPageable().getPageSize());
			
			if(pageable != null && pageable.getSort() != null){
				builder.append("&sort=");
				builder.append(pageable.getSort().toString());
			}
		}
		
		if(builder.toString().trim().length() > 1)
			links.add(new Link(builder.toString() , "participants.next"));
		
		builder.setLength(0); builder = new StringBuilder();
		
		// Create the Pageable Previous Link
		if(participants.hasPrevious()){
			builder.append(requestURI);
			builder.append("?page="); builder.append(participants.previousPageable().getPageNumber());
			builder.append("&offset="); builder.append(participants.previousPageable().getOffset());
			builder.append("&size="); builder.append(participants.previousPageable().getPageSize());
			
			if(pageable != null && pageable.getSort() != null){
				builder.append("&sort=");
				builder.append(pageable.getSort().toString());
			}
		}		
		
		if(builder.toString().trim().length() > 1)
			links.add(new Link(builder.toString() , "participants.previous"));
							
		ParticipantResources participantResources = new ParticipantResources();
		participantResources.setLinks(links);participantResources.setParticipants(participantResourcesList);
		
		return participantResources;
		
	}
	
	
	/*
	 * Overridden default method to create a new instance of ESFParticipant Resource object for a given ESFParticipant entity object.
	 *  
	 */
	@Override
	protected ParticipantResource instantiateResource(ParticipantDTO participant) {
		// TODO Auto-generated method stub
		return new ParticipantResource(participant);
	}

	
}
