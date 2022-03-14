package com.myroom.service;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;

import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class MessageService {

	private final static String ACCOUNT_SID = "ACd5a997ba003454cc4b75d2b8ca9e38bc";
	   private final static String AUTH_ID = "751d4fb793cb4136fdf1371b5648978f";
	   String[] phoneNumberArr= {"+91 8499046226","+91 6305915200","+91 8978486561","+91 7702885451","+91 8297569247","+91 8498957066"};
	   static {
		      Twilio.init(ACCOUNT_SID, AUTH_ID);
		      
	   }
	  
	   public void sendMessage(String userName,String prodName,String description,int amount, String date,String url)
	   {
		   for (int i = 0; i <phoneNumberArr.length; i++) {
			   Message.creator(new PhoneNumber(phoneNumberArr[i]), new PhoneNumber("+12537853807"),
				         userName+" has purchased "+prodName+" for the amount of "+amount+" time : "+date+" click here to view details: "+url).create();
		}
		   
	   }
	   
}
