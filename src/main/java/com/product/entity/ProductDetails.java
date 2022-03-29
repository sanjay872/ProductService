package com.product.entity;

public class ProductDetails {
	private long id;
	private String modelName;
	private String os;
	private int ram;
	private int hardDiskSize;
	private int stockAvailable;
	private long vendorId;
	private String vendorName;
	
	public ProductDetails() {
		// TODO Auto-generated constructor stub
	}
	
	public ProductDetails(String modelName, String os, int ram, int hardDiskSize, int stockAvailable, long vendorId,
			String vendorName) {
		this.modelName = modelName;
		this.os = os;
		this.ram = ram;
		this.hardDiskSize = hardDiskSize;
		this.stockAvailable = stockAvailable;
		this.vendorId = vendorId;
		this.vendorName = vendorName;
	}

	public ProductDetails(long id, String modelName, String os, int ram, int hardDiskSize, int stockAvailable,
			long vendorId, String vendorName) {
		this.id = id;
		this.modelName = modelName;
		this.os = os;
		this.ram = ram;
		this.hardDiskSize = hardDiskSize;
		this.stockAvailable = stockAvailable;
		this.vendorId = vendorId;
		this.vendorName = vendorName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	
	
}
