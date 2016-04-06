package uk.gov.dwp.esf.mi.controllers;

/*
 *  @Author : Phani Krishna
 *  @Description : ESFControllerMocksTest class uses Mockito framework to mock objects. 
 *  @Version : 1.0
 * 
 */

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import uk.gov.dwp.esf.mi.model.Participant;
import uk.gov.dwp.esf.mi.model.Provider;
import uk.gov.dwp.esf.mi.ms.MockDomainConfiguration;
import uk.gov.dwp.esf.mi.common.RecordState;
import uk.gov.dwp.esf.mi.controllers.ParticipantController;
import uk.gov.dwp.esf.mi.dtos.ParticipantDTO;
import uk.gov.dwp.esf.mi.resources.ParticipantResource;
import uk.gov.dwp.esf.mi.resources.ParticipantResources;
import uk.gov.dwp.esf.mi.resources.ProviderResource;
import uk.gov.dwp.esf.mi.resources.ProviderResources;
import uk.gov.dwp.esf.mi.services.ParticipantService;

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import static org.hamcrest.Matchers.equalTo;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { ParticipantController.class, MockDomainConfiguration.class })
@WebAppConfiguration
public class ParticipantControllerTest {

	@Autowired
	private ParticipantService participantService;
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	protected WebApplicationContext webApplicationContext;

