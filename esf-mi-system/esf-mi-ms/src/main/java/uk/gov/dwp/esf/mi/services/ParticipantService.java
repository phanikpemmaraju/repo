package uk.gov.dwp.esf.mi.services;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import uk.gov.dwp.esf.mi.common.Address;
import uk.gov.dwp.esf.mi.common.RecordState;
import uk.gov.dwp.esf.mi.model.Participant;
import uk.gov.dwp.esf.mi.model.Provider;
//import uk.gov.dwp.esf.mi.model.QParticipant;
import uk.gov.dwp.esf.mi.dtos.ParticipantDTO;
import uk.gov.dwp.esf.mi.exceptions.DataException;
import uk.gov.dwp.esf.mi.exceptions.PredicateException;
import uk.gov.dwp.esf.mi.commons.PredicatesBuilder;
import uk.gov.dwp.esf.mi.assemblers.ParticipantResourceAssembler;
import uk.gov.dwp.esf.mi.assemblers.ProviderResourceAssembler;
import uk.gov.dwp.esf.mi.resources.ProviderResources;
import uk.gov.dwp.esf.mi.resources.ParticipantResource;
import uk.gov.dwp.esf.mi.resources.ParticipantResources;
import uk.gov.dwp.esf.mi.resources.ProviderResource;
import uk.gov.dwp.esf.mi.repositories.ParticipantRepository;
import uk.gov.dwp.esf.mi.repositories.ProviderRepository;
import org.modelmapper.ModelMapper;
import com.mysema.query.types.expr.BooleanExpression;

//Logger Imports
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@EnableMongoRepositories(basePackages = "uk.gov.dwp.esf")
@Transactional
public class ParticipantService {
	
	private ModelMapper modelMapper;
	private RestTemplate restTemplate;

	public ParticipantService(){
		modelMapper = new ModelMapper();
		restTemplate = new RestTemplate();
	}

	@Value("${server.port}")
	private int port;
	
	@Autowired
	private ProviderRepository providerRepository;
	
	@Autowired
	private ParticipantRepository participantRepository;

	@Autowired
	private ProviderResourceAssembler providerResourceAssembler;

	@Autowired
	private ParticipantResourceAssembler participantResourceAssembler;

	@Autowired
	private PredicatesBuilder builder;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/*
	 * Retrieves all providers.
	 * 
	 */
	public ProviderResources getProviders(final Pageable pageable, final String requestURI) {
		return providerResourceAssembler.toResourcesForProvider(providerRepository.findAll(pageable), pageable,
				requestURI);

	}
	
	/*
	 * Retrieves provider information based on providerId.
	 * 
	 */
	public List<ProviderResource> getProviderById(final Integer providerId, final Pageable pageable,
			final String requestURI) {
		List<ProviderResource> providerResources = null;
		final Page<Provider> providers = providerRepository.findByProviderId(providerId, pageable);
		providerResources = (providers == null || providers.getContent().size() == 0) ? providerResources
				: providerResourceAssembler.toResourcesForProviders(providers, requestURI);
		return providerResources;
	}

	/*
	 * Retrieves participant information based on providerId.
	 * 
	 */
	public ParticipantResources getParticipantsByProviderId(final Integer providerId, final Pageable pageable,
			final String requestURI) {
		ParticipantResources participantResources = null;
		final Page<Participant> participants = participantRepository.findByProviderId(providerId, pageable);
		participantResources = (participants == null || participants.getContent().size() == 0) ? participantResources
				: participantResourceAssembler.toResourcesForParticipants(modelMapper, participants, pageable,
						requestURI);
		return participantResources;
	}

	/*
	 * Retrieves participant information based on providerId and participantId.
	 * 
	 */
	public List<ParticipantResource> getParticipantsByProviderIdAndParticipantId(final Integer providerId,
			final String participantId, final Pageable pageable, final String requestURI) {
		List<ParticipantResource> participantResources = null;
		final Page<Participant> participants = participantRepository.findByProviderIdAndParticipantId(providerId,
				participantId, pageable);
		participantResources = (participants == null || participants.getContent().size() == 0) ? participantResources
				: participantResourceAssembler.toResourcesForAParticipant(modelMapper, participants, requestURI);
		return participantResources;
	}

