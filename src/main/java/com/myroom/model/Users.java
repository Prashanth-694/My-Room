package com.myroom.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Users {
@Id
private int userId;
@Field
private String userName;
@Field
private String emailId;
@Field
private String password;
@Field
private String role;
@Field
private byte[] img;
public Users() {
	super();
}
public Users(int userId, String userName, String emailId, String password, String role, byte[] img) {
	super();
	this.userId = userId;
	this.userName = userName;
	this.emailId = emailId;
	this.password = password;
	this.role = role;
	this.img = img;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getEmailId() {
	return emailId;
}
public void setEmailId(String emailId) {
	this.emailId = emailId;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public byte[] getImg() {
	return img;
}
public void setImg(byte[] img) {
	this.img = img;
}

}
