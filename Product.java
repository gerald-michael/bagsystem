package com.xadmin.inventorymanagement.bean;

/**
 * User.java
 * This is a model class represents a User entity
 *
 */
public class Product {
	protected int id;
	protected String name;
	protected int price;
	protected int quatity;
	
	public Product() {
	}
	
	public Product(String name, int price, int quatity) {
		super();
		this.name = name;
		this.price = price;
		this.quatity = quatity;
	}

	public Product(int id, String name, int price, int quatity) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.quatity = quatity;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuatity() {
		return quatity;
	}
	public void setQuatity(int quatity) {
		this.quatity = quatity;
	}
}