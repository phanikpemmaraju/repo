package uk.gov.dwp.esf.mi.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import uk.gov.dwp.esf.mi.model.Participant;
import uk.gov.dwp.esf.mi.model.Provider;
import uk.gov.dwp.esf.mi.commons.PredicatesBuilder;
import uk.gov.dwp.esf.mi.assemblers.ParticipantResourceAssembler;
import uk.gov.dwp.esf.mi.assemblers.ProviderResourceAssembler;
import uk.gov.dwp.esf.mi.common.RecordState;
import uk.gov.dwp.esf.mi.dtos.ParticipantDTO;
import uk.gov.dwp.esf.mi.resources.ParticipantResource;
import uk.gov.dwp.esf.mi.resources.ParticipantResources;
import uk.gov.dwp.esf.mi.resources.ProviderResource;
import uk.gov.dwp.esf.mi.resources.ProviderResources;
import uk.gov.dwp.esf.mi.repositories.ParticipantRepository;
import uk.gov.dwp.esf.mi.repositories.ProviderRepository;
import org.junit.Before;
import org.junit.Ignore;
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
import org.springframework.hateoas.Link;
import com.mysema.query.types.path.PathBuilder;


@RunWith(MockitoJUnitRunner.class)
public class ParticipantServiceTest {
	
	private SimpleDateFormat dateFormat;
	
	@Mock
	private ModelMapper modelMapper;
	
	@Mock
	private ProviderRepository providerRepository;
	
	@Mock
	private ParticipantRepository participantRepository;
				
	@Mock
	private ProviderResourceAssembler providerResourceAssembler;
	
	@Mock
	private ParticipantResourceAssembler participantResourceAssembler;
	
	@Mock
	private PredicatesBuilder builder;
	
	@Mock
	private PathBuilder<Participant> participantPath;
	
	@Mock
	private Page<Provider> providers;
	
	@Mock
	private Page<Participant> participants;
	
	@InjectMocks
	private ParticipantService participantService;
	
	@Before
	public void setUp(){
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}
	

	/*
	 *  Code to populate dummy data for Providers.
	 * 
	 */
	private List<Provider> getProvidersList(Optional<String> providerId) throws ParseException {
		// TODO Auto-generated method stub				
		List<Provider> providers = new ArrayList<>();
		List<Participant> participants = new ArrayList<>();		
		final Participant participant = new Participant.ParticipantBuilder(new ObjectId().toHexString(), 1111111, "SL123457B", dateFormat.parse(dateFormat.format(new Date())),4001115)
				.creationDate(dateFormat.parse(dateFormat.format(new Date()))).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.INCOMPLETE).build();
		
    	final Provider provider1 = new Provider();
    	provider1.setProviderId(1111111);provider1.setProviderName("the training provider company ltd");
    	final Provider provider2 = new Provider();
    	provider2.setProviderId(1111112);provider2.setProviderName("another provider ltd");
    	Provider provider3 = new Provider();
    	provider3.setProviderId(1111113);provider3.setProviderName("another provider ltd");
    	
    	participants.add(participant);provider1.setParticipants(participants);
    	providers.add(provider1);providers.add(provider2);providers.add(provider3);
    	if(providerId.isPresent())
    		providers = providers.stream().filter(provider -> provider.getProviderId() == Integer.parseInt(providerId.get())).collect(Collectors.toList()); ;    	
    	
