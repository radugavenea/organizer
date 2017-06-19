package spring.demo.dto;

public class UserDTO {

	private Integer id;
	private String firstname;
	private String surname;
	private String email;
	private String address;
	private String postcode;
	private String city;
	private String country;
	private String telephone;
	private String IBAN;

	public UserDTO() {
	}

	public UserDTO(Integer id, String firstname, String surname,  String email, String address, String postcode, String city, String country,
			String telephone, String IBAN) {
		super();
		this.id = id;
		this.firstname= firstname;
		this.surname = surname;
		this.email = email;
		this.address = address;
		this.postcode = postcode;
		this.city = city;
		this.country = country;
		this.telephone = telephone;
		this.IBAN = IBAN;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getIBAN() {
		return IBAN;
	}

	public void setIBAN(String IBAN) {
		this.IBAN = IBAN;
	}




	public static class Builder {
		private Integer nestedid;
		private String nestedfirstname;
		private String nestedsurname;
		private String nestedemail;
		private String nestedaddress;
		private String nestedpostcode;
		private String nestedcity;
		private String nestedcountry;
		private String nestedtelephone;
		private String nestedIBAN;
		private String nestedfullname;

		public Builder id(int id) {
			this.nestedid = id;
			return this;
		}

		public Builder firstname(String name) {
			this.nestedfirstname = name;
			return this;
		}
		
		public Builder surname(String name) {
			this.nestedsurname = name;
			return this;
		}

		public Builder email(String email) {
			this.nestedemail = email;
			return this;
		}

		public Builder address(String address) {
			this.nestedaddress = address;
			return this;
		}

		public Builder postcode(String postcode) {
			this.nestedpostcode = postcode;
			return this;
		}

		public Builder city(String city) {
			this.nestedcity = city;
			return this;
		}

		public Builder country(String country) {
			this.nestedcountry = country;
			return this;
		}

		public Builder telephone(String telephone) {
			this.nestedtelephone = telephone;
			return this;
		}

		public Builder IBAN(String iban) {
			this.nestedIBAN = iban;
			return this;
		}



		public UserDTO create() {
			return new UserDTO(nestedid, nestedfirstname,nestedsurname, nestedemail, nestedaddress, nestedpostcode,
					nestedcity, nestedcountry, nestedtelephone, nestedIBAN);
		}

	}

}
