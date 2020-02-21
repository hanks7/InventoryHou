package com.easyway.mismclient.model;

import java.util.List;

/**
 *             "DetailID": 2141,
 *             "InStoreNo": "201810230003",
 *             "ProductInfoID": 167805,
 *             "ProductBatch": "11",
 *             "PurchasePrice": 0.2,
 *             "UpdatePurchasePrice": 0,
 *             "RetailPrice": 0,
 *             "Amount": 20,
 *             "OutAmount": 0,
 *             "ThisAmount": 0,
 *             "UpdateAmount": 0,
 *             "UPCOUNT": 0,
 *             "UPAmount": 0,
 *             "UPInvoiceMoney": 0,
 *             "Moneys": 4,
 *             "Unit": 164916,
 *             "ExpireDate": "1900-01-01T00:00:00",
 *             "ProductionDate": "1900-01-01T00:00:00",
 *             "RegistrationCard": "无注册证第20180814000111号(2018-08-14~2023-08-14)",
 *             "CustomCategoryID": "9202010000",
 *             "CusCategoryShortName": " ",
 *             "ShelfID": 0,
 *             "CreateTime": "2018-10-23T14:55:51.847",
 *             "UpdateTime": "2018-10-23T14:55:51.847",
 *             "IsVoid": "0",
 *             "KPAmount": 0,
 *             "ProductName": "日立样品杯",
 *             "SpecName": "12*37",
 *             "ModelName": "",
 *             "UnitName": "只",
 *             "CustomCategoryName": null,
 *             "CategoryName": "默认产品类别",
 *             "Check": false,
 *             "SerialNumber": "",
 *             "Price": 0,
 *             "InvoiceMoney": 0,
 *             "OrderMoney": 0,
 *             "InvoiceDate": "0001-01-01T00:00:00",
 *             "SupplierName": "广州市荔湾区明兴玻璃工艺店",
 *             "isHighValue": "1",
 *             "OpDate": "0001-01-01T00:00:00",
 *             "CusCategoryName": "化验材料",
 *             "SupplierID": "2615",
 *             "IsFree": false,
 *             "JITS_SALEACCOUNTROWNO": 0,
 *             "OutDetailID": 0,
 *             "Attachments": []
 */
public class WarehouseDetailList {
    private int DetailID;

    private String InStoreNo;

    private int ProductInfoID;

    private String ProductBatch;

    private double PurchasePrice;

    private int UpdatePurchasePrice;

    private int RetailPrice;

    private int Amount;

    private int OutAmount;

    private int ThisAmount;

    private int UpdateAmount;

    private int UPCOUNT;

    private int UPAmount;

    private int UPInvoiceMoney;

    private String ExpectInvoiceDate;

    private String Moneys;

    private int Unit;

    private String ExpireDate;

    private String HealthPermitCode;

    private String IsHaveAccCertificate;

    private String ProductionDate;

    private String RegistrationCard;

    private String CustomCategoryID;

    private String CusCategoryShortName;

    private int ShelfID;

    private String CreateTime;

    private String UpdateTime;

    private String state;

    private String error;

    private String IsVoid;

    private int KPAmount;

    private String ProductName;

    private String Prod_MapID;

    private String GeneralName;

    private String Specification;

    private String SpecName;

    private String Model;

    private String ModelName;

    private String UnitName;

    private String Error;

    private String CustomCategoryName;

    private String CategoryName;

    private boolean Check;

    private String Department;

    private String DepartmentName;

    private String InStoreCode;

    private String SerialNumber;

    private int Price;

    private int InvoiceMoney;

    private int OrderMoney;

    private String ProductModSpec;

    private String BCode1;

    private String BCode2;

    private String InvoiceCode;

    private String InvoiceDate;

    private String SupplierName;

    private String BLMakerName;

    private String AuditorName;

    private String BedNum;

    private String isHighValue;

    private String Status;

    private String WDetail_ID;

    private String OpDate;

    private String DeptCode;

    private String DeptName;

    private String DeptAddress;

    private String OutStoreNumber;

    private String WareHouseType;

    private String Remark;

    private String CusCategoryName;

    private String HospitalSpec;

    private String SupplierID;

    private String WarehouseDate;

    private String HisProductCode;

    private String PaceMakerTypeID;

    private String PaceMakerTypeName;

    private boolean IsFree;

    private String JITS_SALEACCOUNTNO;

    private int JITS_SALEACCOUNTROWNO;

    private String SumChineseMoney;

    private String SumChineseTotalMoney;

    private int OutDetailID;

    private String OnlyBCode;

    private List<Attachments> Attachments;