		return providers;
	}
	
	
	/*
     * Test getProviders controller rest method
     * This method returns with the basic provider information.
     * A self link is also populated. 
   	*/
	@Ignore
	@Test
	public void testGetProviders() throws Exception{
		final Pageable pageable = new PageRequest(0,1,Direction.ASC,"providerId");
		final List<Provider> providersList = getProvidersList(Optional.empty());
		final Page<Provider> providers = new PageImpl<Provider>(providersList);

		final Provider provider =  new Provider();
		provider.setProviderName("Rob");
		final List<ProviderResource> providerResourcesList = new ArrayList<>();
		ProviderResource resource = new ProviderResource(provider);
		resource.add(new Link("/providers","self"));providerResourcesList.add(resource);
		final ProviderResources providerResources = new ProviderResources();
		providerResources.setProviders(providerResourcesList);
				
		when(providerRepository.findAll(pageable)).thenReturn(providers);
		when(providerResourceAssembler.toResourcesForProvider(providerRepository.findAll(pageable),pageable,"/providers")).thenReturn(null);		
		ProviderResources resources = participantService.getProviders(pageable,"/providers");		
		assertNull(resources);
		
		when(providerResourceAssembler.toResourcesForProvider(providerRepository.findAll(pageable),pageable,"/providers")).thenReturn(providerResources);
		resources = participantService.getProviders(pageable,"/providers");
		assertNotNull(resources);		
		
	}

	
	/*
     * Test getProviderById controller rest method
     * This method returns with the basic provider information.
     * A self link is also populated. 
   	*/
	@Test
	public void testGetProviderById() throws Exception{
		final Pageable pageable = new PageRequest(0,1,Direction.ASC,"providerId");
		final Optional<String> providerId = Optional.of("1111111");
		final List<Provider> providersList = getProvidersList(providerId);

		final List<ProviderResource> providerResourcesList = new ArrayList<>();
		ProviderResource resource = new ProviderResource(providersList.get(0));
		resource.add(new Link("/providers/" + providersList.get(0).getProviderId(),"self"));
		providerResourcesList.add(resource);
		
		when(providers.getContent()).thenReturn(providersList);
				
		when(providerRepository.findByProviderId(Integer.parseInt(providerId.get()),pageable)).thenReturn(providers);
		when(providerResourceAssembler.toResourcesForProviders(providers,"/providers/1111111")).thenReturn(null);		
		List<ProviderResource> resources = participantService.getProviderById(Integer.parseInt(providerId.get()),pageable,"/providers/1111111");		
		assertNull(resources);
		
		when(providerResourceAssembler.toResourcesForProviders(providers,"/providers/1111111")).thenReturn(providerResourcesList);
		resources = participantService.getProviderById(Integer.parseInt(providerId.get()),pageable,"/providers/1111111");
		assertNotNull(resources);		
        assertThat(resources.get(0).getProvider().getProviderId(), is(1111111));
        assertThat(resources.get(0).getProvider().getProviderName(), is("the training provider company ltd"));
        
        when(providerRepository.findByProviderId(Integer.parseInt(providerId.get()),pageable)).thenReturn(null);
		resources = participantService.getProviderById(Integer.parseInt(providerId.get()),pageable,"/providers/1111111");
		assertNull(resources);		
		
		providersList.clear();
		when(providers.getContent()).thenReturn(providersList);
		when(providerRepository.findByProviderId(Integer.parseInt(providerId.get()),pageable)).thenReturn(providers);
		resources = participantService.getProviderById(Integer.parseInt(providerId.get()),pageable,"/providers/1111111");
		assertNull(resources);	
		
		providers = null;
		when(providerRepository.findByProviderId(Integer.parseInt(providerId.get()),pageable)).thenReturn(providers);
		resources = participantService.getProviderById(Integer.parseInt(providerId.get()),pageable,"/providers/1111111");
		assertNull(resources);	
	}


	
	
	/*
     * Test getParticipantsByProviderId controller rest method
     * This method returns with the basic provider information.
     * A self link is also populated. 
   	*/
	@Test
	public void testGetParticipantsByProviderId() throws Exception{
		final Pageable pageable = new PageRequest(0,1,Direction.ASC,"providerId");
		final List<Participant> participantsList = new ArrayList<>();
		final Participant participant1 = new Participant.ParticipantBuilder("56e1624c710856201cd55f61", 1111111, "SL123457B", dateFormat.parse(dateFormat.format(new Date())),4001114)
				.creationDate(dateFormat.parse(dateFormat.format(new Date()))).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.INCOMPLETE).build();
		final Participant participant2 = new Participant.ParticipantBuilder("56e1624c710856201cd55f62", 1111111, "SL123457B", dateFormat.parse(dateFormat.format(new Date())),4001115)
				.creationDate(dateFormat.parse(dateFormat.format(new Date()))).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.INCOMPLETE).build();
    	
		participantsList.add(participant1);participantsList.add(participant2);
		
		final List<ParticipantResource> participantResourcesList = new ArrayList<>();
		ParticipantResources participantResources = new ParticipantResources();
		participantsList.forEach(participant -> {
			final ParticipantDTO participantDTO = new ModelMapper().map(participant, ParticipantDTO.class);
			final ParticipantResource participantResource = new ParticipantResource(participantDTO);
			participantResource.add(new Link("/providers/"+participant.getProviderId()+"/participants/"+participant.getParticipantId(),"self"));
			participantResourcesList.add(participantResource);
		});
		
		participantResources.setParticipants(participantResourcesList);
		
		when(participants.getContent()).thenReturn(participantsList);
		when(participantRepository.findByProviderId(1111111, pageable)).thenReturn(participants);
		when(participantResourceAssembler.toResourcesForParticipants(modelMapper,participantRepository.findByProviderId(1111111, pageable),pageable,"/providers/1111111/participants")).thenReturn(null);
		
		ParticipantResources resources = participantService.getParticipantsByProviderId(1111111, pageable, "/providers/1111111/participants");
		assertNull(resources);
		
		when(participantResourceAssembler.toResourcesForParticipants(modelMapper,participantRepository.findByProviderId(1111111, pageable),pageable,"/providers/1111111/participants")).thenReturn(participantResources);
		resources = participantService.getParticipantsByProviderId(1111111, pageable, "/providers/1111111/participants");
		assertNotNull(resources);
		assertEquals(resources.getParticipants().size(),2);
		assertEquals(resources.getParticipants().get(0).getLinks().get(0).getHref(),"/providers/1111111/participants/56e1624c710856201cd55f61");
		assertEquals(resources.getParticipants().get(1).getLinks().get(0).getHref(),"/providers/1111111/participants/56e1624c710856201cd55f62");
		
		participantsList.clear();
		when(participants.getContent()).thenReturn(participantsList);
		resources = participantService.getParticipantsByProviderId(1111111, pageable, "/providers/1111111/participants");
		assertNull(resources);
		
		participants = null;
		when(participantRepository.findByProviderId(1111111, pageable)).thenReturn(participants);
		resources = participantService.getParticipantsByProviderId(1111111, pageable, "/providers/1111111/participants");
		assertNull(resources);
	}

	
	/*
     * Test getParticipantsByProviderIdAndParticipantId controller rest method
     * This method returns with the basic provider information.
     * A self link is also populated. 
   	*/
	@Test
	public void testGetParticipantsByProviderIdAndParticipantId() throws Exception{
		final Pageable pageable = new PageRequest(0,1,Direction.ASC,"providerId");
		final List<Participant> participantsList = new ArrayList<>();
		final Participant participant1 = new Participant.ParticipantBuilder("56e1624c710856201cd55f61", 1111111, "SL123457B", dateFormat.parse(dateFormat.format(new Date())),4001114)
				.creationDate(dateFormat.parse(dateFormat.format(new Date()))).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.INCOMPLETE).build();
    	    	    					
		participantsList.add(participant1);
		
		List<ParticipantResource> participantResourcesList = new ArrayList<>();
		participantsList.forEach(participant -> {
			final ParticipantDTO participantDTO = new ModelMapper().map(participant, ParticipantDTO.class);
			final ParticipantResource participantResource = new ParticipantResource(participantDTO);
			participantResource.add(new Link("/providers/"+participant.getProviderId()+"/participants/"+participant.getParticipantId(),"self"));
			participantResourcesList.add(participantResource);
		});
		
		
		when(participants.getContent()).thenReturn(participantsList);
		when(participantRepository.findByProviderIdAndParticipantId(1111111, "56e1624c710856201cd55f61", pageable)).thenReturn(participants);
		when(participantResourceAssembler.toResourcesForAParticipant(modelMapper,participantRepository.findByProviderIdAndParticipantId(1111111, "56e1624c710856201cd55f61", pageable),"/providers/1111111/participants/56e1624c710856201cd55f61")).thenReturn(null);
		
		List<ParticipantResource>  resources = participantService.getParticipantsByProviderIdAndParticipantId(1111111, "56e1624c710856201cd55f61", pageable, "/providers/1111111/participants/56e1624c710856201cd55f61");
		assertNull(resources);
		
		when(participantResourceAssembler.toResourcesForAParticipant(modelMapper,participantRepository.findByProviderIdAndParticipantId(1111111, "56e1624c710856201cd55f61", pageable),"/providers/1111111/participants/56e1624c710856201cd55f61")).thenReturn(participantResourcesList);
		
		resources = participantService.getParticipantsByProviderIdAndParticipantId(1111111, "56e1624c710856201cd55f61", pageable, "/providers/1111111/participants/56e1624c710856201cd55f61");
		assertNotNull(resources);
		assertEquals(resources.get(0).getLinks().get(0).getHref(),"/providers/1111111/participants/56e1624c710856201cd55f61");
		assertEquals(resources.get(0).getParticipant().getParticipantId(),"56e1624c710856201cd55f61");
		
		participantsList.clear();
		when(participants.getContent()).thenReturn(participantsList);
		resources = participantService.getParticipantsByProviderIdAndParticipantId(1111111, "56e1624c710856201cd55f61", pageable, "/providers/1111111/participants/56e1624c710856201cd55f61");
		assertNull(resources);
		
		participants = null;
		when(participantRepository.findByProviderIdAndParticipantId(1111111, "56e1624c710856201cd55f61", pageable)).thenReturn(participants);
		resources = participantService.getParticipantsByProviderIdAndParticipantId(1111111, "56e1624c710856201cd55f61", pageable, "/providers/1111111/participants/56e1624c710856201cd55f61");
		assertNull(resources);
	}

	
	/*
     * Test getFilteredParticipants controller rest method
     * This method returns with the basic provider information.
     * A self link is also populated. 
   	*/
	@Test
	public void testGetFilteredParticipants() throws Exception{
		final Pageable pageable = new PageRequest(0,1,Direction.ASC,"providerId");
		final List<Participant> participantsList = new ArrayList<>();

		final Participant participant1 = new Participant.ParticipantBuilder("56e1624c710856201cd55f61", 1111111, "SL123457B", dateFormat.parse(dateFormat.format(new Date())),4001114)
				.creationDate(dateFormat.parse(dateFormat.format(new Date()))).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.INCOMPLETE).build();
    	
		final Participant participant2 = new Participant.ParticipantBuilder("56e1624c710856201cd55f62", 1111111, "SL123457B", dateFormat.parse(dateFormat.format(new Date())),4001115)
				.creationDate(dateFormat.parse(dateFormat.format(new Date()))).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.INCOMPLETE).build();

		participantsList.add(participant1);participantsList.add(participant2);
		
		//Page<Participant> participants = new PageImpl<Participant>(participantsList);
		final List<ParticipantResource> participantResourcesList = new ArrayList<>();
		ParticipantResources participantResources = new ParticipantResources();
		participantsList.forEach(participant -> {
			final ParticipantDTO participantDTO = new ModelMapper().map(participant, ParticipantDTO.class);
			final ParticipantResource participantResource = new ParticipantResource(participantDTO);
			participantResource.add(new Link("/providers/"+participant.getProviderId()+"/participants/"+participant.getParticipantId(),"self"));
			participantResourcesList.add(participantResource);
		});
		
		participantResources.setParticipants(participantResourcesList);
		
		when(participants.getContent()).thenReturn(participantsList);
		when(participantRepository.findAll(participantPath.in(participantsList), pageable)).thenReturn(participants);
		when(participantResourceAssembler.toResourcesForParticipants(modelMapper,participantRepository.findAll(participantPath.in(participantsList), pageable),pageable,"/providers/1111111/participants/search/participantId_in=56e1624c710856201cd55f61,56e1624c710856201cd55f62")).thenReturn(null);
		
		ParticipantResources resources = participantService.getFilteredParticipants("participantId_in=56e1624c710856201cd55f61,56e1624c710856201cd55f62", pageable, "/providers/1111111/participants/search/participantId_in=56e1624c710856201cd55f61,56e1624c710856201cd55f62");
		assertNull(resources);
		
		when(participantResourceAssembler.toResourcesForParticipants(modelMapper,participantRepository.findAll(participantPath.in(participantsList), pageable),pageable,"/providers/1111111/participants/search/participantId_in=56e1624c710856201cd55f61,56e1624c710856201cd55f62")).thenReturn(participantResources);
		resources = participantService.getFilteredParticipants("participantId_in=56e1624c710856201cd55f61,56e1624c710856201cd55f62", pageable, "/providers/1111111/participants/search/participantId_in=56e1624c710856201cd55f61,56e1624c710856201cd55f62");
		assertNotNull(resources);
		assertEquals(resources.getParticipants().get(0).getParticipant().getParticipantId(),"56e1624c710856201cd55f61");
		assertEquals(resources.getParticipants().get(1).getParticipant().getParticipantId(),"56e1624c710856201cd55f62");
		
		participantsList.clear();
		when(participants.getContent()).thenReturn(participantsList);
		resources = participantService.getFilteredParticipants("participantId_in=56e1624c710856201cd55f61,56e1624c710856201cd55f62", pageable, "/providers/1111111/participants/search/participantId_in=56e1624c710856201cd55f61,56e1624c710856201cd55f62");
		assertNull(resources);
		
		participants = null;
		when(participantRepository.findAll(participantPath.in(participantsList), pageable)).thenReturn(participants);
		resources = participantService.getFilteredParticipants("participantId_in=56e1624c710856201cd55f61,56e1624c710856201cd55f62", pageable, "/providers/1111111/participants/search/participantId_in=56e1624c710856201cd55f61,56e1624c710856201cd55f62");
		assertNull(resources);
	}
	
	
	/*
     * Test createParticipant controller rest method
     * This method saves the participant object into the repository and returns with the participant object with a generated Id.
     * A self link is also populated. 
   	*/
	@Test
	public void testCreateParticipant() throws Exception{
		final List<Participant> participantsList = new ArrayList<>();
		final Participant participant = new Participant.ParticipantBuilder(null, 1111111, "SL123457B", dateFormat.parse(dateFormat.format(new Date())),4001114)
				.creationDate(dateFormat.parse(dateFormat.format(new Date()))).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.INCOMPLETE).build();
    	
				
		final Participant persistentParticipant = new Participant.ParticipantBuilder("56b4694d7eb68a5fff76290c", 1111111, "SL123457B", dateFormat.parse(dateFormat.format(new Date())),4001114)
				.creationDate(dateFormat.parse(dateFormat.format(new Date()))).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.INCOMPLETE).build();
    	    					
		participantsList.add(participant);
		List<ParticipantResource> participantResourcesList = new ArrayList<>();
		participantsList.forEach(participantResource -> {
			final ParticipantDTO participantDTO = new ModelMapper().map(persistentParticipant, ParticipantDTO.class);
			final ParticipantResource resource = new ParticipantResource(participantDTO);
			resource.add(new Link("/providers/"+participant.getProviderId()+"/participants/"+participant.getParticipantId(),"self"));
			participantResourcesList.add(resource);
		});

		final ParticipantDTO participantDTO = new ModelMapper().map(persistentParticipant, ParticipantDTO.class);
		participantDTO.setDob(dateFormat.parse("1982-12-16"));participantDTO.setNino("SJ196058B");
		
		when(participantRepository.save(modelMapper.map(participantDTO, Participant.class))).thenReturn(persistentParticipant);
		when(participantResourceAssembler.toResourcesForAParticipant(modelMapper,participantDTO,persistentParticipant.getParticipantId(),"/providers/1111111/participants")).thenReturn(null);
		
		List<ParticipantResource>  resources = participantService.createParticipant(participantDTO, "/providers/1111111/participants");
		assertNull(resources);
		
		when(participantResourceAssembler.toResourcesForAParticipant(modelMapper,participantDTO,persistentParticipant.getParticipantId(),"/providers/1111111/participants")).thenReturn(participantResourcesList);
		resources = participantService.createParticipant(participantDTO, "/providers/1111111/participants");
		assertNotNull(resources);		
        assertThat(resources.get(0).getParticipant().getProviderId(), is(1111111));
        assertThat(resources.get(0).getParticipant().getParticipantId(), is("56b4694d7eb68a5fff76290c"));
        
        when(participantRepository.save(modelMapper.map(participantDTO, Participant.class))).thenReturn(null);
        resources = participantService.createParticipant(participantDTO, "/providers/1111111/participants");
		assertNull(resources);
		
	}
	
	/*
     * Test updateParticipant controller rest method
     * This method saves the existing participant object with the new details into the repository.
     * A self link is also populated. 
   	*/
	@Test
	public void testUpdateParticipant() throws Exception{
		final List<Participant> participantsList = new ArrayList<>();
		final Participant participant = new Participant.ParticipantBuilder(null, 1111111, "SJ196058B", dateFormat.parse(dateFormat.format(new Date())),4001114)
				.creationDate(dateFormat.parse(dateFormat.format(new Date()))).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.INCOMPLETE).build();
				
		final Participant persistentParticipant = new Participant.ParticipantBuilder("56b4694d7eb68a5fff76290c", 1111111, "SJ196058B", dateFormat.parse(dateFormat.format(new Date())),4001114)
				.creationDate(dateFormat.parse(dateFormat.format(new Date()))).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.INCOMPLETE).build();
    	    					
		participantsList.add(participant);
		List<ParticipantResource> participantResourcesList = new ArrayList<>();
		participantsList.forEach(participantResource -> {
			final ParticipantDTO participantDTO = new ModelMapper().map(persistentParticipant, ParticipantDTO.class);
			final ParticipantResource resource = new ParticipantResource(participantDTO);
			resource.add(new Link("/providers/"+participant.getProviderId()+"/participants/"+participant.getParticipantId(),"self"));
			participantResourcesList.add(resource);
		});
		
		final ParticipantDTO participantDTO = new ModelMapper().map(persistentParticipant, ParticipantDTO.class);
		participantDTO.setDob(dateFormat.parse("1982-12-16"));participantDTO.setNino("SJ196058B");
		
		when(participantRepository.findByParticipantId("56b4694d7eb68a5fff76290c")).thenReturn(persistentParticipant);
		when(participantRepository.save(modelMapper.map(participantDTO, Participant.class))).thenReturn(persistentParticipant);
		when(participantResourceAssembler.toResourcesForAParticipant(modelMapper,participantDTO,persistentParticipant.getParticipantId(),"/providers/1111111/participants")).thenReturn(null);
		
		List<ParticipantResource>  resources = participantService.updateParticipant(participantDTO, "/providers/1111111/participants");
		assertNull(resources);
		
		when(participantResourceAssembler.toResourcesForAParticipant(modelMapper,participantDTO,persistentParticipant.getParticipantId(),"/providers/1111111/participants")).thenReturn(participantResourcesList);
		resources = participantService.updateParticipant(participantDTO, "/providers/1111111/participants");
		assertNotNull(resources);		
        assertThat(resources.get(0).getParticipant().getProviderId(), is(1111111));
        assertThat(resources.get(0).getParticipant().getParticipantId(), is("56b4694d7eb68a5fff76290c"));
        
        when(participantRepository.findByParticipantId("56b4694d7eb68a5fff76290c")).thenReturn(null);
        when(participantRepository.save(modelMapper.map(participantDTO, Participant.class))).thenReturn(null);
        resources = participantService.updateParticipant(participantDTO, "/providers/1111111/participants");
		assertNull(resources);
		
	}
	
	/*
     * Test deleteParticipant controller rest method
     * This method deletes the participant object from the repository
     * A self link is also populated. 
   	*/
	@Test
	public void testDeleteParticipant() throws Exception{				
		final Participant participant = new Participant.ParticipantBuilder("56b4694d7eb68a5fff76290c", 1111111, "SL123457B", dateFormat.parse(dateFormat.format(new Date())),4001114)
				.creationDate(dateFormat.parse(dateFormat.format(new Date()))).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.INCOMPLETE).build();
    	    							
		when(participantRepository.findOne("56b4694d7eb68a5fff76290c")).thenReturn(participant);
		participantService.deleteParticipant("56b4694d7eb68a5fff76290c");
		verify(participantRepository,times(1)).delete("56b4694d7eb68a5fff76290c");
	}


	
}
