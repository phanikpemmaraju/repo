package uk.gov.dwp.esf.mi.seed;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import uk.gov.dwp.esf.mi.common.Ethnicity;
import uk.gov.dwp.esf.mi.common.RecordState;
import uk.gov.dwp.esf.mi.model.Participant;
import uk.gov.dwp.esf.mi.model.Provider;

public class ESFDataSeeder {	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MongoTemplate mongoTemplate = new MongoTemplate(new MongoClient("localhost"),"esf-mi");
		
		
		final List<Participant> participantList = new ArrayList<>();
    	
    	final Participant participant1 = new Participant.ParticipantBuilder(null, 1111111, "SL123457B",LocalDate.of(1983, 01, 22),4001111)
    			.creationDate(LocalDate.of(1991, 05, 12)).updatedDate(LocalDate.now()).recordState(RecordState.INCOMPLETE).ethnicity(Ethnicity.WHITE_ENGLISH)
				.build();
    	
    	final Participant participant2 = new Participant.ParticipantBuilder(null, 1111111, "SL123456A",LocalDate.of(1993, 05, 27),4001111)
    			.creationDate(LocalDate.of(1979, 12, 16)).updatedDate(LocalDate.now()).recordState(RecordState.INCOMPLETE).build();
    	
    	final Participant participant3 = new Participant.ParticipantBuilder(null, 1111111, "AB123456A",LocalDate.of(1990, 9, 9),4001113)
    			.creationDate(LocalDate.of(1985, 01, 17)).updatedDate(LocalDate.now()).recordState(RecordState.INCOMPLETE).build();
    	    	
    	
    	final List<Participant> participantList1 = new ArrayList<>();
    	
    	final Participant participant4 = new Participant.ParticipantBuilder(null, 1111111, "AB123458A",LocalDate.of(1991, 07, 15),4001112)
    			.creationDate(LocalDate.of(1987, 9, 05)).updatedDate(LocalDate.now()).recordState(RecordState.COMPLETE).build();
    	
    	
    	participantList1.add(participant4);
    	
    	participantList.add(participant1);
    	participantList.add(participant2);
    	participantList.add(participant3);
    	
    	final List<Provider> providerList = new ArrayList<>();
    	
    	final Provider provider1 = new Provider();
    	provider1.setProviderId(1111111);provider1.setProviderName("the training provider company ltd");
    	final Provider provider2 = new Provider();
    	provider2.setProviderId(1111112);provider2.setProviderName("another provider ltd");
    	final Provider provider3 = new Provider();
    	provider3.setProviderId(1111113);provider3.setProviderName("another provider ltd");
    	provider1.setParticipants(participantList); provider3.setParticipants(participantList1);
    	providerList.add(provider1);providerList.add(provider2);providerList.add(provider3);
    	    	    	
    	mongoTemplate.insert(participant1);mongoTemplate.insert(participant2);mongoTemplate.insert(participant3);mongoTemplate.insert(participant4);
    	mongoTemplate.insert(provider1);mongoTemplate.insert(provider2);mongoTemplate.insert(provider3);
		
	}

}
