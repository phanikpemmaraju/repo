package uk.gov.dwp.esf.mi.dtos;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import uk.gov.dwp.esf.mi.common.Address;
import uk.gov.dwp.esf.mi.common.EntryEmpStatus;
import uk.gov.dwp.esf.mi.common.Ethnicity;
import uk.gov.dwp.esf.mi.common.ExitEmpStatus;
import uk.gov.dwp.esf.mi.common.HouseHoldType;
import uk.gov.dwp.esf.mi.common.ISCEDLevel;
import uk.gov.dwp.esf.mi.common.RecordState;
import uk.gov.dwp.esf.mi.common.View;

public class ParticipantDTO {	
	@JsonView(View.SummaryWithBasicParticipants.class)
	private String participantId;
	@NotNull(message="error.providerId.null")
	@JsonView(View.SummaryWithBasicParticipants.class)
	private Integer providerId;
	@JsonView(View.SummaryWithBasicParticipants.class)
	private String providerRef;
	@JsonView(View.SummaryWithFullParticipants.class)
	private String title;
	@JsonView(View.SummaryWithFullParticipants.class)
	private String surname;
	@JsonView(View.SummaryWithFullParticipants.class)
	private String firstname;
	@JsonView(View.SummaryWithFullParticipants.class)
	private Address address;
	@JsonView(View.SummaryWithFullParticipants.class)
	private String phoneNo;
	@NotNull(message="error.nino.null")
	@Pattern(regexp="^[A-Z]{2}[0-9]{6}[A-D]{1}$",message="error.invalid.nino.format")
	@JsonView(View.SummaryWithBasicParticipants.class)
	private String nino;
	@NotNull(message="error.dob.null")
	@JsonView(View.SummaryWithBasicParticipants.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dob;
	@JsonView(View.SummaryWithFullParticipants.class)
	private Ethnicity ethnicity;
	@JsonView(View.SummaryWithFullParticipants.class)
	private boolean match;
	@JsonView(View.SummaryWithFullParticipants.class)
	private boolean disabled;
	@JsonView(View.SummaryWithFullParticipants.class)
	private String gender;
	@JsonView(View.SummaryWithFullParticipants.class)
	private EntryEmpStatus entryEmpStatus;
	@JsonView(View.SummaryWithFullParticipants.class)
	private boolean longTermUnemployed;
	@JsonView(View.SummaryWithFullParticipants.class)
	private boolean basicSkills;
	@JsonView(View.SummaryWithFullParticipants.class)
	private ISCEDLevel iscedLevel;
	@JsonView(View.SummaryWithFullParticipants.class)
	private boolean homeless;
	@JsonView(View.SummaryWithFullParticipants.class)
	private boolean alcoholUser;
	@JsonView(View.SummaryWithFullParticipants.class)
	private boolean drugUser;	
	@JsonView(View.SummaryWithFullParticipants.class)
	private HouseHoldType householdType;
	@JsonView(View.SummaryWithBasicParticipants.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate creationDate;
	@JsonView(View.SummaryWithBasicParticipants.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate updatedDate;
	@JsonView(View.SummaryWithBasicParticipants.class)
	private RecordState recordState;
	@JsonView(View.SummaryWithFullParticipants.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate startDate;
	@JsonView(View.SummaryWithFullParticipants.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate proposedExitDate;
	@JsonView(View.SummaryWithFullParticipants.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate exitDate;
	@NotNull(message="error.contractId.null")
	@JsonView(View.SummaryWithBasicParticipants.class)
	private Integer contractId;
	@JsonView(View.SummaryWithFullParticipants.class)
	private ExitEmpStatus exitEmpStatus;
	@JsonView(View.SummaryWithFullParticipants.class)
	private boolean exitTraining;
	@JsonView(View.SummaryWithFullParticipants.class)
	private boolean exitSkills;
	@JsonView(View.SummaryWithFullParticipants.class)
	private boolean exitQualification;
	@JsonView(View.SummaryWithFullParticipants.class)
	private boolean exitChildcare;
	@JsonView(View.SummaryWithFullParticipants.class)
	private String deliveryPostcode;
	@JsonView(View.SummaryWithFullParticipants.class)
	private String signedForm;	
	@JsonView(View.SummaryWithFullParticipants.class)
	private String fundingAggrement;
	@JsonView(View.SummaryWithFullParticipants.class)
	private String cor;
	@JsonView(View.SummaryWithFullParticipants.class)
	private String priorityAxis;
	
	public String getParticipantId() {
		return participantId;
	}
	public void setParticipantId(String participantId) {
		this.participantId = participantId;
	}
	public Integer getProviderId() {
		return providerId;
	}
	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}		
	
	public String getProviderRef() {
		return providerRef;
	}
	public void setProviderRef(String providerRef) {
		this.providerRef = providerRef;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getNino() {
		return nino;
	}
	public void setNino(String nino) {
		this.nino = nino;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public Ethnicity getEthnicity() {
		return ethnicity;
	}
	public void setEthnicity(Ethnicity ethnicity) {
		this.ethnicity = ethnicity;
	}
	public boolean isMatch() {
		return match;
	}
	public void setMatch(boolean match) {
		this.match = match;
	}
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public EntryEmpStatus getEntryEmpStatus() {
		return entryEmpStatus;
	}
	public void setEntryEmpStatus(EntryEmpStatus entryEmpStatus) {
		this.entryEmpStatus = entryEmpStatus;
	}
	public boolean isLongTermUnemployed() {
		return longTermUnemployed;
	}
	public void setLongTermUnemployed(boolean longTermUnemployed) {
		this.longTermUnemployed = longTermUnemployed;
	}
	public boolean isBasicSkills() {
		return basicSkills;
	}
	public void setBasicSkills(boolean basicSkills) {
		this.basicSkills = basicSkills;
	}
	public ISCEDLevel getIscedLevel() {
		return iscedLevel;
	}
	public void setIscedLevel(ISCEDLevel iscedLevel) {
		this.iscedLevel = iscedLevel;
	}
	public boolean isHomeless() {
		return homeless;
	}
	public void setHomeless(boolean homeless) {
		this.homeless = homeless;
	}
	public boolean isAlcoholUser() {
		return alcoholUser;
	}
	public void setAlcoholUser(boolean alcoholUser) {
		this.alcoholUser = alcoholUser;
	}
	public boolean isDrugUser() {
		return drugUser;
	}
	public void setDrugUser(boolean drugUser) {
		this.drugUser = drugUser;
	}
	public HouseHoldType getHouseholdType() {
		return householdType;
	}
	public void setHouseholdType(HouseHoldType householdType) {
		this.householdType = householdType;
	}
	public LocalDate getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}
	public LocalDate getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}
	public RecordState getRecordState() {
		return recordState;
	}
	public void setRecordState(RecordState recordState) {
		this.recordState = recordState;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getProposedExitDate() {
		return proposedExitDate;
	}
	public void setProposedExitDate(LocalDate proposedExitDate) {
		this.proposedExitDate = proposedExitDate;
	}
	public LocalDate getExitDate() {
		return exitDate;
	}
	public void setExitDate(LocalDate exitDate) {
		this.exitDate = exitDate;
	}
	public Integer getContractId() {
		return contractId;
	}
	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}
	public ExitEmpStatus getExitEmpStatus() {
		return exitEmpStatus;
	}
	public void setExitEmpStatus(ExitEmpStatus exitEmpStatus) {
		this.exitEmpStatus = exitEmpStatus;
	}
	public boolean isExitTraining() {
		return exitTraining;
	}
	public void setExitTraining(boolean exitTraining) {
		this.exitTraining = exitTraining;
	}
	public boolean isExitSkills() {
		return exitSkills;
	}
	public void setExitSkills(boolean exitSkills) {
		this.exitSkills = exitSkills;
	}
	public boolean isExitQualification() {
		return exitQualification;
	}
	public void setExitQualification(boolean exitQualification) {
		this.exitQualification = exitQualification;
	}
	public boolean isExitChildcare() {
		return exitChildcare;
	}
	public void setExitChildcare(boolean exitChildcare) {
		this.exitChildcare = exitChildcare;
	}
	public String getDeliveryPostcode() {
		return deliveryPostcode;
	}
	public void setDeliveryPostcode(String deliveryPostcode) {
		this.deliveryPostcode = deliveryPostcode;
	}
	public String getSignedForm() {
		return signedForm;
	}
	public void setSignedForm(String signedForm) {
		this.signedForm = signedForm;
	}
	public String getFundingAggrement() {
		return fundingAggrement;
	}
	public void setFundingAggrement(String fundingAggrement) {
		this.fundingAggrement = fundingAggrement;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public String getPriorityAxis() {
		return priorityAxis;
	}
	public void setPriorityAxis(String priorityAxis) {
		this.priorityAxis = priorityAxis;
	}
		
	@Override
	public String toString() {
		return "ParticipantDTO [participantId=" + participantId + ", providerId=" + providerId + ", nino=" + nino
				+  ", contractId=" + contractId + ", creationDate=" + creationDate + ", updatedDate=" + updatedDate
				+ ", recordState =" + recordState + "]";
	}

	
	
}