	@Autowired
	private ObjectMapper mapper;
	private SimpleDateFormat dateFormat;
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}

	@After
	public void resetMocks() {
		reset(participantService);
	}

	/*
	 * Code to populate dummy data for Providers.
	 * 
	 */
	private List<Provider> getProvidersList(Optional<String> providerId) throws ParseException {
		// TODO Auto-generated method stub
		List<Provider> providers = new ArrayList<>();
		List<Participant> participants = new ArrayList<>();
		final Participant participant = new Participant.ParticipantBuilder(new ObjectId().toHexString(), 1111111,
				"SL123457B", dateFormat.parse(dateFormat.format(new Date())), 4001115).creationDate(dateFormat.parse(dateFormat.format(new Date()))).updatedDate(dateFormat.parse(dateFormat.format(new Date())))
						.recordState(RecordState.INCOMPLETE).build();

		final Provider provider1 = new Provider();
		provider1.setProviderId(1111111);
		provider1.setProviderName("the training provider company ltd");
		final Provider provider2 = new Provider();
		provider2.setProviderId(1111112);
		provider2.setProviderName("another provider ltd");
		Provider provider3 = new Provider();
		provider3.setProviderId(1111113);
		provider3.setProviderName("another provider ltd");

		participants.add(participant);
		provider1.setParticipants(participants);
		providers.add(provider1);
		providers.add(provider2);
		providers.add(provider3);
		if (providerId.isPresent())
			providers = providers.stream()
					.filter(provider -> provider.getProviderId() == Integer.parseInt(providerId.get()))
					.collect(Collectors.toList());
		;

		return providers;
	}

	/*
	 * Test getProviders controller rest method This method returns with the
	 * basic provider information. A self link is also populated.
	 */

	@Test
	public void testGetProviders() throws Exception {
		final ProviderResources resources = new ProviderResources();
		final List<ProviderResource> resourceList = new ArrayList<>();
		final List<Provider> list = getProvidersList(Optional.empty());
		list.forEach(provider -> {
			ProviderResource resource = new ProviderResource(provider);
			resource.add(new Link("/providers/" + provider.getProviderId(), "self"));
			resourceList.add(resource);
		});
		resources.setProviders(resourceList);

		String uri = "/providers";
		when(participantService.getProviders(Mockito.anyObject(), Mockito.anyString())).thenReturn(resources);

		mockMvc.perform(get(uri).accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.providers.[0].provider.providerId").value(equalTo(1111111)))
				.andExpect(jsonPath("$.providers.[0].links.[0].href").value(equalTo("/providers/1111111")));
	}

	/*
	 * Test getProviderById controller rest method for a given providerId This
	 * method returns with provider information along with contract details. A
	 * self link and a participants link is also populated.
	 */
	@Test
	public void testGetProviderById() throws Exception {
		final Optional<String> providerId = Optional.of("1111111");
		final List<ProviderResource> resourceList = new ArrayList<>();
		final List<Provider> list = getProvidersList(providerId);
		list.forEach(provider -> {
			final ProviderResource resource = new ProviderResource(provider);
			resource.add(new Link("/providers/" + provider.getProviderId(), "self"));
			resourceList.add(resource);
		});

		when(participantService.getProviderById(Mockito.anyInt(), Mockito.anyObject(), Mockito.anyString()))
				.thenReturn(resourceList);

		String uri = "/providers/{providerId}";

		mockMvc.perform(get(uri, "1111111").accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.[0].provider.providerId").value(equalTo(1111111)))
				.andExpect(jsonPath("$.[0].links.[0].href").value(equalTo("/providers/1111111")));


		when(participantService.getProviderById(Mockito.anyInt(), Mockito.anyObject(), Mockito.anyString()))
				.thenReturn(null);
		mockMvc.perform(get(uri, "5555555").accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isNotFound())
				.andDo(print());

	}

	/*
	 * Test getParticipantsByProviderId controller rest method This method
	 * returns with basic provider information along with all participant
	 * details for the specified providerId. A self link for each participant,
	 * previous and next page links for the data collection.
	 */

	@Test
	public void testGetParticipantsByProviderId() throws Exception {
		final List<Participant> participantsList = new ArrayList<>();
		final Participant participant = new Participant.ParticipantBuilder("56ebb854bf9a401670d0eadc", 1111111,
				"SL123457B", dateFormat.parse(dateFormat.format(new Date())), 4001115).creationDate(dateFormat.parse(dateFormat.format(new Date()))).updatedDate(dateFormat.parse(dateFormat.format(new Date())))
				.recordState(RecordState.INCOMPLETE).build();

		participantsList.add(participant);
		final List<ParticipantResource> participantList = new ArrayList<>();
		participantsList.forEach(Participant -> {
			final ParticipantResource resource = new ParticipantResource(modelMapper.map(Participant,ParticipantDTO.class));
			resource.add(new Link(
					"/providers/" + Participant.getProviderId() + "/participants/" + Participant.getParticipantId()));
			participantList.add(resource);
		});

		final ParticipantResources participantResources = new ParticipantResources();
		participantResources.setParticipants(participantList);
		when(participantService.getParticipantsByProviderId(Mockito.anyInt(), Mockito.anyObject(),
				Mockito.anyString())).thenReturn(participantResources);

		String uri = "/providers/{providerId}/participants";

		mockMvc.perform(get(uri, "1111111").accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.participants.[0].participant.participantId").value(equalTo("56ebb854bf9a401670d0eadc")))
				.andExpect(jsonPath("$.participants.[0].participant.nino").value(equalTo("SL123457B")))
				.andExpect(jsonPath("$.participants.[0].participant.creationDate").value(equalTo(LocalDate.now().toString())))
				.andExpect(jsonPath("$.participants.[0].participant.updatedDate").value(equalTo(LocalDate.now().toString())))
				.andExpect(jsonPath("$.participants.[0].participant.recordState").value(equalTo("INCOMPLETE")))
				.andExpect(jsonPath("$.participants.[0].links.[0].href")
						.value(equalTo("/providers/1111111/participants/56ebb854bf9a401670d0eadc")));



		when(participantService.getParticipantsByProviderId(Mockito.anyInt(), Mockito.anyObject(),
				Mockito.anyString())).thenReturn(null);
		mockMvc.perform(get(uri, "5555555").accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isNotFound())
				.andDo(print());

	}

	/*
	 * Test getParticipantsByProviderIdAndParticipantId controller rest method
	 * This method returns all participant details for the specified providerId
	 * & participantId. A self link for the participant object is created.
	 */

	@Test
	public void testGetParticipantsByProviderIdAndParticipantId() throws Exception {
		final List<Participant> participantsList = new ArrayList<>();
		final Participant participant = new Participant.ParticipantBuilder("12345678", 1111111,
				"SL123457B", dateFormat.parse(dateFormat.format(new Date())), 4001115).creationDate(dateFormat.parse(dateFormat.format(new Date()))).updatedDate(dateFormat.parse(dateFormat.format(new Date())))
				.recordState(RecordState.INCOMPLETE).build();

		participantsList.add(participant);
		final List<ParticipantResource> participantList = new ArrayList<>();
		participantsList.forEach(Participant -> {
			final ParticipantResource resource = new ParticipantResource(modelMapper.map(Participant,ParticipantDTO.class));
			resource.add(new Link(
					"/providers/" + Participant.getProviderId() + "/participants/" + Participant.getParticipantId()));
			participantList.add(resource);
		});

		when(participantService.getParticipantsByProviderIdAndParticipantId(Mockito.anyInt(), Mockito.anyString(),
				Mockito.anyObject(), Mockito.anyString())).thenReturn(participantList);

		String uri = "/providers/{providerId}/participants/{participantId}";

		mockMvc.perform(get(uri, "1111111", "12345678").accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.[0].participant.participantId").value(equalTo("12345678")))
				.andExpect(jsonPath("$.[0].participant.nino").value(equalTo("SL123457B")))
				.andExpect(jsonPath("$.[0].participant.creationDate").value(LocalDate.now().toString()))
				.andExpect(jsonPath("$.[0].participant.updatedDate").value(LocalDate.now().toString()))
				.andExpect(jsonPath("$.[0].participant.recordState").value(equalTo("INCOMPLETE")))
				.andExpect(jsonPath("$.[0].links.[0].href").value(equalTo("/providers/1111111/participants/12345678")));


		when(participantService.getParticipantsByProviderIdAndParticipantId(Mockito.anyInt(), Mockito.anyString(),
				Mockito.anyObject(), Mockito.anyString())).thenReturn(null);
		mockMvc.perform(get(uri, "1111111", "5555555").accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isNotFound()).andDo(print());

	}

	/*
	 * Test getFilteredParticipants controller rest method This method returns
	 * all participant details for the specified search filter. A self link for
	 * each participant, previous and next page links for the data collection.
	 */

	@Test
	public void testGetFilteredParticipants() throws Exception {
		final List<Participant> participantsList = new ArrayList<>();
		final Participant participant1 = new Participant.ParticipantBuilder("12345677", 1111111, "SL123457B",
				dateFormat.parse(dateFormat.format(new Date())),4001115).creationDate(dateFormat.parse(dateFormat.format(new Date())))
						.updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.INCOMPLETE).build();

		final Participant participant2 = new Participant.ParticipantBuilder("12345678", 1111111, "SL123457B",
				dateFormat.parse(dateFormat.format(new Date())),4001115).creationDate(dateFormat.parse(dateFormat.format(new Date())))
						.updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.INCOMPLETE).build();

		final Participant participant3 = new Participant.ParticipantBuilder("12345679", 1111111, "SL123457B",
				dateFormat.parse(dateFormat.format(new Date())),4001115).creationDate(dateFormat.parse(dateFormat.format(new Date())))
						.updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.INCOMPLETE).build();

		participantsList.add(participant1);
		participantsList.add(participant2);
		participantsList.add(participant3);
		final String filter = "participantId=12345677,12345678,12345679";
		final List<ParticipantResource> participantList = new ArrayList<>();
		participantsList.forEach(Participant -> {
			final ParticipantResource resource = new ParticipantResource(modelMapper.map(Participant,ParticipantDTO.class));
			resource.add(new Link(
					"/providers/" + Participant.getProviderId() + "/participants/" + Participant.getParticipantId()));
			participantList.add(resource);
		});
		final ParticipantResources participantResources = new ParticipantResources();
		participantResources.setParticipants(participantList);
		when(participantService.getFilteredParticipants(Mockito.anyString(), Mockito.anyObject(), Mockito.anyString()))
				.thenReturn(participantResources);

		String uri = "/providers/{providerId}/participants/search/{filter}";

		mockMvc.perform(get(uri, "1111111", filter).accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.participants.[0].participant.participantId").value(equalTo("12345677")))
				.andExpect(jsonPath("$.participants.[1].participant.participantId").value(equalTo("12345678")))
				.andExpect(jsonPath("$.participants.[2].participant.participantId").value(equalTo("12345679")))
				.andExpect(jsonPath("$.participants.[0].participant.nino").value(equalTo("SL123457B")))
				.andExpect(jsonPath("$.participants.[0].participant.creationDate")
						.value(equalTo(LocalDate.now().toString())))
				.andExpect(jsonPath("$.participants.[0].participant.updatedDate")
						.value(equalTo(LocalDate.now().toString())))
				.andExpect(jsonPath("$.participants.[0].participant.recordState").value(equalTo("INCOMPLETE")))
				.andExpect(jsonPath("$.participants.[0].links.[0].href")
						.value(equalTo("/providers/1111111/participants/12345677")))
				.andExpect(jsonPath("$.participants.[1].links.[0].href")
						.value(equalTo("/providers/1111111/participants/12345678")))
				.andExpect(jsonPath("$.participants.[2].links.[0].href")
						.value(equalTo("/providers/1111111/participants/12345679")));


		when(participantService.getFilteredParticipants(Mockito.anyString(), Mockito.anyObject(), Mockito.anyString()))
				.thenReturn(null);
		mockMvc.perform(get(uri, "5555555", filter).accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isNotFound()).andDo(print());

	}

	/*
	 * Test createParticipant controller rest method This method creates a
	 * participant object in the back end and returns the stored Participant
	 * object. A self link for the participant object is created.
	 */

	@Test
	public void testCreateParticipant() throws Exception {
		String REQUEST_CONTENT = "{\"participantId\":\"56b4694d7eb68a5fff76290c\",\"providerId\":1111111,\"nino\":\"SJ196058B\",\"dob\":\"1982-12-16\",\"contractId\":4001115,\"creationDate\":\"2015-01-11\",\"updatedDate\":\"2015-01-11\",\"recordState\":\"INCOMPLETE\"}";
		final List<Participant> participantsList = new ArrayList<>();
		final Participant participant = new Participant.ParticipantBuilder(null, 1111111, "SJ196058B",dateFormat.parse("1982-12-16"),4001115)
						.creationDate(dateFormat.parse("2015-01-11")).updatedDate(dateFormat.parse("2015-01-11")).recordState(RecordState.INCOMPLETE)
						.build();

		final Participant persistentParticipant = new Participant.ParticipantBuilder("56b4694d7eb68a5fff76290c",1111111, "SJ196058B", dateFormat.parse("1982-12-16"),4001115)
						.creationDate(dateFormat.parse("2015-01-11")).updatedDate(dateFormat.parse("2015-01-11")).recordState(RecordState.INCOMPLETE)
						.build();

		participantsList.add(persistentParticipant);
		final List<ParticipantResource> participantList = new ArrayList<>();
		participantsList.forEach(Participant -> {
			final ParticipantResource resource = new ParticipantResource(modelMapper.map(Participant,ParticipantDTO.class));
			resource.add(new Link(
					"/providers/" + Participant.getProviderId() + "/participants/" + Participant.getParticipantId()));
			participantList.add(resource);
		});

		when(participantService.createParticipant(Mockito.anyObject(), Mockito.anyString()))
				.thenReturn(participantList);

		String uri = "/providers/{providerId}/participants";

		mockMvc.perform(post(uri, 1111111).contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(participant))
				.content(REQUEST_CONTENT)).andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.[0].participant.participantId").value(equalTo("56b4694d7eb68a5fff76290c")))
				.andExpect(jsonPath("$.[0].participant.nino").value(equalTo("SJ196058B")))
				.andExpect(jsonPath("$.[0].participant.creationDate").value(equalTo("2015-01-11")))
				.andExpect(jsonPath("$.[0].participant.updatedDate").value(equalTo("2015-01-11")))
				.andExpect(jsonPath("$.[0].participant.recordState").value(equalTo("INCOMPLETE")));

		REQUEST_CONTENT = "{\"participantId\":\"56b4694d7eb68a5fff76290c\",\"providerId\":\"1111111\",\"title\":\"Mr\",\"surname\":\"Mark\",\"firstName\":\"Anderson\",\"nino\":\"SL30999\",\"creationDate\":\"2015-01-11\",\"updatedDate\":\"2015-01-11\",\"status\":\"2\"}";

		mockMvc.perform(post(uri, 1111111).contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(participant))
				.content(REQUEST_CONTENT)).andExpect(status().isBadRequest()).andDo(print());

	}

	/*
	 * Test updateParticipant controller rest method This method creates a
	 * participant object in the back end and returns the stored Participant
	 * object. A self link for the participant object is created.
	 */

	@Test
	public void testUpdateParticipant() throws Exception {
		String REQUEST_CONTENT = "{\"participantId\":\"56b4694d7eb68a5fff76290c\",\"providerId\":1111111,\"nino\":\"SJ196058B\",\"dob\":\"1982-12-16\",\"contractId\":4001115,\"creationDate\":\"2015-01-11\",\"updatedDate\":\"2015-01-11\",\"recordState\":\"INCOMPLETE\"}";
		final List<Participant> participantsList = new ArrayList<>();
		final Participant participant = new Participant.ParticipantBuilder(null, 1111111, "SJ196058B",
				dateFormat.parse(dateFormat.format(new Date())),4001115).creationDate(dateFormat.parse(dateFormat.format(new Date())))
						.updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.INCOMPLETE).build();

		final Participant persistentParticipant = new Participant.ParticipantBuilder("56b4694d7eb68a5fff76290c",
				1111111, "SJ196058B", dateFormat.parse(dateFormat.format(new Date())),4001115)
						.creationDate(dateFormat.parse(dateFormat.format(new Date()))).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.INCOMPLETE).build();

		participantsList.add(persistentParticipant);
		final List<ParticipantResource> participantList = new ArrayList<>();
		participantsList.forEach(Participant -> {
			final ParticipantResource resource = new ParticipantResource(modelMapper.map(Participant,ParticipantDTO.class));
			resource.add(new Link(
					"/providers/" + Participant.getProviderId() + "/participants/" + Participant.getParticipantId()));
			participantList.add(resource);
		});

		when(participantService.updateParticipant(Mockito.anyObject(), Mockito.anyString()))
				.thenReturn(participantList);

		String uri = "/providers/{providerId}/participants";

		mockMvc.perform(
				put(uri, 1111111).contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON_UTF8)
						.content(mapper.writeValueAsString(participant)).content(REQUEST_CONTENT))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.[0].participant.participantId").value(equalTo("56b4694d7eb68a5fff76290c")))
				.andExpect(jsonPath("$.[0].participant.nino").value(equalTo("SJ196058B")))
				.andExpect(jsonPath("$.[0].participant.creationDate").value(equalTo(LocalDate.now().toString())))
				.andExpect(jsonPath("$.[0].participant.updatedDate").value(equalTo(LocalDate.now().toString())))
				.andExpect(jsonPath("$.[0].participant.recordState").value(equalTo("INCOMPLETE")));

		REQUEST_CONTENT = "{\"participantId\":\"56b4694d7eb68a5fff76290c\",\"providerId\":\"1111111\",\"title\":\"Mr\",\"surname\":\"Mark\",\"firstName\":\"Anderson\",\"nino\":\"SL123457B\",\"creationDate\":\"2015-01-11\",\"updatedDate\":\"2015-01-11\",\"recordState\":\"INCOMPLETE\"}";

		mockMvc.perform(
				put(uri, 1111111).contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON_UTF8)
						.content(mapper.writeValueAsString(participant)).content(REQUEST_CONTENT))
				.andExpect(status().isBadRequest()).andDo(print());

		REQUEST_CONTENT = "{\"participantId\":\"56b4694d7eb68a5fff76290c\",\"providerId\":1111111,\"nino\":\"SJ196011B\",\"dob\":\"1982-12-16\",\"contractId\":4001115,\"creationDate\":\"2015-01-11\",\"updatedDate\":\"2015-01-11\",\"recordState\":\"INCOMPLETE\"}";

		when(participantService.updateParticipant(Mockito.anyObject(), Mockito.anyString())).thenReturn(null);

		mockMvc.perform(
				put(uri, 1111111).contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON_UTF8)
						.content(mapper.writeValueAsString(participant)).content(REQUEST_CONTENT))
				.andExpect(status().isNotFound()).andDo(print());

	}

	/*
	 * Test deleteParticipant controller rest method This method creates a
	 * participant object in the back end and returns the stored Participant
	 * object. A self link for the participant object is created.
	 */

	@Test
	public void testDeleteParticipant() throws Exception {
		String uri = "/providers/{providerId}/participants/{participantId}";
		mockMvc.perform(delete(uri, "1111111", "56b4694d7eb68a5fff76290c")).andExpect(status().isOk()).andDo(print());

	}

}
