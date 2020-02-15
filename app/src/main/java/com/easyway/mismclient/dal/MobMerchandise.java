package com.easyway.mismclient.dal;

import java.util.List;

public class MobMerchandise {
	public String MerchandiseID;
	public String MerchandiseName;
	public String Specification;
	public String ProductID;
	public String ProductName;
	public String BarCode;
	public String SubBarCode;
	public String Buy;

	public List<ProductInventoryDetail> Details;

	public String getMerchandiseID() {
		return MerchandiseID;
	}

	public void setMerchandiseID(String MerchandiseID) {
		this.MerchandiseID = MerchandiseID;
	}

	public String getMerchandiseName() {
		return MerchandiseName;
	}

	public void setMerchandiseName(String MerchandiseName) {
		this.MerchandiseName = MerchandiseName;
	}

	public String getSpecification() {
		return Specification;
	}

	public void setSpecification(String Specification) {
		this.Specification = Specification;
	}

	public String getProductID() {
		return ProductID;
	}

	public void setProductID(String ProductID) {
		this.ProductID = ProductID;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String ProductName) {
		this.ProductName = ProductName;
	}

	public String getBarCode() {
		return BarCode;
	}

	public void setBarCode(String BarCode) {
		this.BarCode = BarCode;
	}

	public String getSubBarCode() {
		return SubBarCode;
	}

	public void setSubBarCode(String SubBarCode) {
		this.SubBarCode = SubBarCode;
	}
	
	public String getBuy() {
		return Buy;
	}

	public void setSubBuy(String Buy) {
		this.Buy = Buy;
	}

	public List<ProductInventoryDetail> GetDetails() {
		return Details;
	}

	public void SetDetails(List<ProductInventoryDetail> Details) {
		this.Details = Details;
	}
}
