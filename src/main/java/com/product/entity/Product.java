package com.product.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String modelName;
	private String os;
	private int ram;
	private int hardDiskSize;
	private int stockAvailable;
	private long vendorId;
	
	public Product() {
		
	}

	public Product(String modelName, String os, int ram, int hardDiskSize, int stockAvailable, long vendorId) {
		this.modelName = modelName;
		this.os = os;
		this.ram = ram;
		this.hardDiskSize = hardDiskSize;
		this.stockAvailable = stockAvailable;
		this.vendorId = vendorId;
	}

	public Product(long id, String modelName, String os, int ram, int hardDiskSize, int stockAvailable, long vendorId) {
		this.id = id;
		this.modelName = modelName;
		this.os = os;
		this.ram = ram;
		this.hardDiskSize = hardDiskSize;
		this.stockAvailable = stockAvailable;
		this.vendorId = vendorId;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}

	public int getHardDiskSize() {
		return hardDiskSize;
	}

	public void setHardDiskSize(int hardDiskSize) {
		this.hardDiskSize = hardDiskSize;
	}

	public int getStockAvailable() {
		return stockAvailable;
	}

	public void setStockAvailable(int stockAvailable) {
		this.stockAvailable = stockAvailable;
	}

	public long getVendorId() {
		return vendorId;
	}

	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	
}
