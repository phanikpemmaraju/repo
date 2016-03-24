package uk.gov.dwp.esf.mi.resources;

/*
 *  @Author : Phani Krishna
 *  @Description : A collection of ESFParticipantResource objects and a list of Spring hateoas links.
 *  @Version : 1.0
 * 
 */

import java.util.List;

import org.springframework.hateoas.Link;

public class ParticipantResources{
	
	private List<ParticipantResource> participants;
	
	private List<Link> links;

	public List<ParticipantResource> getParticipants() {
		return participants;
	}

	public void setParticipants(List<ParticipantResource> participants) {
		this.participants = participants;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	

}
