package com.easyway.mismclient.model;

import com.easyway.mismclient.base.BaseModel;

import java.util.List;

/**
 * Created by admin on 2018/4/25.
 */

public class MProdEnterDetailBean extends BaseModel{


    /**
     * Reg_id : 3726
     * OperateID : null
     * OperateName : null
     * Details : [{"Record_id":12153,"ProductInfoID":371044,"ProductInfoName":"压力延长管","Specification":"PM6148","FactAmount":0,"IsVoid":"0"},{"Record_id":12154,"ProductInfoID":371044,"ProductInfoName":"压力延长管","Specification":"PM6148","FactAmount":0,"IsVoid":"0"}]
     */

    private int Reg_id;
    private String DepartmentCollarID;

    public String getDepartmentCollarID() {
        return DepartmentCollarID;
    }

    public void setDepartmentCollarID(String departmentCollarID) {
        DepartmentCollarID = departmentCollarID;
    }

    private Object OperateID;
    private Object OperateName;
    private List<DetailsBean> Details;

    public int getReg_id() {
        return Reg_id;
    }

    public void setReg_id(int Reg_id) {
        this.Reg_id = Reg_id;
    }

    public Object getOperateID() {
        return OperateID;
    }

    public void setOperateID(Object OperateID) {
        this.OperateID = OperateID;
    }

    public Object getOperateName() {
        return OperateName;
    }

    public void setOperateName(Object OperateName) {
        this.OperateName = OperateName;
    }

    public List<DetailsBean> getDetails() {
        return Details;
    }

    public void setDetails(List<DetailsBean> Details) {
        this.Details = Details;
    }

    public static class DetailsBean {
        /**
         * Record_id : 12153
         * ProductInfoID : 371044
         *
         * ProductInfoName : 压力延长管
         * Specification : PM6148
         *
         * FactAmount : 0.0
         * IsVoid : 0
         */

        private int Record_id;
        private int ProductInfoID;
        private String ProductInfoName;
        private String Specification;
        private int FactAmount;
        private String IsVoid;

        public int getRecord_id() {
            return Record_id;
        }

        public void setRecord_id(int Record_id) {
            this.Record_id = Record_id;
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

        public String getSpecification() {
            return Specification;
        }

        public void setSpecification(String Specification) {
            this.Specification = Specification;
        }

        public int getFactAmount() {
            return FactAmount;
        }

        public void setFactAmount(int FactAmount) {
            this.FactAmount = FactAmount;
        }

        public String getIsVoid() {
            return IsVoid;
        }

        public void setIsVoid(String IsVoid) {
            this.IsVoid = IsVoid;
        }
    }
}
