package spring.organizer.entities;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "user")
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String email;
	private String address;
	private String postcode;
	private String city;
	private String country;
	private String telephone;
	private String IBAN;
	private Date created;

	public User() {
	}

	public User(Integer id, String name, String email, String address, String postcode, String city, String country,
			String telephone, String IBAN, Date created) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.address = address;
		this.postcode = postcode;
		this.city = city;
		this.country = country;
		this.telephone = telephone;
		this.IBAN = IBAN;
		this.created = created;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "email", nullable = false, length = 200)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "address", nullable = true, length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "postcode", nullable = true, length = 50)
	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@Column(name = "city", nullable = true, length = 100)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "country", nullable = true, columnDefinition = "char(2)")
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "telephone", nullable = true, length = 50)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "IBAN", nullable = true, length = 100)
	public String getIBAN() {
		return IBAN;
	}

	public void setIBAN(String iban) {
		this.IBAN = iban;
	}


	@Column(name = "created", nullable = false, length = 100)
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
