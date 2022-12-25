package com.mecavia.site.controllerimplies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mecavia.site.dto.EmailDto;
import com.mecavia.site.dto.ResponseDto;
import com.mecavia.site.util.MailApi;

@RestController
@CrossOrigin
@RequestMapping

public class EmailController {
	@Autowired	
	private MailApi mailApi;
	
	@Autowired
	private ResponseDto responseDto;
	
	@PostMapping("/sendmail")
	public ResponseEntity<ResponseDto> sendMail(@RequestBody EmailDto emailDto){
		mailApi.sendMail(emailDto.getToEmail(), emailDto.getSubject(), emailDto.getBody());
		return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
				
	}
}
