package com.product.entity;

public class Vendor {
	private long id;
	private String name;
	private double rating;
	private String address;
	private String contactNumber;
	
	public Vendor() {
	
	}

	public Vendor(long id, String name, double rating, String address, String contactNumber) {
		super();
		this.id = id;
		this.name = name;
		this.rating = rating;
		this.address = address;
		this.contactNumber = contactNumber;
	}

	public Vendor(String name, double rating, String address, String contactNumber) {
		super();
		this.name = name;
		this.rating = rating;
		this.address = address;
		this.contactNumber = contactNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	
}
