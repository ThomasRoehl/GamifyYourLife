package de.tro.development.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Profile implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@Column(name = "firstname", nullable = false)
	private String firstname;
	
	@Column(name = "lastname", nullable = false)
	private String lastname;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "mail", nullable = false)
	private String mail;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "city_fk", nullable = false)
	private City city;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "country_fk", nullable = false)
	private Country country;
	
	@Column(name = "street1", nullable = false)
	private String street1;
	
	@Column(name = "street2")
	private String street2;

	public Profile() {
		super();
	}

	public Profile(String firstname, String lastname, String description,
			String mail, City city, Country country, String street1,
			String street2) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.description = description;
		this.mail = mail;
		this.city = city;
		this.country = country;
		this.street1 = street1;
		this.street2 = street2;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", firstname=" + firstname + ", lastname="
				+ lastname + ", description=" + description + ", mail=" + mail
				+ ", city=" + city + ", country=" + country + ", street1="
				+ street1 + ", street2=" + street2 + "]";
	}
}
