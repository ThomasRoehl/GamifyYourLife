package de.tro.development.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQueries({
	@NamedQuery(name = "Message.getMessagesFromUserByID", query = "SELECT m FROM Message m WHERE m.addressee = :addressee")
})

@Entity
public class Message {
	
	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@Column(name = "msg", updatable = false, nullable = false)
	private String msg;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "sender_fk", nullable = false)
	private UserProfile sender;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "addressee_fk", nullable = false)
	private UserProfile addressee;
	
	@Column(name = "regard", updatable = false, nullable = false)
	private String regard;
	
	@Column(name = "time")
	@Temporal(TemporalType.DATE)
	private Date time;
	
	@Column(name = "system", updatable = false, nullable = false)
	private boolean system;
	
	@Column(name = "SendObject")
	private String sendObject;

	public String getSendObject() {
		return sendObject;
	}

	public void setSendObject(String sendObject) {
		this.sendObject = sendObject;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public UserProfile getSender() {
		return sender;
	}

	public void setSender(UserProfile sender) {
		this.sender = sender;
	}

	public UserProfile getAddressee() {
		return addressee;
	}

	public void setAddressee(UserProfile addressee) {
		this.addressee = addressee;
	}

	public String getRegard() {
		return regard;
	}

	public void setRegard(String regard) {
		this.regard = regard;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public boolean isSystem() {
		return system;
	}

	public void setSystem(boolean system) {
		this.system = system;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", msg=" + msg + ", sender=" + sender
				+ ", addressee=" + addressee + ", regard=" + regard + ", time="
				+ time + ", system=" + system + "]";
	}
}
