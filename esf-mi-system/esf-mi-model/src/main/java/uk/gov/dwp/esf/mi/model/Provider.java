package uk.gov.dwp.esf.mi.model;

/*
 *  @Author : Phani Krishna
 *  @Description : Represents ESFProvider object which has associations with 
 *  			   ESFParticipant and ESFContract objects.
 *  @Version : 1.0
 * 
 */

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonView;

import uk.gov.dwp.esf.mi.common.View;

@Document(collection="providers")
public class Provider {
	
	@Id
	@JsonView(View.Summary.class)
	private Integer providerId;
	@JsonView(View.Summary.class)
	private String providerName;
	@JsonView(View.SummaryWithBasicParticipants.class)
	private List<Participant> participants;
	@JsonView(View.Summary.class)
	private String postcode;
	@JsonView(View.Summary.class)
	private String email;
	
	public Integer getProviderId() {
		return providerId;
	}
	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	
	public List<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}
	
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((providerId == null) ? 0 : providerId.hashCode());
		result = prime * result + ((providerName == null) ? 0 : providerName.hashCode());
		result = prime * result + ((postcode == null) ? 0 : postcode.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if(obj instanceof Provider){
			if(!providerId.equals(((Provider)obj).providerId) && !providerName.equals(((Provider)obj).providerName) && !postcode.equals(((Provider)obj).postcode) && !email.equals(((Provider)obj).email))
				return false;
		}
		
		return true;
	}
	
	
	@Override
	public String toString() {
		return "ESFProvider [providerId=" + providerId + ", providerName=" + providerName + ", postcode=" + postcode
				+ ", email=" + email + "]";
	}
	

}
