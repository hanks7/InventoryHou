package com.easyway.mismclient.dal;

import java.util.List;

public class MobileInventorybalance {
	public int MobileInventorybalanceID ;
    public int ProductInfoID ;
    public String ProductInfoName ;
    public String EnterpriseID ;
    public String EnterpriseName ;
    public String GeneralName ;
    public String Specification ;
    public String Model ;
    public String BarCode ;
    public String SubBarCode ;
    public String BarCodeSource ;
    public String SubBarCodeSource ;
    public String ProductionDate ;
    public String EffectiveDate ;
    public String Lot ;
    public String SN ;
    public Float Amount ;
    public String UnitName ;
    public String State ;
    public String Remark ;
    public String OperateID ;
    public String OperateName ;
    public String CreateTime ;
    public String UpdateTime ;
    public String DeptCode ;
    public String Buy ; //�������0�Թ�1����
	public List<ProductInventoryDetail> DetailList;
	
	public int getMobileInventorybalanceID() {
		return MobileInventorybalanceID;
	}

	public void setMobileInventorybalanceID(int MobileInventorybalanceID) {
		this.MobileInventorybalanceID = MobileInventorybalanceID;
	}
	
	public int getProductInfoID() {
		return ProductInfoID;
	}

	public void setProductInfoID(int ProductInfoID) {
		this.ProductInfoID = ProductInfoID;
	}
	
	public String getProductInfoName() {
		return ProductInfoName;
	}

	public void setProductInfoName(String ProductInfoName) {
		this.ProductInfoName = ProductInfoName;
	}
	
	public String getEnterpriseID() {
		return EnterpriseID;
	}

	public void setEnterpriseID(String EnterpriseID) {
		this.EnterpriseID = EnterpriseID;
	}
	
	public String getEnterpriseName() {
		return EnterpriseName;
	}

	public void setEnterpriseName(String EnterpriseName) {
		this.EnterpriseName = EnterpriseName;
	}
	public String getGeneralName() {
		return GeneralName;
	}

	public void setGeneralNamen(String GeneralName) {
		this.GeneralName = GeneralName;
	}
	public String getSpecification() {
		return Specification;
	}

	public void setSpecification(String Specification) {
		this.Specification = Specification;
	}
	public String getModel() {
		return Model;
	}

	public void setModel(String Model) {
		this.Model = Model;
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
	public String getBarCodeSource() {
		return BarCodeSource;
	}

	public void setBarCodeSource(String BarCodeSource) {
		this.BarCodeSource = BarCodeSource;
	}
	public String getSubBarCodeSource() {
		return BarCodeSource;
	}

	public void setSubBarCodeSource(String SubBarCodeSource) {
		this.SubBarCodeSource = SubBarCodeSource;
	}
	public String getBuy() {
		return Buy;
	}

	public void setBuy(String Buy) {
		this.Buy = Buy;
	}
	public String getProductionDate() {
		return ProductionDate;
	}

	public void setProductionDate(String ProductionDate) {
		this.ProductionDate = ProductionDate;
	}
	public String getEffectiveDate() {
		return EffectiveDate;
	}

	public void setEffectiveDate(String EffectiveDate) {
		this.EffectiveDate = EffectiveDate;
	}
	
	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String CreateTime) {
		this.CreateTime = CreateTime;
	}
	
	public String getUpdateTime() {
		return UpdateTime;
	}

	public void setUpdateTime(String CreateTime) {
		this.CreateTime = CreateTime;
	}
	public String getRemark() {
		return Remark;
	}

	public void setRemark(String Remark) {
		this.Remark = Remark;
	}
	
	public String getLot() {
		return Lot;
	}

	public void setLot(String Lot) {
		this.Lot = Lot;
	}
	public String getSN() {
		return SN;
	}

	public void setSN(String SN) {
		this.SN = SN;
	}
	public Float getAmount() {
		return Amount;
	}

	public void setAmount(Float Amount) {
		if(Amount != null)
		{
		this.Amount = Amount;
		}
		else
			this.Amount=(float) 0;
	}
	
	public String getUnitName() {
		return UnitName;
	}

	public void setUnitName(String UnitName) {
		this.UnitName = UnitName;
	}
	
	public String getState() {
		return State;
	}

	public void setState(String State) {
		this.State = State;
	}
	public String getOperateID () {
		return OperateID;
	}

	public void setOperateID (String OperateID ) {
		this.OperateID  = OperateID ;
	}
	public String getOperateName () {
		return OperateName;
	}

	public void setOperateName  (String OperateName) {
		this.OperateName   = OperateName;
	}
	public String getDeptCode () {
		return DeptCode;
	}

	public void setDeptCode  (String DeptCode) {
		this.DeptCode   = DeptCode;
	}
	
	public List<ProductInventoryDetail> getDetailList(){
		return DetailList;
	}
	public void setDetailList(List<ProductInventoryDetail> DetailList)
	{
		this.DetailList = DetailList;
	}
	
}
 