    public void setDetailID(int DetailID) {
        this.DetailID = DetailID;
    }

    public int getDetailID() {
        return this.DetailID;
    }

    public void setInStoreNo(String InStoreNo) {
        this.InStoreNo = InStoreNo;
    }

    public String getInStoreNo() {
        return this.InStoreNo;
    }

    public void setProductInfoID(int ProductInfoID) {
        this.ProductInfoID = ProductInfoID;
    }

    public int getProductInfoID() {
        return this.ProductInfoID;
    }

    public void setProductBatch(String ProductBatch) {
        this.ProductBatch = ProductBatch;
    }

    public String getProductBatch() {
        return this.ProductBatch;
    }

    public void setPurchasePrice(double PurchasePrice) {
        this.PurchasePrice = PurchasePrice;
    }

    public double getPurchasePrice() {
        return this.PurchasePrice;
    }

    public void setUpdatePurchasePrice(int UpdatePurchasePrice) {
        this.UpdatePurchasePrice = UpdatePurchasePrice;
    }

    public int getUpdatePurchasePrice() {
        return this.UpdatePurchasePrice;
    }

    public void setRetailPrice(int RetailPrice) {
        this.RetailPrice = RetailPrice;
    }

    public int getRetailPrice() {
        return this.RetailPrice;
    }

    public void setAmount(int Amount) {
        this.Amount = Amount;
    }

    public int getAmount() {
        return this.Amount;
    }

    public void setOutAmount(int OutAmount) {
        this.OutAmount = OutAmount;
    }

    public int getOutAmount() {
        return this.OutAmount;
    }

    public void setThisAmount(int ThisAmount) {
        this.ThisAmount = ThisAmount;
    }

    public int getThisAmount() {
        return this.ThisAmount;
    }

    public void setUpdateAmount(int UpdateAmount) {
        this.UpdateAmount = UpdateAmount;
    }

    public int getUpdateAmount() {
        return this.UpdateAmount;
    }

    public void setUPCOUNT(int UPCOUNT) {
        this.UPCOUNT = UPCOUNT;
    }

    public int getUPCOUNT() {
        return this.UPCOUNT;
    }

    public void setUPAmount(int UPAmount) {
        this.UPAmount = UPAmount;
    }

    public int getUPAmount() {
        return this.UPAmount;
    }

    public void setUPInvoiceMoney(int UPInvoiceMoney) {
        this.UPInvoiceMoney = UPInvoiceMoney;
    }

    public int getUPInvoiceMoney() {
        return this.UPInvoiceMoney;
    }

    public void setExpectInvoiceDate(String ExpectInvoiceDate) {
        this.ExpectInvoiceDate = ExpectInvoiceDate;
    }

    public String getExpectInvoiceDate() {
        return this.ExpectInvoiceDate;
    }

    public void setMoneys(String Moneys) {
        this.Moneys = Moneys;
    }

    public String getMoneys() {
        return this.Moneys;
    }

    public void setUnit(int Unit) {
        this.Unit = Unit;
    }

    public int getUnit() {
        return this.Unit;
    }

    public void setExpireDate(String ExpireDate) {
        this.ExpireDate = ExpireDate;
    }

    public String getExpireDate() {
        return this.ExpireDate;
    }

    public void setHealthPermitCode(String HealthPermitCode) {
        this.HealthPermitCode = HealthPermitCode;
    }

    public String getHealthPermitCode() {
        return this.HealthPermitCode;
    }

    public void setIsHaveAccCertificate(String IsHaveAccCertificate) {
        this.IsHaveAccCertificate = IsHaveAccCertificate;
    }

    public String getIsHaveAccCertificate() {
        return this.IsHaveAccCertificate;
    }

    public void setProductionDate(String ProductionDate) {
        this.ProductionDate = ProductionDate;
    }

    public String getProductionDate() {
        return this.ProductionDate;
    }

    public void setRegistrationCard(String RegistrationCard) {
        this.RegistrationCard = RegistrationCard;
    }

    public String getRegistrationCard() {
        return this.RegistrationCard;
    }

    public void setCustomCategoryID(String CustomCategoryID) {
        this.CustomCategoryID = CustomCategoryID;
    }

    public String getCustomCategoryID() {
        return this.CustomCategoryID;
    }

    public void setCusCategoryShortName(String CusCategoryShortName) {
        this.CusCategoryShortName = CusCategoryShortName;
    }

    public String getCusCategoryShortName() {
        return this.CusCategoryShortName;
    }

    public void setShelfID(int ShelfID) {
        this.ShelfID = ShelfID;
    }

