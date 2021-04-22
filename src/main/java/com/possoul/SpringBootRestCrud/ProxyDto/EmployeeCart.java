package com.possoul.SpringBootRestCrud.ProxyDto;




import java.util.List;


public class EmployeeCart {
	
	private String customerName;
	private int cId;
	private List<ProductDetails> pDetails;
	
	public EmployeeCart() {
		super();
	}

	public EmployeeCart(String customerName, int cId, List<ProductDetails> pDetails) {
		super();
		this.customerName = customerName;
		this.cId = cId;
		this.pDetails = pDetails;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public List<ProductDetails> getpDetails() {
		return pDetails;
	}

	public void setpDetails(List<ProductDetails> pDetails) {
		this.pDetails = pDetails;
	}

	@Override
	public String toString() {
		return "CustomerCart [customerName=" + customerName + ", cId=" + cId + ", pDetails=" + pDetails + "]";
	}
	
}