package uk.gov.dwp.esf.mi.resources;

/*
 *  @Author : Phani Krishna
 *  @Description : ESFProviderResource uses the Spring HATEOAS Resource Support for
 *  			   providing Hyper Links/Hyper Media formats
 *  @Version : 1.0
 * 
 */

import uk.gov.dwp.esf.mi.model.Provider;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

@Relation(value="provider", collectionRelation="providers")
public class ProviderResource extends ResourceSupport{
	
	private Provider provider;
	
	public ProviderResource(Provider provider){	
		this.provider = provider;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	
	


}
