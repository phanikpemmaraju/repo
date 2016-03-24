package uk.gov.dwp.esf.mi.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import org.junit.Test;

import uk.gov.dwp.esf.mi.common.EntryEmpStatus;
import uk.gov.dwp.esf.mi.common.Ethnicity;
import uk.gov.dwp.esf.mi.common.ExitEmpStatus;
import uk.gov.dwp.esf.mi.common.HouseHoldType;
import uk.gov.dwp.esf.mi.common.ISCEDLevel;
import uk.gov.dwp.esf.mi.common.RecordState;

public class ParticipantTest {
	
	
	/*
	 *  Using Builder Pattern to create Participant default Object
	 */	
	@Test
	public void shouldReturnParticipantBuilderFromFactory() {
		assertThat(new Participant.ParticipantBuilder(null, null, null, null,null),isA(Participant.ParticipantBuilder.class));
	}
	
	/*
	 *  Using Builder Pattern to create Participant default Object
	 */	
	@Test
	public void shouldReturnParticipantFromFactory() {
		assertThat(new Participant.ParticipantBuilder(null, null, null, null,null).build(),isA(Participant.class));
	}
	
	/*
	 *  Using Builder Pattern to create Participant Object with all mandatory fields
	 */
	@Test
	public void buildAParticipantWithMandatoryFields() {
		Participant participant = new Participant.ParticipantBuilder("56dffaa6097d9818d8b455a7", 1111111, "SJ196058B", LocalDate.of(1982, 12, 16), 4001115)
									  .build();
		
		assertThat(participant.getParticipantId(), is("56dffaa6097d9818d8b455a7"));
        assertThat(participant.getProviderId(), is(1111111));
        assertThat(participant.getNino(), is("SJ196058B"));
        assertThat(participant.getDob().toString(), is("1982-12-16"));
        assertThat(participant.getContractId(), is(4001115));        
        assertNotNull(participant.toString());
	}
	
