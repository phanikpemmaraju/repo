package uk.gov.dwp.esf.mi.dtos;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import uk.gov.dwp.esf.mi.common.Address;
import uk.gov.dwp.esf.mi.common.EntryEmpStatus;
import uk.gov.dwp.esf.mi.common.Ethnicity;
import uk.gov.dwp.esf.mi.common.ExitEmpStatus;
import uk.gov.dwp.esf.mi.common.HouseHoldType;
import uk.gov.dwp.esf.mi.common.ISCEDLevel;
import uk.gov.dwp.esf.mi.common.RecordState;
import uk.gov.dwp.esf.mi.common.View;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ParticipantDTOTest {
	
	private Validator validator;
	private ObjectMapper mapper;
	private SimpleDateFormat dateFormat;

	@Before
	public void setup() {
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
		this.validator = vf.getValidator();
		this.mapper = new ObjectMapper();
		this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}
	
	@Test
	public void validationsTest() throws ParseException{
		// Invalid NI
		ParticipantDTO participant = new ParticipantDTO();
		participant.setParticipantId("56dffaa6097d9818d8b455a7");participant.setProviderId(1111111);
		participant.setContractId(4001115);participant.setNino("SJ1960589");participant.setDob(dateFormat.parse("1982-12-16"));
		participant.setCreationDate(dateFormat.parse(dateFormat.format(new Date())));participant.setUpdatedDate(dateFormat.parse(dateFormat.format(new Date())));participant.setRecordState(RecordState.INCOMPLETE);
		Set<ConstraintViolation<ParticipantDTO>> violations = this.validator.validate(participant);
		assertEquals(1, violations.size());
		assertEquals("error.invalid.nino.format", violations.iterator().next().getMessage());
		
		// Invalid Contract Id
		participant.setNino("SJ196058B");participant.setContractId(null);
		violations = this.validator.validate(participant);
		assertEquals(1, violations.size());
		assertEquals("error.contractId.null", violations.iterator().next().getMessage());
		participant.setContractId(4001115);participant.setProviderId(null);
		
		// Invalid Provider Id
		violations = this.validator.validate(participant);
		assertEquals(1, violations.size());
		assertEquals("error.providerId.null", violations.iterator().next().getMessage());
		participant.setProviderId(1111111);participant.setDob(null);
		
		// Invalid DOB Id
		violations = this.validator.validate(participant);
		assertEquals(1, violations.size());
		assertEquals("error.dob.null", violations.iterator().next().getMessage());
		
		// Valid NI
		participant.setDob(dateFormat.parse("1982-12-16"));
		violations = this.validator.validate(participant);
		assertEquals(0, violations.size());
			
		// Test the toString() method
		final String value = "ParticipantDTO [participantId=56dffaa6097d9818d8b455a7, providerId=1111111, nino=SJ196058B, contractId=4001115, creationDate=" + dateFormat.parse(dateFormat.format(new Date())) + ", updatedDate=" + dateFormat.parse(dateFormat.format(new Date())).toString() + ", recordState =INCOMPLETE]";
		assertThat(participant.toString(), is(value));
	}
	
	@Test
	public void basicParticipantsViewTest() throws IOException, ParseException{
		// Set Participant details for Basic Participant Json View
		final ParticipantDTO participant = new ParticipantDTO();
		participant.setParticipantId("56dffaa6097d9818d8b455a7");participant.setProviderId(1111111);
		participant.setContractId(4001115);participant.setNino("SJ1960589");participant.setDob(dateFormat.parse("1982-12-16"));
		participant.setCreationDate(dateFormat.parse(dateFormat.format(new Date())));participant.setUpdatedDate(dateFormat.parse(dateFormat.format(new Date())));participant.setRecordState(RecordState.INCOMPLETE);
		
		participant.setAlcoholUser(false);
		
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		
		String participantView =  mapper.writerWithView(View.SummaryWithBasicParticipants.class).writeValueAsString(participant);
		String viewValue = "{\"participantId\":\"56dffaa6097d9818d8b455a7\",\"providerId\":1111111,\"nino\":\"SJ1960589\",\"dob\":\"1982-12-16\",\"creationDate\":\"" + LocalDate.now().toString() + "\",\"updatedDate\":\"" + LocalDate.now().toString() + "\",\"recordState\":\"INCOMPLETE\",\"contractId\":4001115}";
		assertEquals(viewValue, participantView);		
	}
	@Test
	public void fullParticipantsViewTest() throws IOException, ParseException{
		final ParticipantDTO participant = new ParticipantDTO();
		participant.setParticipantId("56dffaa6097d9818d8b455a7");participant.setProviderId(1111111);
		participant.setContractId(4001115);participant.setNino("SJ1960589");participant.setDob(dateFormat.parse("1982-12-16"));
		participant.setCreationDate(dateFormat.parse(dateFormat.format(new Date())));participant.setUpdatedDate(dateFormat.parse(dateFormat.format(new Date())));participant.setRecordState(RecordState.INCOMPLETE);
		
		// Create Address object		
		final Address address = new Address.AddressBuilder("Apartment 33","Sheffield","S1 4GG")
				.country("UK").district("South Yorkshire").email("abc@gmail.com").secondLine("Royal Plaza").street("Rockingham Street")
                .build();
		
		// Set Participant details for Full Participant Json View
		participant.setTitle("Mr");participant.setSurname("Davies");participant.setFirstname("Geoff");
		participant.setAddress(address);participant.setPhoneNo("+44 7948291591");participant.setEthnicity(Ethnicity.WHITE_ENGLISH);
		participant.setMatch(true);participant.setAlcoholUser(false);participant.setDisabled(false);
		participant.setGender("MALE");participant.setEntryEmpStatus(EntryEmpStatus.JOBSEEKING);participant.setLongTermUnemployed(false);
		participant.setBasicSkills(true);participant.setIscedLevel(ISCEDLevel.THREE);participant.setHomeless(false);
		participant.setDrugUser(false);participant.setHouseholdType(HouseHoldType.JOBLESS_HOUSEHOLD);
		participant.setStartDate(dateFormat.parse(LocalDate.now().plusDays(14).toString()));participant.setProposedExitDate(dateFormat.parse(LocalDate.now().plusDays(74).toString()));participant.setExitDate(dateFormat.parse(LocalDate.now().plusDays(75).toString()));
		participant.setExitTraining(false);participant.setExitSkills(false);participant.setExitQualification(false);participant.setExitChildcare(false);
		participant.setDeliveryPostcode("S1 4GG");participant.setSignedForm("signed");participant.setFundingAggrement("funds");
		participant.setCor("cor");participant.setPriorityAxis("priority");participant.setExitEmpStatus(ExitEmpStatus.JOBSEEKING);
		
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		
		String participantView =  mapper.writerWithView(View.SummaryWithFullParticipants.class).writeValueAsString(participant);
		String viewValue = "{\"participantId\":\"56dffaa6097d9818d8b455a7\",\"providerId\":1111111,\"title\":\"Mr\",\"surname\":\"Davies\",\"firstname\":\"Geoff\",\"address\":{\"firstLine\":\"Apartment 33\",\"secondLine\":\"Royal Plaza\",\"street\":\"Rockingham Street\",\"district\":\"South Yorkshire\",\"city\":\"Sheffield\",\"postcode\":\"S1 4GG\",\"country\":\"UK\",\"email\":\"abc@gmail.com\"},\"phoneNo\":\"+44 7948291591\",\"nino\":\"SJ1960589\",\"dob\":\"1982-12-16\",\"ethnicity\":\"WHITE_ENGLISH\",\"match\":true,\"disabled\":false,\"gender\":\"MALE\",\"entryEmpStatus\":\"JOBSEEKING\",\"longTermUnemployed\":false,\"basicSkills\":true,\"iscedLevel\":\"THREE\",\"homeless\":false,\"alcoholUser\":false,\"drugUser\":false,\"householdType\":\"JOBLESS_HOUSEHOLD\",\"creationDate\":\"" + LocalDate.now().toString() + "\",\"updatedDate\":\"" + LocalDate.now().toString() + "\",\"recordState\":\"INCOMPLETE\",\"startDate\":\"" + LocalDate.now().plusDays(14).toString() + "\",\"proposedExitDate\":\"" + LocalDate.now().plusDays(74).toString() + "\",\"exitDate\":\"" + LocalDate.now().plusDays(75).toString() + "\",\"contractId\":4001115,\"exitEmpStatus\":\"JOBSEEKING\",\"exitTraining\":false,\"exitSkills\":false,\"exitQualification\":false,\"exitChildcare\":false,\"deliveryPostcode\":\"S1 4GG\",\"signedForm\":\"signed\",\"fundingAggrement\":\"funds\",\"cor\":\"cor\",\"priorityAxis\":\"priority\"}";
		assertEquals(viewValue, participantView);
	}

}
