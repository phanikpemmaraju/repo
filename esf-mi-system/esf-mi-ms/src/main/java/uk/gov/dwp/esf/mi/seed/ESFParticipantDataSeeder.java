package uk.gov.dwp.esf.mi.seed;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uk.gov.dwp.esf.mi.common.Ethnicity;
import uk.gov.dwp.esf.mi.common.RecordState;
import uk.gov.dwp.esf.mi.model.Participant;
import uk.gov.dwp.esf.mi.model.Provider;

public class ESFParticipantDataSeeder {	

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		MongoTemplate mongoTemplate = new MongoTemplate(new MongoClient("localhost"),"esf-mi");		
		
		final List<Participant> participantList = new ArrayList<>();
    	
    	final Participant participant1 = new Participant.ParticipantBuilder(null, 1111111, "SL123457B",dateFormat.parse("1983-01-22"),4001111)
    			.creationDate(dateFormat.parse("1991-05-12")).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.INCOMPLETE).ethnicity(Ethnicity.WHITE_ENGLISH)
				.build();
    	
    	final Participant participant2 = new Participant.ParticipantBuilder(null, 1111111, "SL123456A",dateFormat.parse("1993-05-27"),4001111)
    			.creationDate(dateFormat.parse("1979-12-16")).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.INCOMPLETE).build();
    	
    	final Participant participant3 = new Participant.ParticipantBuilder(null, 1111111, "AB123456A",dateFormat.parse("1990-09-09"),4001113)
    			.creationDate(dateFormat.parse("1985-01-17")).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.INCOMPLETE).build();
    	    	
    	
    	final List<Participant> participantList1 = new ArrayList<>(); 
    	
    	final Participant participant4 = new Participant.ParticipantBuilder(null, 1111111, "AB123458A",dateFormat.parse("1991-07-15"),4001112)
    			.creationDate(dateFormat.parse("1987-09-05")).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.COMPLETE).build();
    	
    	
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
