package com.kaas.kaasPass.Model;

import java.io.Serializable;
import java.util.Objects;

import org.hibernate.annotations.Collate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
//import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
//@Table(name = "tourist")
public class Tourist implements Serializable{
	
	private static final long serialVersionUID = 3989624443255853830L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String  firstName;	
	private String lastName;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;
	private String email;
	private String contactNumber;
	private String timeSlot;
	private Integer adults;
	private Integer minors;
	
	@Column(length = 512)
	private String encryptedString;
	
	
	public String getEncryptedString() {
		return encryptedString;
	}
	public void setEncryptedString(String encryptedString) {
		this.encryptedString = encryptedString;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getTimeSlot() {
		return timeSlot;
	}
	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}
	public Integer getAdults() {
		return adults;
	}
	public void setAdults(Integer adults) {
		this.adults = adults;
	}
	public Integer getMinors() {
		return minors;
	}
	public void setMinors(Integer minors) {
		this.minors = minors;
	}
	@Override
	public String toString() {
		return "Tourist [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
				+ ", email=" + email + ", contactNumber=" + contactNumber + ", timeSlot=" + timeSlot + ", adults="
				+ adults + ", minors=" + minors + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(address, adults, contactNumber, email, firstName, id, lastName, minors, timeSlot);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tourist other = (Tourist) obj;
		return Objects.equals(address, other.address) && Objects.equals(adults, other.adults)
				&& Objects.equals(contactNumber, other.contactNumber) && Objects.equals(email, other.email)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(minors, other.minors)
				&& Objects.equals(timeSlot, other.timeSlot);
	}

}
