package com.mecavia.site.util;

import lombok.Getter;


public enum Status {
	ACTIVE("STATUS_ACTIVE"),
	INACTIVE("STATUS_INACTIVE"),
	ACCEPTED("STATUS_ACCEPTED"),
	CANCELED("STATUS_CANCELED"),
	RETURN("STATUS_RETURN"),
	OPENED("STATUS_OPEND"),
	CLOSED("STATUS_CLOSED");
	@Getter  private final  String status; 
	Status(String status){
		 this.status = status;
	}
 
}