	/*
	 * Retrieves participant information based on Nino provided.
	 * 
	 */
	public ParticipantResources getParticipantsByNiNo(final String nino, final Pageable pageable,
			final String requestURI) {
		ParticipantResources participantResources = null;
		final Page<Participant> participants = participantRepository.findByNino(nino, pageable);
		participantResources = (participants == null || participants.getContent().size() == 0) ? participantResources
				: participantResourceAssembler.toResourcesForParticipants(modelMapper, participants, pageable,
						requestURI);
		return participantResources;
	}

	/*
	 * Retrieves all participants based on the filtering criteria.
	 * 
	 */
	public ParticipantResources getFilteredParticipants(final String filter, final Pageable pageable,
			final String requestURI) throws PredicateException {
		ParticipantResources participantResources = null;
		BooleanExpression expression = builder.createPredicateBuilder(filter);
		final Page<Participant> participants = participantRepository.findAll(expression, pageable);
		participantResources = (participants == null || participants.getContent().size() == 0) ? participantResources
				: participantResourceAssembler.toResourcesForParticipants(modelMapper, participants, pageable,
						requestURI);
		return participantResources;
	}

	/*
	 * Create a new Participant in the database based on the Participant DTO
	 * object provided.
	 * 
	 */
	public List<ParticipantResource> createParticipant(ParticipantDTO participantDTO, final String requestURI)
			throws DataException {
		// Check whether the given contractId and nino exists in the database.If exists, then throw data exception
		final List<Participant> participants = validateParticipant(participantDTO.getContractId(),participantDTO.getNino());				
		if(participants != null && participants.size() > 0)
			throw new DataException("error.participant.found","Participant already exists");
		
		List<ParticipantResource> participantResources = null;
		Address address = getAddress(participantDTO.getNino(), participantDTO.getDob());
		participantDTO = mapParticipantDetails(participantDTO);
		final Participant esfParticipant = participantRepository.save(modelMapper.map(participantDTO, Participant.class));
		if (esfParticipant != null) {
			// Call to CIS to get Address and other details
			mapCitizenDetails(participantDTO, address, "Mr", "Geoff", "Davies", "+44 7948291591");
		}

		participantResources = (esfParticipant == null) ? participantResources
				: participantResourceAssembler.toResourcesForAParticipant(modelMapper, participantDTO,
						esfParticipant.getParticipantId(), requestURI);
		return participantResources;
	}

	/*
	 * Update the existing Participant in the database based on the Participant
	 * DTO object provided.
	 * 
	 */
	public List<ParticipantResource> updateParticipant(ParticipantDTO participantDTO, final String requestURI) {
		List<ParticipantResource> participantResources = null;
		Participant esfParticipant = participantRepository.findByParticipantId(participantDTO.getParticipantId());
		esfParticipant = (esfParticipant == null) ? esfParticipant
				: participantRepository.save(modelMapper.map(participantDTO, Participant.class));
		participantResources = (esfParticipant == null) ? participantResources
				: participantResourceAssembler.toResourcesForAParticipant(modelMapper, participantDTO,
						esfParticipant.getParticipantId(), requestURI);
		return participantResources;
	}

	/*
	 * Delete the Participant record from the database based on the participant
	 * Id.
	 * 
	 */
	public void deleteParticipant(final String participantId) {
		participantRepository.delete(participantId);
	}
	
	
	
	/*
	 * A call to CIS web service and populate the Address object details from
	 * the CIS response.
	 * 
	 */
	private List<Participant> validateParticipant(Integer contractId , String nino) throws DataException {
		List<Participant> participants = participantRepository.findByContractIdAndNino(contractId, nino);
		return participants;
	}

