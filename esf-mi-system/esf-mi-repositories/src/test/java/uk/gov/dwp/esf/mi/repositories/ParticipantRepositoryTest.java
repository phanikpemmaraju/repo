package uk.gov.dwp.esf.mi.repositories;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import uk.gov.dwp.esf.mi.repositories.ParticipantRepository;

import uk.gov.dwp.esf.mi.AbstractIntegrationTest;
import uk.gov.dwp.esf.mi.model.Participant;

public class ParticipantRepositoryTest extends AbstractIntegrationTest {
	
	final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private ParticipantRepository participantRepository;
	
	@Test
	public void createParticipant()  throws ParseException {
		Participant participant = new Participant.ParticipantBuilder("56dffaa6097d9818d8b455a7", 1111111, "SJ196058B", dateFormat.parse("1982-12-16"), 4001115)
				  .build();
		participant = participantRepository.save(participant);
	}
	
	@Test
	public void findByProviderIdTest(){
		Pageable pageable = new PageRequest(0, 1, Direction.DESC, "providerId");
		Page<Participant> page = participantRepository.findByProviderId(1111111, pageable);

		assertThat(page.getContent(), hasSize(1));
		assertThat(page.getContent().get(0).getNino(), is("SJ196058B"));
		assertThat(page.getContent().get(0).getContractId(), is(4001234));
		assertThat(page.getContent(), hasSize(1));
		assertThat(page.isFirst(), is(true));
		assertThat(page.isLast(), is(true));
		assertThat(page.hasNext(), is(false));		
	}
	
	@Test
	public void findByProviderIdAndParticipantIdTest() throws ParseException{
		Pageable pageable = new PageRequest(0, 1, Direction.DESC, "providerId");
		Page<Participant> page = participantRepository.findByProviderIdAndParticipantId(1111112,"56e14abf710856264052cc14", pageable);

		assertThat(page.getContent(), hasSize(1));
		assertThat(page.getContent().get(0).getNino(), is("EF984361C"));
		assertThat(page.getContent().get(0).getContractId(), is(4009832));
		assertThat(page.getContent().get(0).getDob(), is(dateFormat.parse("1985-03-22")));
		assertThat(page.isLast(), is(true));
		
		page = participantRepository.findByProviderIdAndParticipantId(1111119,"56e14abf710856264052cc14", pageable);
		assertThat(page.getContent(), hasSize(0));
	}
	
	@Test
	public void findByParticipantIdTest() throws ParseException{
		Pageable pageable = new PageRequest(0, 1, Direction.DESC, "providerId");
		Page<Participant> page = participantRepository.findByParticipantId("56e14abf710856264052cc14", pageable);

		assertThat(page.getContent(), hasSize(1));
		assertThat(page.getContent().get(0).getProviderId(),is(1111112));
		assertThat(page.getContent().get(0).getNino(), is("EF984361C"));
		assertThat(page.getContent().get(0).getContractId(), is(4009832));
		assertThat(page.getContent().get(0).getDob(), is(dateFormat.parse("1985-03-22")));
		assertThat(page.isLast(), is(true));
		
		page = participantRepository.findByParticipantId("56e14abf710856264052cc15", pageable);
		assertThat(page.getContent(), hasSize(0));
	}
	
	
	@Test
	public void findByContractIdTest() throws ParseException{
		Pageable pageable = new PageRequest(0, 1, Direction.DESC, "providerId");
		Page<Participant> page = participantRepository.findByContractId(4001235, pageable);

		assertThat(page.getContent(), hasSize(1));
		assertThat(page.getContent().get(0).getProviderId(),is(1111113));
		assertThat(page.getContent().get(0).getNino(), is("CD756309C"));
		assertThat(page.getContent().get(0).getContractId(), is(4001235));
		assertThat(page.getContent().get(0).getDob(), is(dateFormat.parse("1977-07-27")));
		assertThat(page.isLast(), is(true));
		
		page = participantRepository.findByContractId(4001211, pageable);
		assertThat(page.getContent(), hasSize(0));
	}
	
	
	@Test
	public void findByNinoTest() throws ParseException{
		Pageable pageable = new PageRequest(0, 1, Direction.DESC, "providerId");
		Page<Participant> page = participantRepository.findByNino("AB234071A", pageable);

		assertThat(page.getContent(), hasSize(1));
		assertThat(page.getContent().get(0).getProviderId(),is(1111114));
		assertThat(page.getContent().get(0).getNino(), is("AB234071A"));
		assertThat(page.getContent().get(0).getContractId(), is(4001236));
		assertThat(page.getContent().get(0).getDob(), is(dateFormat.parse("1982-01-22")));
		assertThat(page.isLast(), is(true));
		
		page = participantRepository.findByNino("AB234072A", pageable);
		assertThat(page.getContent(), hasSize(0));
	}
	
	
	@Test
	public void findByContractIdAndNinoTest(){
		List<Participant> participantList = participantRepository.findByContractIdAndNino(4001234,"SJ196058B");

		assertThat(participantList, hasSize(1));
		assertThat(participantList.get(0).getNino(), is("SJ196058B"));
		assertThat(participantList.get(0).getContractId(), is(4001234));
		
		participantList = participantRepository.findByContractIdAndNino(4001234,"XY196058C");
		assertThat(participantList, hasSize(0));
	}
	
	@Test
	public void findByProviderRefTest(){
		Pageable pageable = new PageRequest(0, 1, Direction.DESC, "providerId");
		Page<Participant> page = participantRepository.findByProviderRef("1111114",pageable);
		
		assertThat(page.getContent(), hasSize(1));
		assertThat(page.getContent().get(0).getProviderId(),is(1111112));
		assertThat(page.getContent().get(0).getProviderRef(), is("1111114"));
		assertThat(page.getContent().get(0).getNino(), is("EF984361C"));
		assertThat(page.isLast(), is(true));
		
		page = participantRepository.findByProviderRef("1111113", pageable);
		assertThat(page.getContent(), hasSize(0));
	}

}
