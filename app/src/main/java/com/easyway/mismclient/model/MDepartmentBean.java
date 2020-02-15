package com.easyway.mismclient.model;

import com.easyway.mismclient.base.BaseModel;

import java.util.List;

/**
 * Created by admin on 2018/4/24.
 */

public class MDepartmentBean extends BaseModel {

    /**
     * details : [{"DeptCode":"000001","DepartmentName":"设备科","PinYin":"sbk"},{"DeptCode":"000002","DepartmentName":"心内介入手术室","PinYin":"xnjrsss"},{"DeptCode":"000003","DepartmentName":"介入手术室一","PinYin":"jrsssy"},{"DeptCode":"000005","DepartmentName":"介入二手术室","PinYin":"jresss"},{"DeptCode":"000008","DepartmentName":"日间手术部","PinYin":"rjssb"},{"DeptCode":"000009","DepartmentName":"消化内镜","PinYin":"xhnj"},{"DeptCode":"000010","DepartmentName":"手术部","PinYin":"ssb"},{"DeptCode":"000011","DepartmentName":"骨科","PinYin":"gk"},{"DeptCode":"000012","DepartmentName":"急诊手术室","PinYin":"jzsss"},{"DeptCode":"000017","DepartmentName":"东区心内介入手术室(2)","PinYin":"dqxnjrsss"},{"DeptCode":"000018","DepartmentName":"东区介入手术室","PinYin":"dqjrsss"},{"DeptCode":"000019","DepartmentName":"东区心导管室","PinYin":"dqxdgs"},{"DeptCode":"000020","DepartmentName":"东区日间手术2部","PinYin":"dqrjss2b"},{"DeptCode":"000021","DepartmentName":"东区中心手术二部","PinYin":"dqzxsseb"},{"DeptCode":"000022","DepartmentName":"医学装备部二","PinYin":"yxzbbe"},{"DeptCode":"000023","DepartmentName":"消毒供应中心二部","PinYin":"xdgyzxeb"},{"DeptCode":"000025","DepartmentName":"医学装备部（3）","PinYin":"yxzbb3"},{"DeptCode":"000026","DepartmentName":"手术部（3）","PinYin":"ssb3"},{"DeptCode":"000027","DepartmentName":"骨科（3）","PinYin":"gk3"},{"DeptCode":"000028","DepartmentName":"惠济介入科手术室","PinYin":"hjjrksss"},{"DeptCode":"000029","DepartmentName":"东区骨科（二）","PinYin":"dqgke"},{"DeptCode":"000030","DepartmentName":"12121","PinYin":"12121"},{"DeptCode":"002614","DepartmentName":"心内介入手术室（2）","PinYin":"xnjrsss2"},{"DeptCode":"123456","DepartmentName":"血液净化中心","PinYin":"xyjhzx"},{"DeptCode":"200000","DepartmentName":"医学装备部","PinYin":"YXZBB"},{"DeptCode":"210000","DepartmentName":"医学工程部","PinYin":"YXGCB"}]
     */
    private List<DetailsBean> details;



    public List<DetailsBean> getDetails() {
        return details;
    }

    public void setDetails(List<DetailsBean> details) {
        this.details = details;
    }

    public static class DetailsBean {
        public DetailsBean(String deptCode, String departmentName, String pinYin) {
            DeptCode = deptCode;
            DepartmentName = departmentName;
            PinYin = pinYin;
        }

        /**
         * DeptCode : 000001
         * DepartmentName : 设备科
         * PinYin : sbk
         */

        private String DeptCode;
        private String DepartmentName;
        private String PinYin;

        public String getDeptCode() {
            return DeptCode;
        }

        public void setDeptCode(String DeptCode) {
            this.DeptCode = DeptCode;
        }

        public String getDepartmentName() {
            return DepartmentName;
        }

        public void setDepartmentName(String DepartmentName) {
            this.DepartmentName = DepartmentName;
        }

        public String getPinYin() {
            return PinYin;
        }

        public void setPinYin(String PinYin) {
            this.PinYin = PinYin;
        }
    }
}