	/*
	 *  Using Builder Pattern to create Participant Object with all fields
	 */
	@Test
	public void buildAParticipantWithAllFields() {
		Participant participant = new Participant.ParticipantBuilder("56dffaa6097d9818d8b455a7", 1111111, "SJ196058B", LocalDate.of(1982, 12, 16), 4001115)
				.ethnicity(Ethnicity.WHITE_ENGLISH).isMatch(true).isAlcoholUser(false).isDisabled(false).gender("MALE")
				.entryEmpStatus(EntryEmpStatus.JOBSEEKING).longTermUnemployed(false).basicSkills(true).ISCEDLevel(ISCEDLevel.THREE)
				.isHomeless(false).isDrugUser(false).householdType(HouseHoldType.JOBLESS_HOUSEHOLD)
				.creationDate(LocalDate.now()).updatedDate(LocalDate.now())
				.startDate(LocalDate.now().plusDays(14)).proposedExitDate(LocalDate.now().plusDays(74)).exitDate(LocalDate.now().plusDays(75))
				.exitTraining(false).exitSkills(false).exitQualification(false).exitChildcare(false)
				.deliveryPostcode("S1 4GG").signedForm("signed").fundingAggrement("funds").recordState(RecordState.INCOMPLETE)
				.cor("cor").priorityAxis("priority").exitEmpStatus(ExitEmpStatus.JOBSEEKING).build();
		
		assertThat(participant.getParticipantId(), is("56dffaa6097d9818d8b455a7"));
		assertThat(participant.getCreationDate(), is(LocalDate.now()));
		assertThat(participant.getUpdatedDate(), is(LocalDate.now()));
		assertThat(participant.getProposedExitDate(), is(LocalDate.now().plusDays(74)));
		assertThat(participant.getStartDate(), is(LocalDate.now().plusDays(14)));
		assertThat(participant.getExitDate(), is(LocalDate.now().plusDays(75)));
		
		
		assertThat(participant.getGender(), is("MALE"));
		assertThat(participant.getDeliveryPostcode(), is("S1 4GG"));
		assertThat(participant.getSignedForm(), is("signed"));
		assertThat(participant.getFundingAggrement(), is("funds"));
		assertThat(participant.getCor(), is("cor"));
		assertThat(participant.getPriorityAxis(), is("priority"));

		assertThat(participant.getEthnicity(), is(Ethnicity.WHITE_ENGLISH));
		assertThat(participant.getEntryEmpStatus(), is(EntryEmpStatus.JOBSEEKING));
		assertThat(participant.getHouseholdType(), is(HouseHoldType.JOBLESS_HOUSEHOLD));
		assertThat(participant.getISCEDLevel(), is(ISCEDLevel.THREE));
		assertThat(participant.getExitEmpStatus(), is(ExitEmpStatus.JOBSEEKING));
		assertThat(participant.getRecordState(), is(RecordState.INCOMPLETE));
		
		assertThat(participant.isMatch(), is(true));
		assertThat(participant.isAlcoholUser(), is(false));
		assertThat(participant.isDisabled(), is(false));
		assertThat(participant.isLongTermUnemployed(), is(false));
		assertThat(participant.isBasicSkills(), is(true));
		
		assertThat(participant.isHomeless(), is(false));
		assertThat(participant.isDrugUser(), is(false));
		assertThat(participant.isExitTraining(), is(false));
		assertThat(participant.isExitSkills(), is(false));
		assertThat(participant.isExitQualification(), is(false));
		assertThat(participant.isExitChildcare(), is(false));				

	}
	
	
	/*
	 *  Test Participant Builder object
	 */
	@Test
	public void buildParticipantBuilderWithAllFields() {
		Participant.ParticipantBuilder builder = new Participant.ParticipantBuilder("56dffaa6097d9818d8b455a7", 1111111, "SJ196058B", LocalDate.of(1982, 12, 16), 4001115)
				.ethnicity(Ethnicity.WHITE_ENGLISH).isMatch(true).isAlcoholUser(false).isDisabled(false).gender("MALE")
				.entryEmpStatus(EntryEmpStatus.JOBSEEKING).longTermUnemployed(false).basicSkills(true).ISCEDLevel(ISCEDLevel.THREE)
				.isHomeless(false).isDrugUser(false).householdType(HouseHoldType.JOBLESS_HOUSEHOLD)
				.creationDate(LocalDate.now()).updatedDate(LocalDate.now())
				.startDate(LocalDate.now().plusDays(14)).proposedExitDate(LocalDate.now().plusDays(74)).exitDate(LocalDate.now().plusDays(75))
				.exitTraining(false).exitSkills(false).exitQualification(false).exitChildcare(false)
				.deliveryPostcode("S1 4GG").signedForm("signed").fundingAggrement("funds").recordState(RecordState.INCOMPLETE)
				.cor("cor").priorityAxis("priority").exitEmpStatus(ExitEmpStatus.JOBSEEKING);
		
		assertNotNull(builder);
	}
	
	
	/*
	 *  Create the Participant object using Setters.
	 *  We have created Setters for the Participant entity because of the Model Mapper.
	 *  The Model mapper maps the DTOs to Entity Objects and Vice Versa.
	 *  We would require this as we are not storing the Address and Personal details of Participant in the backend.
	 */
	@Test
	public void buildParticipant() {
		final Participant participant = new Participant();
		participant.setParticipantId("56dffaa6097d9818d8b455a7");participant.setProviderId(1111111);
		participant.setContractId(4001115);participant.setNino("SJ1960589");participant.setDob(LocalDate.of(1982, 12, 16));
		participant.setCreationDate(LocalDate.now());participant.setUpdatedDate(LocalDate.now());participant.setRecordState(RecordState.INCOMPLETE);
		
		// Set Participant details for Full Participant Json View
		participant.setEthnicity(Ethnicity.WHITE_ENGLISH);participant.setExitEmpStatus(ExitEmpStatus.JOBSEEKING);
		participant.setMatch(true);participant.setAlcoholUser(false);participant.setDisabled(false);
		participant.setGender("MALE");participant.setEntryEmpStatus(EntryEmpStatus.JOBSEEKING);participant.setLongTermUnemployed(false);
		participant.setBasicSkills(true);participant.setIscedLevel(ISCEDLevel.THREE);participant.setHomeless(false);
		participant.setDrugUser(false);participant.setHouseholdType(HouseHoldType.JOBLESS_HOUSEHOLD);
		participant.setStartDate(LocalDate.now().plusDays(14));participant.setProposedExitDate(LocalDate.now().plusDays(74));participant.setExitDate(LocalDate.now().plusDays(75));
		participant.setExitTraining(false);participant.setExitSkills(false);participant.setExitQualification(false);participant.setExitChildcare(false);
		participant.setDeliveryPostcode("S1 4GG");participant.setSignedForm("signed");participant.setFundingAggrement("funds");
		participant.setCor("cor");participant.setPriorityAxis("priority");
				
		assertNotNull(participant);
		assertThat(participant.getPriorityAxis(), is("priority"));
	}
	
}
