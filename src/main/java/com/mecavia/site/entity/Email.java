package com.mecavia.site.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.mecavia.site.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Email {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String toEmail;
	private String subject;
	private String body;
	private Status status;
}
