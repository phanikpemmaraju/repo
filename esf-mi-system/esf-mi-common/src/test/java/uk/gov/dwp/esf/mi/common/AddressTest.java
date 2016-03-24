package uk.gov.dwp.esf.mi.common;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;


public class AddressTest {
	
	@Test
    public void shouldReturnAddressBuilder() {
		assertThat(new Address.AddressBuilder(null, null, null),isA(Address.AddressBuilder.class));
    }
	
	@Test
    public void shouldBuildADefaultAddress() {
        assertThat(new Address.AddressBuilder(null, null, null).build(), isA(Address.class));
    }
	
	@Test
    public void shouldBuildAnAddress() {
        Address address = new Address.AddressBuilder("Apartment 33","Sheffield","S1 4GG")
                .build();

        assertThat(address.getFirstLine(), is("Apartment 33"));
        assertThat(address.getCity(), is("Sheffield"));
        assertThat(address.getPostcode(), is("S1 4GG"));
        
        Address addressNew = new Address.AddressBuilder("Apartment 33","Sheffield","S1 4GG")
				.country("UK").district("South Yorkshire").email("abc@gmail.com").secondLine("Royal Plaza").street("Rockingham Street")
                .build();
        
        assertThat(addressNew.getFirstLine(), is("Apartment 33"));
        assertThat(addressNew.getCity(), is("Sheffield"));
        assertThat(addressNew.getPostcode(), is("S1 4GG"));
        assertThat(addressNew.getCountry(), is("UK"));
        assertThat(addressNew.getDistrict(), is("South Yorkshire"));
        assertThat(addressNew.getEmail(), is("abc@gmail.com"));
        assertThat(addressNew.getSecondLine(), is("Royal Plaza"));
        assertThat(addressNew.getStreet(), is("Rockingham Street"));   
        
        final String value = "Address [firstLine=Apartment 33, secondLine=Royal Plaza, street=Rockingham Street, district=South Yorkshire, city=Sheffield, postcode=S1 4GG, country=UK, email=abc@gmail.com]";
        assertThat(addressNew.toString(), is(value));
    }
	
}
