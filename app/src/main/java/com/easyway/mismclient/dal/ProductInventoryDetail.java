package com.easyway.mismclient.dal;

public class ProductInventoryDetail {
	public int DetailID;
	public String ProductInfoID;
	public String DeptCode;
	public String DeptCodeName;
	public String SupplierID;
	public String SupplierName;
	public String ProductBatch;
	public String SerialNumber;
	public String ExpireDate;
	public String ProductionDate;
	public String RegistrationCard;
	public Float RealTimeAmount;
	public Float FactAmount;
	private String UnitName;
	private String EnterpriseName;
	
	public int getDetailID() {
		return DetailID;
	}

	public void DetailID(int DetailID) {
		this.DetailID = DetailID;
	}
	

	public String getProductInfoID() {
		return ProductInfoID;
	}

	public void setProductInfoID(String ProductInfoID) {
		this.ProductInfoID = ProductInfoID;
	}
	
	public String getDeptCode() {
		return DeptCode;
	}

	public void setDeptCode(String DeptCode) {
		this.DeptCode = DeptCode;
	}
	
	public String getDeptCodeName() {
		return DeptCodeName;
	}

	public void setDeptCodeName(String DeptCodeName) {
		this.DeptCodeName = DeptCodeName;
	}
	
	public String getSupplierID() {
		return SupplierID;
	}

	public void setSupplierID(String SupplierID) {
		this.SupplierID = SupplierID;
	}
	public String getSupplierName() {
		return SupplierName;
	}

	public void setSupplierName(String SupplierName) {
		this.SupplierName = SupplierName;
	}
	
	public String getProductBatch() {
		return ProductBatch;
	}

	public void setProductBatch(String ProductBatch) {
		this.ProductBatch = ProductBatch;
	}
	public String getSerialNumber() {
		return SerialNumber;
	}

	public void setSerialNumber(String SerialNumber) {
		this.SerialNumber = SerialNumber;
	}
	public String getExpireDate() {
		return ExpireDate;
	}

	public void setExpireDate(String ExpireDate) {
		this.ExpireDate = ExpireDate;
	}
	public String getProductionDate() {
		return ProductionDate;
	}

	public void setProductionDate(String ProductionDate) {
		this.ProductionDate = ProductionDate;
	}
	
	public String getRegistrationCard() {
		return RegistrationCard;
	}

	public void setRegistrationCard(String RegistrationCard) {
		this.RegistrationCard = RegistrationCard;
	}
	public Float getRealTimeAmount() {
		return RealTimeAmount;
	}

	public void setRealTimeAmount(Float RealTimeAmount) {
		this.RealTimeAmount = RealTimeAmount;
	}
	public Float getFactAmount() {
		return FactAmount;
	}
	


	public void setFactAmount(Float FactAmount) {
		this.FactAmount = FactAmount;
	}
	
	public String getUnitName() {
		return UnitName;
	}

	public void setUnitName(String UnitName) {
		this.UnitName = UnitName;
	}
	public String getEnterpriseName() {
		return EnterpriseName;
	}

	public void setEnterpriseName(String EnterpriseName) {
		this.EnterpriseName = EnterpriseName;
	}
	
	
	public String getDescription() {
		return "��Ӧ��:" + getSupplierName() + "\r\nע��֤:" + getRegistrationCard() + "\r\n��������:" +  getRealTimeAmount() ;
	}
}
