package com.easyway.mismclient.model;

import java.util.List;

/**
 * @author 侯建军 47466436@qq.com
 * @class com.easyway.mismclient.model.OfflineInverntoryBean
 * @time 2018/7/31 14:46
 * @description 请填写描述
 */
public class OfflineInverntoryBean {

    private int ProductInfoID;//产品号
    private String ProductInfoName;//品名
    private String EnterpriseName;//生产厂商
    private String GeneralName;//通用名

    private String Specification;//规格
    private String Model;//型号
    private String ProductionDate;//生产日期
    private String EffectiveDate;//有效期
    private String Lot;//批号
    private String SN;//序列号
    private String RegisterNum;//注册证
    private int isChecked;//是否已经扫描过了

    public int getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }

    private List<DetailListBean> DetailList;
    private List<LicenceListBean> LicenceList;

    public List<DetailListBean> getDetailList() {
        return DetailList;
    }

    public void setDetailList(List<DetailListBean> detailList) {
        DetailList = detailList;
    }

    public List<LicenceListBean> getLicenceList() {
        return LicenceList;
    }

    public void setLicenceList(List<LicenceListBean> licenceList) {
        LicenceList = licenceList;
    }

    public int getProductInfoID() {
        return ProductInfoID;
    }

    public void setProductInfoID(int productInfoID) {
        ProductInfoID = productInfoID;
    }

    public String getProductInfoName() {
        return ProductInfoName;
    }

    public void setProductInfoName(String productInfoName) {
        ProductInfoName = productInfoName;
    }

    public String getEnterpriseName() {
        return EnterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        EnterpriseName = enterpriseName;
    }

    public String getGeneralName() {
        return GeneralName;
    }

    public void setGeneralName(String generalName) {
        GeneralName = generalName;
    }

    public String getSpecification() {
        return Specification;
    }

    public void setSpecification(String specification) {
        Specification = specification;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getProductionDate() {
        return ProductionDate;
    }

    public void setProductionDate(String productionDate) {
        ProductionDate = productionDate;
    }

    public String getEffectiveDate() {
        return EffectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        EffectiveDate = effectiveDate;
    }

    public String getLot() {
        return Lot;
    }

    public void setLot(String lot) {
        Lot = lot;
    }

    public String getSN() {
        return SN;
    }

    public void setSN(String SN) {
        this.SN = SN;
    }

    public String getRegisterNum() {
        return RegisterNum;
    }

    public void setRegisterNum(String registerNum) {
        RegisterNum = registerNum;
    }
}
