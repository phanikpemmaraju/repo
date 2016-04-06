package uk.gov.dwp.esf.mi.repositories;

import com.google.common.collect.Iterators;
import com.mysema.query.types.Predicate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uk.gov.dwp.esf.mi.AbstractIntegrationTest;
import uk.gov.dwp.esf.mi.model.Participant;
import uk.gov.dwp.esf.mi.model.QParticipant;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class QuerydslParticipantRepositoryTest extends AbstractIntegrationTest{
	
	static final QParticipant participant = QParticipant.participant;

	@Autowired
	private ParticipantRepository participantRepository;

	@Test
	public void findParticipantsByQuerydslPredicate() throws Exception {

		Participant esfParticipant = participantRepository.findOne(participant.nino.eq("SJ196058B").and(participant.contractId.eq(4001234)));
		assertThat(esfParticipant.getProviderId(), is(1111111));
		
		Predicate providers = participant.providerId.gt(1111112);

		Iterable<Participant> result = participantRepository.findAll(providers);
		assertThat(result,not(esfParticipant));
		
		// Find Data between two dates
		final Date from = dateFormat.parse("1977-01-27");
		final Date to = dateFormat.parse("1985-04-10");
		final Iterable<Participant> participants = participantRepository.findAll(participant.dob.between(from, to));
		assertNotNull(participants);
		assertThat(Iterators.size(participants.iterator()),is(3));
		
	}

}
