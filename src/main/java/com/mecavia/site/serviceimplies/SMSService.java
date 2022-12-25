package com.mecavia.site.serviceimplies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mecavia.site.dto.SMSDto;
import com.mecavia.site.service.SMSServive;
import com.mecavia.site.util.URLReader;

@Service
@Transactional
public class SMSService implements SMSServive {
	@Autowired
	private URLReader urlReader;
	
	@Override
	public String sendSMS(SMSDto smsDto) {
		String resp = null; 
		try {
			resp = urlReader.sendSMS(smsDto.getMassage(),smsDto.getReceiver());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}

}
