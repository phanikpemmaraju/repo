package uk.gov.dwp.esf.mi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import uk.gov.dwp.esf.mi.model.Provider;

@Repository
public interface ProviderRepository extends MongoRepository<Provider, String> {
	
	Page<Provider> findByProviderId(Integer providerId,Pageable pageable);		
}
