package com.myroom.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class MessageService {
	@Value("${TWILIO_ACCOUNT_SID}")
	private   String sId;
	@Value("${TWILIO_AUTH_TOKEN}")
	   private   String id;
	@Value("${TWILIO_NUMBER}")
	   private   String twilioNumber;
	@Value("${PHONE_NUMBER}")
	   private   String phoneNumber;
	   //String[] phoneNumberArr= {"+91 84989 57066", "+91 89784 86561","+91 84990 46226","+91 77028 85451","+91 82975 69247","+91 63059 15200"};
//	   ,"+91 9701311735","+91 63059 15200","+91 89784 86561","+91 77028 85451","+91 82975 69247"
	//   static {
		  // MessageService messageService=new MessageService();
//		      Twilio.init(messageService.sId,messageService.id); 
		      
	//}
	  
	   public void sendMessage(String userName,String prodName,String description,int amount, String date)
	   
	   {	String url = "https://flat401.ccbp.tech";
	   Twilio.init(sId,id);
	  // System.out.println(sId+" "+id);
		   //for (int i = 0; i <phoneNumberArr.length-5; i++) {
			   Message.creator(new PhoneNumber(phoneNumber), new PhoneNumber(twilioNumber),
				         userName+" has purchased "+prodName+" for the amount of "+amount+" rupees at \n"+date+". to view click below \n"+url).create();
		//}
		   
	   }
	   
}
