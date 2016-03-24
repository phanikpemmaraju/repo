package uk.gov.dwp.esf.mi.common;

public class Address {
	
	private String firstLine;
	private String secondLine;
	private String street;
	private String district;
	private String city;
	private String postcode;
	private String country;
	private String email;
	
	@Override
	public String toString() {
		return "Address [firstLine=" + firstLine + ", secondLine=" + secondLine + ", street=" + street + ", district="
				+ district + ", city=" + city + ", postcode=" + postcode + ", country=" + country + ", email=" + email + "]";
	}

	public String getFirstLine() {
		return firstLine;
	}
	
	public String getSecondLine() {
		return secondLine;
	}

	public String getStreet() {
		return street;
	}

	public String getDistrict() {
		return district;
	}

	public String getCity() {
		return city;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getCountry() {
		return country;
	}	

	public String getEmail() {
		return email;
	}
	
	private Address(AddressBuilder builder){		
		this.firstLine = builder.firstLine;
		this.secondLine = builder.secondLine;
		this.street = builder.street;
		this.district = builder.district;
		this.city = builder.city;
		this.postcode = builder.postcode;
		this.country = builder.country;
		this.email = builder.email;
	}
	
	public static class AddressBuilder{
		
		private final String firstLine;
		private final String city;
		private final String postcode;
		private String secondLine;
		private String street;
		private String district;
		private String country;
		private String email;
		
		public AddressBuilder(String firstLine,String city,String postcode){
			this.firstLine = firstLine;
			this.city = city;
			this.postcode = postcode;
		}
				
		public AddressBuilder secondLine(String secondLine){
			this.secondLine = secondLine;
			return this;
		}
		
		public AddressBuilder street(String street){
			this.street = street;
			return this;
		}
		
		public AddressBuilder district(String district){
			this.district = district;
			return this;
		}
		
		public AddressBuilder country(String country){
			this.country = country;
			return this;
		}
		
		public AddressBuilder email(String email){
			this.email = email;
			return this;
		}
		
		public Address build() {
			Address address = new Address(this);
			return address;
		}
		
	}
	
}
