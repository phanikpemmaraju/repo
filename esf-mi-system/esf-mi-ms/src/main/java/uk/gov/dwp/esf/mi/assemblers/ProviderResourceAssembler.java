package uk.gov.dwp.esf.mi.assemblers;


/*
 *  @Author : Phani Krishna
 *  @Description :  A Helper class which extends Spring Hateoas ResourceAssemblerSupport class to reduce 
 *  				the amount of code needed to write for mapping from ESFProvider entity
 *                  to a resource type and adding respective links.
 *  @Version : 1.0
 * 
 */

import uk.gov.dwp.esf.mi.model.Provider;
import uk.gov.dwp.esf.mi.controllers.ParticipantController;
import uk.gov.dwp.esf.mi.resources.ProviderResource;
import uk.gov.dwp.esf.mi.resources.ProviderResources;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProviderResourceAssembler extends ResourceAssemblerSupport<Provider, ProviderResource> {

	public ProviderResourceAssembler() {
		super(ParticipantController.class, ProviderResource.class);
	}

	/*
	 * Overridden default method to generate a ESFProvider Resource object with
	 * a Self link for the provider Id.
	 * 
	 */
	@Override
	public ProviderResource toResource(Provider provider) {

		// The below will add a link with rel self pointing itself
		ProviderResource providerResource = new ProviderResource(provider);

		// The below will add a link with href for pointing to provider's provider Id
		providerResource.add(new Link("/providers/" + String.valueOf(provider.getProviderId()), "self"));
		return providerResource;
	}
	
	
	/*public Resources<ProviderResource> toResourcesFor(Page<Provider> providers, Pageable pageable,
			final String requestURI){
		Resources<ProviderResource> providerResources;
		Collection<ProviderResource> providerResourceList = new ArrayList<ProviderResource>();
		
		
		for (Provider provider : providers.getContent()) {
			providerResourceList.add(toResource(provider));
		}

		final List<Link> links = new ArrayList<>();

		StringBuilder builder = new StringBuilder();

		// Create the Pageable Self Link
		if (pageable != null) {
			builder.append(requestURI);
			builder.append("?page="); builder.append(pageable.getPageNumber());
			builder.append("&offset="); builder.append(pageable.getOffset());
			builder.append("&size="); builder.append(pageable.getPageSize());

			if (pageable.getSort() != null) {
				builder.append("&sort=");
				builder.append(pageable.getSort().toString());
			}
		}

		if (builder.toString().trim().length() > 1)
			links.add(new Link(builder.toString(), "self"));

		builder.setLength(0);
		builder = new StringBuilder();

		// Create the Pageable Next Link
		if (providers.hasNext()) {
			builder.append(requestURI);
			builder.append("?page="); builder.append(providers.nextPageable().getPageNumber());
			builder.append("&offset="); builder.append(providers.nextPageable().getOffset());
			builder.append("&size="); builder.append(providers.nextPageable().getPageSize());

			if (pageable != null && pageable.getSort() != null) {
				builder.append("&sort=");
				builder.append(pageable.getSort().toString());
			}
		}

		if (builder.toString().trim().length() > 1)
			links.add(new Link(builder.toString(), "providers.next"));

		builder.setLength(0);
		builder = new StringBuilder();

		// Create the Pageable Previous Link
		if (providers.hasPrevious()) {
			builder.append(requestURI); 
			builder.append("?page="); builder.append(providers.previousPageable().getPageNumber());
			builder.append("&offset="); builder.append(providers.previousPageable().getOffset());
			builder.append("&size="); builder.append(providers.previousPageable().getPageSize());

			if (pageable != null && pageable.getSort() != null) {
				builder.append("&sort=");
				builder.append(pageable.getSort().toString());
			}
		}

		if (builder.toString().trim().length() > 1)
			links.add(new Link(builder.toString(), "providers.previous"));

		providerResources = new Resources<>(providerResourceList);
		providerResources.add(links);
		return providerResources;
	}*/
	

	/*
	 * Method to generate a collection of ESFProvider Resource objects along
	 * with a Self link
	 * 
	 */
	public ProviderResources toResourcesForProvider(Page<Provider> providers, Pageable pageable,
			final String requestURI) {
		final List<ProviderResource> providerResourcesList = new ArrayList<>();

		for (Provider provider : providers.getContent()) {
			providerResourcesList.add(toResource(provider));
		}

		final List<Link> links = new ArrayList<>();

		StringBuilder builder = new StringBuilder();

		// Create the Pageable Self Link
		if (pageable != null) {
			builder.append(requestURI);
			builder.append("?page="); builder.append(pageable.getPageNumber());
			builder.append("&offset="); builder.append(pageable.getOffset());
			builder.append("&size="); builder.append(pageable.getPageSize());

			if (pageable.getSort() != null) {
				builder.append("&sort=");
				builder.append(pageable.getSort().toString());
			}
		}

		if (builder.toString().trim().length() > 1)
			links.add(new Link(builder.toString(), "self"));

		builder.setLength(0);
		builder = new StringBuilder();

		// Create the Pageable Next Link
		if (providers.hasNext()) {
			builder.append(requestURI);
			builder.append("?page="); builder.append(providers.nextPageable().getPageNumber());
			builder.append("&offset="); builder.append(providers.nextPageable().getOffset());
			builder.append("&size="); builder.append(providers.nextPageable().getPageSize());

			if (pageable != null && pageable.getSort() != null) {
				builder.append("&sort=");
				builder.append(pageable.getSort().toString());
			}
		}

		if (builder.toString().trim().length() > 1)
			links.add(new Link(builder.toString(), "providers.next"));

		builder.setLength(0);
		builder = new StringBuilder();

		// Create the Pageable Previous Link
		if (providers.hasPrevious()) {
			builder.append(requestURI); 
			builder.append("?page="); builder.append(providers.previousPageable().getPageNumber());
			builder.append("&offset="); builder.append(providers.previousPageable().getOffset());
			builder.append("&size="); builder.append(providers.previousPageable().getPageSize());

			if (pageable != null && pageable.getSort() != null) {
				builder.append("&sort=");
				builder.append(pageable.getSort().toString());
			}
		}

		if (builder.toString().trim().length() > 1)
			links.add(new Link(builder.toString(), "providers.previous"));

		ProviderResources providerResources = new ProviderResources();
		providerResources.setLinks(links); providerResources.setProviders(providerResourcesList);
		return providerResources;
	}
	
	

	/*
	 * This method gets called for retrieving Provider information for a
	 * specific Provider Id with a rel pointing to Participants information.
	 * 
	 */
	public List<ProviderResource> toResourcesForProviders(Iterable<? extends Provider> providers,
			String requestURI) {
		final List<ProviderResource> providerResources = new ArrayList<>();
		for (final Provider provider : providers) {
			providerResources.add(this.toResource(provider));
		}

		providerResources.forEach(
				providerResource -> providerResource.add(new Link(requestURI + "/participants", "participants")));
		return providerResources;
	}

	/*
	 * Overridden default method to create a new instance of ESFProvider
	 * Resource object for a given ESFProvider entity object.
	 * 
	 */
	@Override
	protected ProviderResource instantiateResource(Provider provider) {
		// TODO Auto-generated method stub
		return new ProviderResource(provider);
	}

}
