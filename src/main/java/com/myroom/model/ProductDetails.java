package com.myroom.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class ProductDetails implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	@Id
private String id;
@Field
private String itemName;
@Field
private String description;
@Field
private int userId ;
@Field
private String createdDate;
@Field
private int amount;
private int spentAmount;
public ProductDetails() {
	super();
}
public ProductDetails( String itemName, String description, int userId, String createdDate, int amount) {
	super();
	//this.id = id;
	this.itemName = itemName;
	this.description = description;
	this.userId = userId;
	this.createdDate = createdDate;
	this.amount = amount;
}
public String getId() {
	return id;
}
//public void setId(int id) {
//	this.id = id;
//}
public String getItemName() {
	return itemName;
}
public void setItemName(String itemName) {
	this.itemName = itemName;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public String getCreatedDate() {
	return createdDate;
}
public void setCreatedDate(String createdDate) {
	this.createdDate = createdDate;
}
public int getAmount() {
	return amount;
}
public void setAmount(int amount) {
	this.amount = amount;
}
public static long getSerialversionuid() {
	return serialVersionUID;
}
public int getSpentAmount() {
	return spentAmount;
}
public void setSpentAmount(int spentAmount) {
	this.spentAmount = spentAmount;
}
public ProductDetails(String id, String itemName, String description, int userId, String createdDate, int amount,
		int spentAmount) {
	super();
	this.id = id;
	this.itemName = itemName;
	this.description = description;
	this.userId = userId;
	this.createdDate = createdDate;
	this.amount = amount;
	this.spentAmount = spentAmount;
}


}
