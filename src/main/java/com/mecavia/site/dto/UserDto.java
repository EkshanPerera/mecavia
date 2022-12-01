package com.mecavia.site.dto;

import java.util.List;

import com.mecavia.site.entity.Address;
import com.mecavia.site.entity.Contact;
import com.mecavia.site.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
	private int id;
	private String code;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private String role;
	private Status status;
	private List<Address> addresses;
	private List<Contact> contactNumbers;
}
