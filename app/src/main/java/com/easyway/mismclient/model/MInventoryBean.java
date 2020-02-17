package com.easyway.mismclient.model;

import com.easyway.mismclient.base.BaseModel;

import java.util.List;

/**
 * Created by admin on 2018/4/12.
 */

public class MInventoryBean extends BaseModel {


    /**
     * MobileInventorybalanceID : 0
     * ProductInfoID : 371044
     * ProductInfoName : 压力延长管
     * EnterpriseID : 3165
     * EnterpriseName : 美国merit医疗设备有限公司
     * GeneralName : null
     * Specification : PM6148
     * Model : null
     * BarCode : 0134987350724435
     * SubBarCode : 1720050010210101
     * BarCodeSource : null
     * SubBarCodeSource : null
     * ProductionDate : null
     * EffectiveDate : 2020-05-31
     * Lot : 210101
     * SN :
     * Amount : 0.0
     * UnitName : 根
     * State : null
     * OperateID : null
     * OperateName : null
     * DeptCode : null
     * Buy : 0
     * RegisterNum :
     * DetailList : [{"DetailID":1452,"SupplierID":3502,"SupplierName":"上海任远商贸有限公司","RegistrationCard":"国械注进20143665578(2014-12-05~2019-12-04)","RealTimeAmount":3,"FactAmount":3},{"DetailID":1453,"SupplierID":3602,"SupplierName":"河南惠佳医疗器械有限公司","RegistrationCard":"国械注进20143665578(2014-12-05~2019-12-04)","RealTimeAmount":2,"FactAmount":2}]
     * LicenceList : [{"StartDate":"","EndDate":"","RegistrationCard":"国械注进20143665578(2014-12-05~2019-12-04)"}]
     */

    private int MobileInventorybalanceID;
    private int ProductInfoID;
    private String ProductInfoName;
    private String EnterpriseID;
    private String EnterpriseName;
    private String GeneralName;
    private String Specification;
    private String Model;
    private String BarCode;
    private String SubBarCode;
    private String BarCodeSource;
    private String SubBarCodeSource;
    private String ProductionDate;
    private String EffectiveDate;
    private String Lot;
    private String SN;
    private double Amount;
    private String UnitName;
    private String State;
    private String OperateID;
    private String OperateName;
    private String DeptCode;
    private String Buy = "-1";
    private String RegisterNum;
    private List<DetailListBean> DetailList;
    private List<LicenceListBean> LicenceList;

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
        if (getBuy() == null) {
            return ProductInfoName + "(未定义)";
        } else if (getBuy().equals("-1")) {
            return ProductInfoName;
        } else if (getBuy().equals("0")) {
            return ProductInfoName + "(自购)";
        } else if (getBuy().equals("1")) {
            return ProductInfoName + "(备货)";
        } else {
            return ProductInfoName + "(未定义)";
        }

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

    public void setGeneralName(String GeneralName) {
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
        return SubBarCodeSource;
    }

    public void setSubBarCodeSource(String SubBarCodeSource) {
        this.SubBarCodeSource = SubBarCodeSource;
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

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double Amount) {
        this.Amount = Amount;
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

    public String getOperateID() {
        return OperateID;
    }

    public void setOperateID(String OperateID) {
        this.OperateID = OperateID;
    }

    public String getOperateName() {
        return OperateName;
    }

    public void setOperateName(String OperateName) {
        this.OperateName = OperateName;
    }

    public String getDeptCode() {
        return DeptCode;
    }

    public void setDeptCode(String DeptCode) {
        this.DeptCode = DeptCode;
    }

    public String getBuy() {
        return Buy;
    }

    public void setBuy(String Buy) {
        this.Buy = Buy;
    }

    public String getRegisterNum() {
        return RegisterNum;
    }

    public void setRegisterNum(String RegisterNum) {
        this.RegisterNum = RegisterNum;
    }

    public List<DetailListBean> getDetailList() {
        return DetailList;
    }

    public void setDetailList(List<DetailListBean> DetailList) {
        this.DetailList = DetailList;
    }

    public List<LicenceListBean> getLicenceList() {
        return LicenceList;
    }

    public void setLicenceList(List<LicenceListBean> LicenceList) {
        this.LicenceList = LicenceList;
    }
}
