package com.myroom.model;

import java.io.Serializable;

public class GenerateOtp  implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String emailId;
private int otp;
public GenerateOtp(String emailId, int otp) {
	super();
	this.emailId = emailId;
	this.otp = otp;
}
public GenerateOtp() {
	super();
}
public String getEmailId() {
	return emailId;
}
public void setEmailId(String emailId) {
	this.emailId = emailId;
}
public int getOtp() {
	return otp;
}
public void setOtp(int otp) {
	this.otp = otp;
}

}