	/*
	 * A call to CIS web service and populate the Address object details from
	 * the CIS response.
	 * 
	 */
	private Address getAddress(String nino, LocalDate dob) throws DataException {
		Address address = null;
		
		if (!nino.equalsIgnoreCase("SJ196058B"))
			throw new DataException("error.nino.not.found","Nino " + nino + " not found");
		
		if (!dob.equals(LocalDate.of(1982, 12, 16)))
			throw new DataException("error.dob.not.found","DOB " + dob.toString() + " not found");
		
		if (nino.equalsIgnoreCase("SJ196058B") && dob.equals(LocalDate.of(1982, 12, 16)))
			address = new Address.AddressBuilder("Apartment 37", "Sheffield", "S1 4GG").secondLine("Royal Plaza")
					.street("Dyche Street").district("South Yorkshire").country("UK").email("geoff.davies@gmail.com")
					.build();

		return address;
	}

	/*
	 * Populate the Creation Date, Updated Date and Status values for the
	 * Participant DTO object.
	 * 
	 */
	private ParticipantDTO mapParticipantDetails(ParticipantDTO participant) {
		participant.setCreationDate(LocalDate.now());
		participant.setUpdatedDate(LocalDate.now());
		participant.setRecordState(RecordState.INCOMPLETE);
		return participant;
	}

	/*
	 * Map participant details for the Participant DTO object.
	 * 
	 */
	private void mapCitizenDetails(ParticipantDTO participantDTO, Address address, String title, String firstName,
			String surname, String phoneNo) {
		participantDTO.setAddress(address);
		participantDTO.setTitle(title);
		participantDTO.setFirstname(firstName);
		participantDTO.setSurname(surname);
		participantDTO.setPhoneNo(phoneNo);
	}

	/*
	 * Calculates the Age between two dates.
	 * 
	 */
	@SuppressWarnings("unused")
	private int getAge(final LocalDate startDate, final LocalDate dob) {
		int difference = 0;
		// Check if startDate provided is before the dob
		if (startDate.isBefore(dob))
			return -1;

		final LocalDate nextBDay = dob.withYear(startDate.getYear());
		if (nextBDay.isBefore(startDate) || nextBDay.equals(startDate))
			difference = startDate.compareTo(dob);
		else
			difference = startDate.compareTo(dob) - 1;

		return difference;
	}

	/*
	 * Sample Code to invoke Rest controller multiple times to get Reporting data.
	 * 
	 */
	public void checkWorks(Pageable pageable) {
		logger.info(">>> Start of Process");
		//QParticipant participant = new QParticipant("participant");
		String test = "creationDate_between=1985-01-16:1988-12-02";
		StringBuilder url = new StringBuilder("http://localhost:");
		url.append(port); url.append("/providers/1111111/participants/search/"); url.append(test);
		
		final String baseUrl = url.toString();
		//url.append(pageable.toString());

		if (pageable != null) {
			url.append("?page=");
			url.append(pageable.getPageNumber());
			url.append("&offset=");
			url.append(pageable.getOffset());
			url.append("&size=");
			url.append(pageable.getPageSize());

			if (pageable.getSort() != null) {
				url.append("&sort=");
				url.append(pageable.getSort().toString());
			}
		}
		
		logger.info(">>> url " + url.toString());

		ParticipantResources resources = restTemplate.getForObject(url.toString(), ParticipantResources.class);
						
		while(resources != null){
			url.setLength(0); url = new StringBuilder();
			resources.getParticipants().forEach(resource -> logger.info(resource.getParticipant().getParticipantId()));
			Iterator<Link> link = resources.getLinks().iterator();
			while(link.hasNext()){
				Link link1 = link.next();
				if(link1.getRel().toString().equalsIgnoreCase("participants.next")){
					url.append(baseUrl);url.append(link1.getHref().substring(link1.getHref().indexOf('?')));
					logger.info(">>> now url : " + url.toString());
				}
			}
			
			resources = (url.length() == 0) ? null : restTemplate.getForObject(url.toString(), ParticipantResources.class);			
		}
		
		logger.info(">>> End of Process");

	}

}
