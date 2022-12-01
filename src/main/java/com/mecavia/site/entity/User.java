package com.mecavia.site.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.mecavia.site.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	private String code;
	private String firstname;
	private String lastname;
	@Column(unique = true)
	private String email;
	private String password;
	private String role;
	private Status status;
	@OneToMany(targetEntity = Address.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_address",referencedColumnName = "id")
	private List<Address> addresses;
	@OneToMany(targetEntity = Contact.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_tpno",referencedColumnName = "id")
	private List<Contact> contactNumbers;
	
}
