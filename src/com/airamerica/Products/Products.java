package com.airamerica.Products;
public abstract class Products {

	
	/*
	 * General fields for all Products
	 */
	protected String code;
	private String productType;
	private String productName;
	
	/*
	 * SuperClass Constructor
	 */

	/*
	 * Getters+Setters for Products class arguments
	 */
	
	public String getCode() {
		return code;
	}

	public Products(String code, String productType) {
		super();
		this.code = code;
		this.productType = productType;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}


	