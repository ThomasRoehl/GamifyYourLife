package de.tro.development.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@NamedQueries({
		@NamedQuery(name = "UserProfile.findAllUser", query = "Select u FROM UserProfile u"),
		@NamedQuery(name = "UserProfile.findUserByID", query = "Select u FROM UserProfile u WHERE u.id = :user_id"),
		@NamedQuery(name = "UserProfile.findUserByName", query = "Select u.id FROM UserProfile u WHERE u.username = :username"),
		@NamedQuery(name = "UserProfile.findUserLikeName", query = "Select u.id FROM UserProfile u WHERE UPPER(u.username) LIKE :username"),
		@NamedQuery(name = "UserProfile.findUserinformationByID", query = "Select u.username, u.firstname, u.lastname, u.mail, u.points FROM UserProfile u WHERE u.id = :user_id"),
		@NamedQuery(name = "UserProfile.findUsernameByID", query = "Select u.username FROM UserProfile u WHERE u.id = :user_id"),
		@NamedQuery(name = "UserProfile.findUserProfileByName", query = "Select u FROM UserProfile u WHERE u.username = :username"),
		@NamedQuery(name = "UserProfile.findTodoListByName", query = "Select u.todo_list FROM UserProfile u WHERE u.username = :username"),
		@NamedQuery(name = "UserProfile.checkUserLogin", query = "Select COUNT(u) FROM UserProfile u WHERE u.username = :username AND u.password = :password"),
		@NamedQuery(name = "UserProfile.findUserPoints", query = "Select u.points FROM UserProfile u WHERE u.id = :id"), 
		@NamedQuery(name = "UserProfile.getAchievements", query = "Select ua.id FROM UserProfile u INNER JOIN u.user_achievement ua WHERE u.id = :id")})
@Entity
public class UserProfile implements Serializable {
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
	@JoinColumn(name = "city_fk")
	private City city;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "country_fk")
	private Country country;

	@Column(name = "street1", nullable = false)
	private String street1;

	@Column(name = "street2")
	private String street2;

	@Column(name = "username", unique = true, nullable = false)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "todo_list_fk")
	private Todo_list todo_list;

	@OneToMany
	@JoinTable(name = "user_achievement", joinColumns = { @JoinColumn(name = "user_fk") }, inverseJoinColumns = { @JoinColumn(name = "achievement_fk") })
	private Set<Achievement> user_achievement = new HashSet<Achievement>();

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "hero_fk")
	private Hero hero;

	@Column(name = "hero_level")
	private Integer hero_level;

	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "missteps_fk")
	private Set<Misstep> missteps = new HashSet<Misstep>();

	@Column(name = "points", nullable = false)
	private Long points;

	@JoinTable(name = "contacts", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "contact_id"))
	private Set<UserProfile> friends = new HashSet<UserProfile>();

	public UserProfile() {
		super();
	}

	public UserProfile(String firstname, String lastname, String description,
			String mail, City city, Country country, String street1,
			String street2, String username, String password,
			Todo_list todo_list, Hero hero) {
		super();
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.description = description;
		this.mail = mail;
		this.city = city;
		this.country = country;
		this.street1 = street1;
		this.street2 = street2;
		this.username = username;
		this.todo_list = todo_list;
		this.hero = hero;
		this.points = 0L;
		this.hero_level = 0;
	}

	public Set<UserProfile> getFriends() {
		return friends;
	}

	public void addFriends(UserProfile contact) {
		this.friends.add(contact);
	}

	public void setUser_achievement(Set<Achievement> user_achievement) {
		this.user_achievement = user_achievement;
	}

	public void setMissteps(Set<Misstep> missteps) {
		this.missteps = missteps;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Todo_list getTodo_list() {
		return todo_list;
	}

	public void setTodo_list(Todo_list todo_list) {
		this.todo_list = todo_list;
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public Long getPoints() {
		return points;
	}

	public void setPoints(Long points) {
		this.points = points;
	}

	public int getId() {
		return id;
	}

	public Set<Misstep> getMissteps() {
		return missteps;
	}

	public void addMissteps(Misstep missteps) {
		this.missteps.add(missteps);
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

	public Set<Achievement> getUser_achievement() {
		return user_achievement;
	}

	public void addAchievement(Achievement achievement) {
		this.user_achievement.add(achievement);
	}

	public Integer getHero_level() {
		return hero_level;
	}

	public void setHero_level(Integer hero_level) {
		this.hero_level = hero_level;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", firstname=" + firstname + ", lastname="
				+ lastname + ", description=" + description + ", mail=" + mail
				+ ", city=" + city + ", country=" + country + ", street1="
				+ street1 + ", street2=" + street2 + ", username=" + username
				+ ", todo_list=" + todo_list + ", user_achievement="
				+ user_achievement + ", hero=" + hero + ", missteps="
				+ missteps + ", points=" + points + "hero_level=" + hero_level
				+ "]";
	}
}
