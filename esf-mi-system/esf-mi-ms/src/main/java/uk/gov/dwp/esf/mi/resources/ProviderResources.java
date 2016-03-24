package uk.gov.dwp.esf.mi.resources;

/*
 *  @Author : Phani Krishna
 *  @Description : A collection of ESFParticipantResource objects and a list of Spring hateoas links. 			   
 *  @Version : 1.0
 * 
 */

import java.util.List;
import org.springframework.hateoas.Link;

public class ProviderResources {
		
	private List<ProviderResource> providers;
	
	private List<Link> links;
	
	public List<Link> getLinks() {
		return links;
	}
		
	public void setProviders(List<ProviderResource> providers) {
		this.providers = providers;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public List<ProviderResource> getProviders() {
		return providers;
	}
	
	
	
}
