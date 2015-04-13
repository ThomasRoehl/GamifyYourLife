package de.tro.development.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Profile {
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
}
