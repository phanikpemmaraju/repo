package uk.gov.dwp.esf.mi.repositories;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mysema.query.types.Predicate;

import uk.gov.dwp.esf.mi.AbstractIntegrationTest;
import uk.gov.dwp.esf.mi.model.Participant;
import uk.gov.dwp.esf.mi.model.QParticipant;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class QuerydslParticipantRepositoryTest extends AbstractIntegrationTest{
	
	static final QParticipant participant = QParticipant.participant;

	@Autowired
	private ParticipantRepository participantRepository;

	@Test
	public void findParticipantsByQuerydslPredicate() {

		Participant esfParticipant = participantRepository.findOne(participant.nino.eq("SJ196058B").and(participant.contractId.eq(4001234)));
		assertThat(esfParticipant.getProviderId(), is(1111111));
		
		Predicate providers = participant.providerId.gt(1111112);

		Iterable<Participant> result = participantRepository.findAll(providers);
		assertThat(result,not(esfParticipant));
		
	}

}
