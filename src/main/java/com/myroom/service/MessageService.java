package com.myroom.service;

import java.util.Iterator;

import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class MessageService {

	private final static String ACCOUNT_SID = "AC81f99dbd34a9c0a33d6f6822aa388412";
	   private final static String AUTH_ID = "560e94c23d93dcc57a40c0addfa8f840";
	   String[] phoneNumberArr= {"+91 84989 57066", "+91 89784 86561","+91 84990 46226","+91 77028 85451","+91 82975 69247","+91 63059 15200"};
//	   ,"+91 9701311735","+91 63059 15200","+91 89784 86561","+91 77028 85451","+91 82975 69247"
	   static {
		      Twilio.init(ACCOUNT_SID, AUTH_ID);
		      
	}
	  
	   public void sendMessage(String userName,String prodName,String description,int amount, String date)
	   {	String url = "https://flat401.ccbp.tech";
	   System.out.println(ACCOUNT_SID+" "+AUTH_ID);
		   for (int i = 0; i <phoneNumberArr.length-5; i++) {
			   Message.creator(new PhoneNumber(phoneNumberArr[i]), new PhoneNumber("+19704101137"),
				         userName+" has purchased "+prodName+" for the amount of "+amount+" rupees at \n"+date+". to view click below \n"+url).create();
		}
		   
	   }
	   
}