    public int getShelfID() {
        return this.ShelfID;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public String getCreateTime() {
        return this.CreateTime;
    }

    public void setUpdateTime(String UpdateTime) {
        this.UpdateTime = UpdateTime;
    }

    public String getUpdateTime() {
        return this.UpdateTime;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return this.error;
    }

    public void setIsVoid(String IsVoid) {
        this.IsVoid = IsVoid;
    }

    public String getIsVoid() {
        return this.IsVoid;
    }

    public void setKPAmount(int KPAmount) {
        this.KPAmount = KPAmount;
    }

    public int getKPAmount() {
        return this.KPAmount;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getProductName() {
        return this.ProductName;
    }

    public void setProd_MapID(String Prod_MapID) {
        this.Prod_MapID = Prod_MapID;
    }

    public String getProd_MapID() {
        return this.Prod_MapID;
    }

    public void setGeneralName(String GeneralName) {
        this.GeneralName = GeneralName;
    }

    public String getGeneralName() {
        return this.GeneralName;
    }

    public void setSpecification(String Specification) {
        this.Specification = Specification;
    }

    public String getSpecification() {
        return this.Specification;
    }

    public void setSpecName(String SpecName) {
        this.SpecName = SpecName;
    }

    public String getSpecName() {
        return this.SpecName;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    public String getModel() {
        return this.Model;
    }

    public void setModelName(String ModelName) {
        this.ModelName = ModelName;
    }

    public String getModelName() {
        return this.ModelName;
    }

    public void setUnitName(String UnitName) {
        this.UnitName = UnitName;
    }

    public String getUnitName() {
        return this.UnitName;
    }

    public void setCustomCategoryName(String CustomCategoryName) {
        this.CustomCategoryName = CustomCategoryName;
    }

    public String getCustomCategoryName() {
        return this.CustomCategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }

    public String getCategoryName() {
        return this.CategoryName;
    }

    public void setCheck(boolean Check) {
        this.Check = Check;
    }

    public boolean getCheck() {
        return this.Check;
    }

    public void setDepartment(String Department) {
        this.Department = Department;
    }

    public String getDepartment() {
        return this.Department;
    }

    public void setDepartmentName(String DepartmentName) {
        this.DepartmentName = DepartmentName;
    }

    public String getDepartmentName() {
        return this.DepartmentName;
    }

    public void setInStoreCode(String InStoreCode) {
        this.InStoreCode = InStoreCode;
    }

    public String getInStoreCode() {
        return this.InStoreCode;
    }

    public void setSerialNumber(String SerialNumber) {
        this.SerialNumber = SerialNumber;
    }

    public String getSerialNumber() {
        return this.SerialNumber;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public int getPrice() {
        return this.Price;
    }

    public void setInvoiceMoney(int InvoiceMoney) {
        this.InvoiceMoney = InvoiceMoney;
    }

    public int getInvoiceMoney() {
        return this.InvoiceMoney;
    }

    public void setOrderMoney(int OrderMoney) {
        this.OrderMoney = OrderMoney;
    }

    public int getOrderMoney() {
        return this.OrderMoney;
    }

    public void setProductModSpec(String ProductModSpec) {
        this.ProductModSpec = ProductModSpec;
    }

    public String getProductModSpec() {
        return this.ProductModSpec;
    }

    public void setBCode1(String BCode1) {
        this.BCode1 = BCode1;
    }

    public String getBCode1() {
        return this.BCode1;
    }

    public void setBCode2(String BCode2) {
        this.BCode2 = BCode2;
    }

    public String getBCode2() {
        return this.BCode2;
    }

    public void setInvoiceCode(String InvoiceCode) {
        this.InvoiceCode = InvoiceCode;
    }

    public String getInvoiceCode() {
        return this.InvoiceCode;
    }

    public void setInvoiceDate(String InvoiceDate) {
        this.InvoiceDate = InvoiceDate;
    }

    public String getInvoiceDate() {
        return this.InvoiceDate;
    }

    public void setSupplierName(String SupplierName) {
        this.SupplierName = SupplierName;
    }

    public String getSupplierName() {
        return this.SupplierName;
    }

    public void setBLMakerName(String BLMakerName) {
        this.BLMakerName = BLMakerName;
    }

    public String getBLMakerName() {
        return this.BLMakerName;
    }

    public void setAuditorName(String AuditorName) {
        this.AuditorName = AuditorName;
    }

    public String getAuditorName() {
        return this.AuditorName;
    }

    public void setBedNum(String BedNum) {
        this.BedNum = BedNum;
    }

    public String getBedNum() {
        return this.BedNum;
    }

    public void setIsHighValue(String isHighValue) {
        this.isHighValue = isHighValue;
    }

    public String getIsHighValue() {
        return this.isHighValue;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getStatus() {
        return this.Status;
    }

    public void setWDetail_ID(String WDetail_ID) {
        this.WDetail_ID = WDetail_ID;
    }

    public String getWDetail_ID() {
        return this.WDetail_ID;
    }

    public void setOpDate(String OpDate) {
        this.OpDate = OpDate;
    }

    public String getOpDate() {
        return this.OpDate;
    }

    public void setDeptCode(String DeptCode) {
        this.DeptCode = DeptCode;
    }

    public String getDeptCode() {
        return this.DeptCode;
    }

    public void setDeptName(String DeptName) {
        this.DeptName = DeptName;
    }

    public String getDeptName() {
        return this.DeptName;
    }

    public void setDeptAddress(String DeptAddress) {
        this.DeptAddress = DeptAddress;
    }

    public String getDeptAddress() {
        return this.DeptAddress;
    }

    public void setOutStoreNumber(String OutStoreNumber) {
        this.OutStoreNumber = OutStoreNumber;
    }

    public String getOutStoreNumber() {
        return this.OutStoreNumber;
    }

    public void setWareHouseType(String WareHouseType) {
        this.WareHouseType = WareHouseType;
    }

    public String getWareHouseType() {
        return this.WareHouseType;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public String getRemark() {
        return this.Remark;
    }

    public void setCusCategoryName(String CusCategoryName) {
        this.CusCategoryName = CusCategoryName;
    }

    public String getCusCategoryName() {
        return this.CusCategoryName;
    }

    public void setHospitalSpec(String HospitalSpec) {
        this.HospitalSpec = HospitalSpec;
    }

    public String getHospitalSpec() {
        return this.HospitalSpec;
    }

    public void setSupplierID(String SupplierID) {
        this.SupplierID = SupplierID;
    }

    public String getSupplierID() {
        return this.SupplierID;
    }

    public void setWarehouseDate(String WarehouseDate) {
        this.WarehouseDate = WarehouseDate;
    }

    public String getWarehouseDate() {
        return this.WarehouseDate;
    }

    public void setHisProductCode(String HisProductCode) {
        this.HisProductCode = HisProductCode;
    }

    public String getHisProductCode() {
        return this.HisProductCode;
    }

    public void setPaceMakerTypeID(String PaceMakerTypeID) {
        this.PaceMakerTypeID = PaceMakerTypeID;
    }

    public String getPaceMakerTypeID() {
        return this.PaceMakerTypeID;
    }

    public void setPaceMakerTypeName(String PaceMakerTypeName) {
        this.PaceMakerTypeName = PaceMakerTypeName;
    }

    public String getPaceMakerTypeName() {
        return this.PaceMakerTypeName;
    }

    public void setIsFree(boolean IsFree) {
        this.IsFree = IsFree;
    }

    public boolean getIsFree() {
        return this.IsFree;
    }

    public void setJITS_SALEACCOUNTNO(String JITS_SALEACCOUNTNO) {
        this.JITS_SALEACCOUNTNO = JITS_SALEACCOUNTNO;
    }

    public String getJITS_SALEACCOUNTNO() {
        return this.JITS_SALEACCOUNTNO;
    }

    public void setJITS_SALEACCOUNTROWNO(int JITS_SALEACCOUNTROWNO) {
        this.JITS_SALEACCOUNTROWNO = JITS_SALEACCOUNTROWNO;
    }

    public int getJITS_SALEACCOUNTROWNO() {
        return this.JITS_SALEACCOUNTROWNO;
    }

    public void setSumChineseMoney(String SumChineseMoney) {
        this.SumChineseMoney = SumChineseMoney;
    }

    public String getSumChineseMoney() {
        return this.SumChineseMoney;
    }

    public void setSumChineseTotalMoney(String SumChineseTotalMoney) {
        this.SumChineseTotalMoney = SumChineseTotalMoney;
    }

    public String getSumChineseTotalMoney() {
        return this.SumChineseTotalMoney;
    }

    public void setOutDetailID(int OutDetailID) {
        this.OutDetailID = OutDetailID;
    }

    public int getOutDetailID() {
        return this.OutDetailID;
    }

    public void setOnlyBCode(String OnlyBCode) {
        this.OnlyBCode = OnlyBCode;
    }

    public String getOnlyBCode() {
        return this.OnlyBCode;
    }

    public void setAttachments(List<Attachments> Attachments) {
        this.Attachments = Attachments;
    }

    public List<Attachments> getAttachments() {
        return this.Attachments;
    }
}
