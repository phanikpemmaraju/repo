package uk.gov.dwp.esf.mi.resources;

/*
 *  @Author : Phani Krishna
 *  @Description : ESFParticipantResource uses the Spring HATEOAS Resource Support for
 *  			   providing Hyper Links/Hyper Media formats
 *  @Version : 1.0
 * 
 */

import uk.gov.dwp.esf.mi.dtos.ParticipantDTO;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

@Relation(value="participant", collectionRelation="participants")
public class ParticipantResource extends ResourceSupport {
	
	public ParticipantResource(){		
	}
	
	private ParticipantDTO participant;
	
	public ParticipantResource(ParticipantDTO participant){	
		this.participant = participant;
	}

	public ParticipantDTO getParticipant() {
		return participant;
	}

	public void setParticipant(ParticipantDTO participant) {
		this.participant = participant;
	}

	@Override
	public String toString() {
		return "ParticipantResource [participant=" + participant + "]";
	}

}
