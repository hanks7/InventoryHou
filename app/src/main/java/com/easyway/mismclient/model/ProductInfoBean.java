package com.easyway.mismclient.model;

import com.easyway.mismclient.base.BaseModel;

/**
 * @author 侯建军 47466436@qq.com
 * @class com.easyway.mismclient.model.ProductInfoBean
 * @time 2018/7/27 15:01
 * @description 请填写描述
 */
public class ProductInfoBean extends BaseModel {
    int ProductInfoID;
    int BrandID;
    String CategoryCode;
    String CusCategoryCode;
    int EnterpriseID;
    int DetailID;
    int DefaultSupplierID;
    String GeneralName;
    int Specification;
    String Model;
    String Place;
    int Unit;
    int PackageUnit;
    int PackageQuantity;
    String StorageCondition;
    int MatInUnit;
    int MatInSplitNum;
    int UseUnit;
    int UseSplitNum;
    double PurchasePrice;
    double PriceCeiling;
    String PriceSource;
    double MinPurchaseAmount;
    String CreateTime;
    String UpdateTime;
    String PackingInstruction;
    String IsVoid;
    String Name;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    double ChargePrice;
    double AuditPriceCeiling;
    int HealthPermitID;
    int Attachment;

    public int getProductInfoID() {
        return ProductInfoID;
    }

    public void setProductInfoID(int productInfoID) {
        ProductInfoID = productInfoID;
    }

    public int getBrandID() {
        return BrandID;
    }

    public void setBrandID(int brandID) {
        BrandID = brandID;
    }

    public String getCategoryCode() {
        return CategoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        CategoryCode = categoryCode;
    }

    public String getCusCategoryCode() {
        return CusCategoryCode;
    }

    public void setCusCategoryCode(String cusCategoryCode) {
        CusCategoryCode = cusCategoryCode;
    }

    public int getEnterpriseID() {
        return EnterpriseID;
    }

    public void setEnterpriseID(int enterpriseID) {
        EnterpriseID = enterpriseID;
    }

    public int getDetailID() {
        return DetailID;
    }

    public void setDetailID(int detailID) {
        DetailID = detailID;
    }

    public int getDefaultSupplierID() {
        return DefaultSupplierID;
    }

    public void setDefaultSupplierID(int defaultSupplierID) {
        DefaultSupplierID = defaultSupplierID;
    }


    public String getGeneralName() {
        return GeneralName;
    }

    public void setGeneralName(String generalName) {
        GeneralName = generalName;
    }

    public int getSpecification() {
        return Specification;
    }

    public void setSpecification(int specification) {
        Specification = specification;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public int getUnit() {
        return Unit;
    }

    public void setUnit(int unit) {
        Unit = unit;
    }

    public int getPackageUnit() {
        return PackageUnit;
    }

    public void setPackageUnit(int packageUnit) {
        PackageUnit = packageUnit;
    }

    public int getPackageQuantity() {
        return PackageQuantity;
    }

    public void setPackageQuantity(int packageQuantity) {
        PackageQuantity = packageQuantity;
    }

    public String getStorageCondition() {
        return StorageCondition;
    }

    public void setStorageCondition(String storageCondition) {
        StorageCondition = storageCondition;
    }

    public int getMatInUnit() {
        return MatInUnit;
    }

    public void setMatInUnit(int matInUnit) {
        MatInUnit = matInUnit;
    }

    public int getMatInSplitNum() {
        return MatInSplitNum;
    }

    public void setMatInSplitNum(int matInSplitNum) {
        MatInSplitNum = matInSplitNum;
    }

    public int getUseUnit() {
        return UseUnit;
    }

    public void setUseUnit(int useUnit) {
        UseUnit = useUnit;
    }

    public int getUseSplitNum() {
        return UseSplitNum;
    }

    public void setUseSplitNum(int useSplitNum) {
        UseSplitNum = useSplitNum;
    }

    public double getPurchasePrice() {
        return PurchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        PurchasePrice = purchasePrice;
    }

    public double getPriceCeiling() {
        return PriceCeiling;
    }

    public void setPriceCeiling(double priceCeiling) {
        PriceCeiling = priceCeiling;
    }

    public String getPriceSource() {
        return PriceSource;
    }

    public void setPriceSource(String priceSource) {
        PriceSource = priceSource;
    }

    public double getMinPurchaseAmount() {
        return MinPurchaseAmount;
    }

    public void setMinPurchaseAmount(double minPurchaseAmount) {
        MinPurchaseAmount = minPurchaseAmount;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(String updateTime) {
        UpdateTime = updateTime;
    }

    public String getPackingInstruction() {
        return PackingInstruction;
    }

    public void setPackingInstruction(String packingInstruction) {
        PackingInstruction = packingInstruction;
    }

    public String getIsVoid() {
        return IsVoid;
    }

    public void setIsVoid(String isVoid) {
        IsVoid = isVoid;
    }

    public double getChargePrice() {
        return ChargePrice;
    }

    public void setChargePrice(double chargePrice) {
        ChargePrice = chargePrice;
    }

    public double getAuditPriceCeiling() {
        return AuditPriceCeiling;
    }

    public void setAuditPriceCeiling(double auditPriceCeiling) {
        AuditPriceCeiling = auditPriceCeiling;
    }

    public int getHealthPermitID() {
        return HealthPermitID;
    }

    public void setHealthPermitID(int healthPermitID) {
        HealthPermitID = healthPermitID;
    }

    public int getAttachment() {
        return Attachment;
    }

    public void setAttachment(int attachment) {
        Attachment = attachment;
    }
}
