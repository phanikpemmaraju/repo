package uk.gov.dwp.esf.mi.model;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

/*
 *  @Author : Phani Krishna
 *  @Description : Represents Participant object.All of the participants information
 *  			   is stored in this object.
 *  @Version : 1.0
 *  
 *  We have created Setters for the Participant entity because of the Model Mapper.
 *  The Model mapper maps the DTOs to Entity Objects and Vice Versa.
 *  We would require this as we are not storing the Address and Personal details of Participant in the backend.
 * 
 */

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import uk.gov.dwp.esf.mi.common.EntryEmpStatus;
import uk.gov.dwp.esf.mi.common.Ethnicity;
import uk.gov.dwp.esf.mi.common.ExitEmpStatus;
import uk.gov.dwp.esf.mi.common.HouseHoldType;
import uk.gov.dwp.esf.mi.common.ISCEDLevel;
import uk.gov.dwp.esf.mi.common.RecordState;

@Document(collection="participants")
public class Participant {
	@Id
	private String participantId;
	@NotNull
	private Integer providerId;
	@NotNull
	private String nino;
	@NotNull
	private LocalDate dob;	
	private String providerRef;
	private Ethnicity ethnicity;
	private boolean match;
	private boolean disabled;
	private String gender;
	private EntryEmpStatus entryEmpStatus;
	private boolean longTermUnemployed;
	private boolean basicSkills;
	private ISCEDLevel iscedLevel;
	private boolean homeless;
	private boolean alcoholUser;
	private boolean drugUser;	
	private HouseHoldType householdType;
	private LocalDate creationDate;
	private LocalDate updatedDate;
	private RecordState recordState;
	private LocalDate startDate;
	private LocalDate proposedExitDate;
	private LocalDate exitDate;
	@NotNull
	private Integer contractId;
	private ExitEmpStatus exitEmpStatus;
	private boolean exitTraining;
	private boolean exitSkills;
	private boolean exitQualification;
	private boolean exitChildcare;
	private String deliveryPostcode;
	private String signedForm;	
	private String fundingAggrement;
	private String cor;
	private String priorityAxis;
	
	public Participant(){		
	}
	
	public String getParticipantId() {
		return participantId;
	}

	public Integer getProviderId() {
		return providerId;
	}

	public String getProviderRef() {
		return providerRef;
	}
	
	public String getNino() {
		return nino;
	}

	public LocalDate getDob() {
		return dob;
	}
	
