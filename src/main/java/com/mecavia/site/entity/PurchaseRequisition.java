package com.mecavia.site.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class PurchaseRequisition {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	private String prcode;
	@Column(unique = true)
	private  String pocode;
	private int supplierid; 
	private int materialid;
	private double unitrate;
	private double quntity;
	private int uomid;
	private int paymettermid;
	
}
