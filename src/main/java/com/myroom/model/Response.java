package com.myroom.model;

import java.io.Serializable;

public class Response implements Serializable{
private String response="Deleted Succesfully";
private int responseCode;
public Response(String response, int responseCode) {
	super();
	this.response = response;
	this.responseCode = responseCode;
}
public Response() {
	super();
}
public String getResponse() {
	return response;
}
public void setResponse(String response) {
	this.response = response;
}
public int getResponseCode() {
	return responseCode;
}
public void setResponseCode(int responseCode) {
	this.responseCode = responseCode;
}

}
