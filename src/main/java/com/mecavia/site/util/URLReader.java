package com.mecavia.site.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class URLReader {
	@Value("${SMSID}")
	private String smsid;
	@Value("${SMSPASSWORD}")
	private String smspassword;
	
	private String res;
	private String resa[]; 
	private String inputLine; 
	public String sendSMS(String text,String receiver) throws Exception {
		
		URL textit = new URL("http://textit.biz/sendmsg/index.php?id="+smsid+"&pw="+smspassword+"&to="+ receiver +"&text="+text);
		BufferedReader in = new BufferedReader(
		new InputStreamReader(textit.openStream()));
		
//		 "OK:1-MSG_GSM-2 Uploaded_Successfully";
		while ((inputLine = in.readLine()) != null)
		res = inputLine;
		in.close();
		
		System.out.println(res);
		resa = res.split(":");
		if (resa[0] == "OK"){
				System.out.println(inputLine);
		}
		return inputLine;
	}

}
