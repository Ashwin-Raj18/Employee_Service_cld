package com.possoul.SpringBootRestCrud.ProxyDto;


public class ProductDetails {
	private int pId;
	private String pName;
	private int pQuantity;
	public ProductDetails() {
		super();
	}
	public ProductDetails(int pId, String pName, int pQuantity) {
		super();
		this.pId = pId;
		this.pName = pName;
		this.pQuantity = pQuantity;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public int getpQuantity() {
		return pQuantity;
	}
	public void setpQuantity(int pQuantity) {
		this.pQuantity = pQuantity;
	}
	@Override
	public String toString() {
		return "ProductDetails [pId=" + pId + ", pName=" + pName + ", pQuantity=" + pQuantity + "]";
	}
	
	
}