	public boolean isMatch() {
		return match;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public Ethnicity getEthnicity() {
		return ethnicity;
	}

	public String getGender() {
		return gender;
	}

	public EntryEmpStatus getEntryEmpStatus() {
		return entryEmpStatus;
	}
	
	
	public boolean isLongTermUnemployed() {
		return longTermUnemployed;
	}


	public boolean isBasicSkills() {
		return basicSkills;
	}

	public ISCEDLevel getISCEDLevel() {
		return iscedLevel;
	}
	
	public boolean isHomeless() {
		return homeless;
	}

	public boolean isAlcoholUser() {
		return alcoholUser;
	}
	
	public boolean isDrugUser() {
		return drugUser;
	}

	public HouseHoldType getHouseholdType() {
		return householdType;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public LocalDate getUpdatedDate() {
		return updatedDate;
	}

	public RecordState getRecordState() {
		return recordState;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getProposedExitDate() {
		return proposedExitDate;
	}

	public LocalDate getExitDate() {
		return exitDate;
	}

	public Integer getContractId() {
		return contractId;
	}

	public ExitEmpStatus getExitEmpStatus() {
		return exitEmpStatus;
	}

	public boolean isExitTraining() {
		return exitTraining;
	}

	public boolean isExitSkills() {
		return exitSkills;
	}

	public boolean isExitQualification() {
		return exitQualification;
	}

	public boolean isExitChildcare() {
		return exitChildcare;
	}

	public String getDeliveryPostcode() {
		return deliveryPostcode;
	}

	public String getSignedForm() {
		return signedForm;
	}
		
	public String getFundingAggrement() {
		return fundingAggrement;
	}

	public String getCor() {
		return cor;
	}

	public String getPriorityAxis() {
		return priorityAxis;
	}

	public void setParticipantId(String participantId) {
		this.participantId = participantId;
	}

	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}

	public void setNino(String nino) {
		this.nino = nino;
	}
	
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public void setProviderRef(String providerRef) {
		this.providerRef = providerRef;
	}
	
	public void setEthnicity(Ethnicity ethnicity) {
		this.ethnicity = ethnicity;
	}

	public void setMatch(boolean match) {
		this.match = match;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setEntryEmpStatus(EntryEmpStatus entryEmpStatus) {
		this.entryEmpStatus = entryEmpStatus;
	}

	public void setLongTermUnemployed(boolean longTermUnemployed) {
		this.longTermUnemployed = longTermUnemployed;
	}

	public void setBasicSkills(boolean basicSkills) {
		this.basicSkills = basicSkills;
	}

	public void setIscedLevel(ISCEDLevel iscedLevel) {
		this.iscedLevel = iscedLevel;
	}

	public void setHomeless(boolean homeless) {
		this.homeless = homeless;
	}

	public void setAlcoholUser(boolean alcoholUser) {
		this.alcoholUser = alcoholUser;
	}

	public void setDrugUser(boolean drugUser) {
		this.drugUser = drugUser;
	}

	public void setHouseholdType(HouseHoldType householdType) {
		this.householdType = householdType;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}

	public void setRecordState(RecordState recordState) {
		this.recordState = recordState;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public void setProposedExitDate(LocalDate proposedExitDate) {
		this.proposedExitDate = proposedExitDate;
	}

	public void setExitDate(LocalDate exitDate) {
		this.exitDate = exitDate;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	public void setExitEmpStatus(ExitEmpStatus exitEmpStatus) {
		this.exitEmpStatus = exitEmpStatus;
	}

	public void setExitTraining(boolean exitTraining) {
		this.exitTraining = exitTraining;
	}

	public void setExitSkills(boolean exitSkills) {
		this.exitSkills = exitSkills;
	}

	public void setExitQualification(boolean exitQualification) {
		this.exitQualification = exitQualification;
	}

	public void setExitChildcare(boolean exitChildcare) {
		this.exitChildcare = exitChildcare;
	}

	public void setDeliveryPostcode(String deliveryPostcode) {
		this.deliveryPostcode = deliveryPostcode;
	}

	public void setSignedForm(String signedForm) {
		this.signedForm = signedForm;
	}

	public void setFundingAggrement(String fundingAggrement) {
		this.fundingAggrement = fundingAggrement;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public void setPriorityAxis(String priorityAxis) {
		this.priorityAxis = priorityAxis;
	}

	
	@Override
	public String toString() {
		return "Participant [participantId=" + participantId + ", nino=" + nino + ", creationDate=" + creationDate
				+ ", updatedDate=" + updatedDate + "]";
	}
	
	private Participant(ParticipantBuilder builder) {
		this.basicSkills = builder.basicSkills;
		this.contractId = builder.contractId;
		this.creationDate = builder.creationDate;
		this.deliveryPostcode = builder.deliveryPostcode;
		this.entryEmpStatus = builder.entryEmpStatus;
		this.longTermUnemployed = builder.longTermUnemployed;
		this.ethnicity = builder.ethnicity;
		this.exitChildcare = builder.exitChildcare;
		this.exitDate = builder.exitDate;
		this.exitEmpStatus = builder.exitEmpStatus;
		this.exitQualification = builder.exitQualification;
		this.exitSkills = builder.exitSkills;
		this.exitTraining = builder.exitTraining;
		this.gender = builder.gender;
		this.householdType = builder.householdType;
		this.iscedLevel = builder.iscedLevel;
		this.disabled = builder.disabled;
		this.match = builder.match;
		this.nino = builder.nino;
		this.participantId = builder.participantId;
		this.proposedExitDate = builder.proposedExitDate;
		this.providerId = builder.providerId;
		this.recordState = builder.recordState;
		this.signedForm = builder.signedForm;
		this.startDate = builder.startDate;
		this.updatedDate = builder.updatedDate;
		this.fundingAggrement=builder.fundingAggrement;
		this.cor=builder.cor;
		this.priorityAxis=builder.priorityAxis;
		this.homeless=builder.homeless;
		this.alcoholUser=builder.alcoholUser;
		this.drugUser=builder.drugUser;
		this.dob = builder.dob;
		this.providerRef = builder.providerRef;
	}
	
	public static class ParticipantBuilder {
		
		private final String participantId;
		private final Integer providerId;
		private final String nino;
		private final LocalDate dob;
		private final Integer contractId;
		private String providerRef;
		private boolean match;
		private boolean disabled;
		private Ethnicity ethnicity;
		private String gender;
		private EntryEmpStatus entryEmpStatus;
		private boolean longTermUnemployed;
		private boolean basicSkills;
		private ISCEDLevel iscedLevel;
		private boolean homeless;
		private boolean alcoholUser;
		private boolean drugUser;
		private HouseHoldType householdType;
		private LocalDate creationDate;
		private LocalDate updatedDate;
		private RecordState recordState;
		private LocalDate startDate;
		private LocalDate proposedExitDate;
		private LocalDate exitDate;
		private ExitEmpStatus exitEmpStatus;
		private boolean exitTraining;
		private boolean exitSkills;
		private boolean exitQualification;
		private boolean exitChildcare;
		private String deliveryPostcode;
		private String signedForm;
		private String fundingAggrement;
		private String cor;
		private String priorityAxis;
				
		public ParticipantBuilder(String participantId, Integer providerId, String nino , LocalDate dob , Integer contractId) {
			this.participantId = participantId;
			this.providerId = providerId;
			this.nino = nino;
			this.dob = dob;
			this.contractId = contractId;
		}

		public ParticipantBuilder isMatch(boolean match) {
			this.match = match;
			return this;
		}
		
		public ParticipantBuilder isDisabled(boolean disabled) {
			this.disabled = disabled;
			return this;
		}
		
		public ParticipantBuilder providerRef(String providerRef) {
			this.providerRef = providerRef;
			return this;
		}
		
		public ParticipantBuilder ethnicity(Ethnicity ethnicity) {
			this.ethnicity = ethnicity;
			return this;
		}
		
		public ParticipantBuilder gender(String gender) {
			this.gender = gender;
			return this;
		}
		
		public ParticipantBuilder entryEmpStatus(EntryEmpStatus entryEmpStatus) {
			this.entryEmpStatus = entryEmpStatus;
			return this;
		}
		
		public ParticipantBuilder longTermUnemployed(boolean longTermUnemployed) {
			this.longTermUnemployed = longTermUnemployed;
			return this;
		}
		
		public ParticipantBuilder basicSkills(boolean basicSkills) {
			this.basicSkills = basicSkills;
			return this;
		}
		
				
		public ParticipantBuilder ISCEDLevel(ISCEDLevel iscedLevel) {
			this.iscedLevel = iscedLevel;
			return this;
		}
						
		public ParticipantBuilder isHomeless(boolean homeless) {
			this.homeless = homeless;
			return this;
		}

		public ParticipantBuilder isAlcoholUser(boolean alcoholUser) {
			this.alcoholUser = alcoholUser;
			return this;
		}

		public ParticipantBuilder isDrugUser(boolean drugUser) {
			this.drugUser = drugUser;
			return this;
		}

		
		public ParticipantBuilder householdType(HouseHoldType householdType) {
			this.householdType = householdType;
			return this;
		}
		
		public ParticipantBuilder creationDate(LocalDate creationDate) {
			this.creationDate = creationDate;
			return this;
		}
		
		public ParticipantBuilder updatedDate(LocalDate updatedDate) {
			this.updatedDate = updatedDate;
			return this;
		}
		
		public ParticipantBuilder recordState(RecordState recordState) {
			this.recordState = recordState;
			return this;
		}
		
		public ParticipantBuilder startDate(LocalDate startDate) {
			this.startDate = startDate;
			return this;
		}
		
		public ParticipantBuilder proposedExitDate(LocalDate proposedExitDate) {
			this.proposedExitDate = proposedExitDate;
			return this;
		}
		
		public ParticipantBuilder exitDate(LocalDate exitDate) {
			this.exitDate = exitDate;
			return this;
		}		
		
		public ParticipantBuilder exitEmpStatus(ExitEmpStatus exitEmpStatus) {
			this.exitEmpStatus = exitEmpStatus;
			return this;
		}
		
		public ParticipantBuilder exitTraining(boolean exitTraining) {
			this.exitTraining = exitTraining;
			return this;
		}
		
		public ParticipantBuilder exitSkills(boolean exitSkills) {
			this.exitSkills = exitSkills;
			return this;
		}
		
		public ParticipantBuilder exitQualification(boolean exitQualification) {
			this.exitQualification = exitQualification;
			return this;
		}
		
		public ParticipantBuilder exitChildcare(boolean exitChildcare) {
			this.exitChildcare = exitChildcare;
			return this;
		}
		
		public ParticipantBuilder deliveryPostcode(String deliveryPostcode) {
			this.deliveryPostcode = deliveryPostcode;
			return this;
		}
		
		public ParticipantBuilder signedForm(String signedForm) {
			this.signedForm = signedForm;
			return this;
		}
		
		public ParticipantBuilder fundingAggrement(String fundingAggrement) {
			this.fundingAggrement = fundingAggrement;
			return this;
		}
		
		public ParticipantBuilder cor(String cor) {
			this.cor = cor;
			return this;
		}
		
		public ParticipantBuilder priorityAxis(String priorityAxis) {
			this.priorityAxis = priorityAxis;
			return this;
		}
		
		public Participant build() {
			Participant participant = new Participant(this);
			//call to validation method
			return participant;
		}
	}
	
	
}
