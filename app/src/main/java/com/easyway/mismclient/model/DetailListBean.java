package com.easyway.mismclient.model;

/**
 * Created by admin on 2018/4/24.
 */

public class DetailListBean {
    /**
     * SupplierName : 上海任远商贸有限公司
     * RealTimeAmount : 0
     * FactAmount : 1
     * SupplierID : 3502
     * DetailID : 0
     */

    private String SupplierName;
    private int RealTimeAmount;
    private int FactAmount;
    private int SupplierID;
    private int DetailID;

    public int getInventoryAmount() {
        return InventoryAmount;
    }

    public void setInventoryAmount(int inventoryAmount) {
        InventoryAmount = inventoryAmount;
    }

    private int InventoryAmount;

    public String getSupplierName() {
        return SupplierName;
    }

    public void setSupplierName(String SupplierName) {
        this.SupplierName = SupplierName;
    }

    public int getRealTimeAmount() {
        return RealTimeAmount;
    }

    public void setRealTimeAmount(int RealTimeAmount) {
        this.RealTimeAmount = RealTimeAmount;
    }

    public int getFactAmount() {
        return FactAmount;
    }

    public void setFactAmount(int FactAmount) {
        this.FactAmount = FactAmount;
    }

    public int getSupplierID() {
        return SupplierID;
    }

    public void setSupplierID(int SupplierID) {
        this.SupplierID = SupplierID;
    }

    public int getDetailID() {
        return DetailID;
    }

    public void setDetailID(int DetailID) {
        this.DetailID = DetailID;
    }
}
