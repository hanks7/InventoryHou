package com.easyway.mismclient.model;

/**
 * Created by admin on 2018/4/24.
 */

public class MProdEnterBean extends MInventoryBean {

    /**
     * UnitName : 根
     * BarCode : 0134987350724435
     * BarCodeSource : 0134987350724435
     * Buy : 0
     * DeptCode : 000020
     * DetailList : [{"SupplierName":"上海任远商贸有限公司","RealTimeAmount":0,"FactAmount":1,"SupplierID":3502,"DetailID":0},{"SupplierName":"河南惠佳医疗器械有限公司","RealTimeAmount":0,"FactAmount":1,"SupplierID":3602,"DetailID":0}]
     * EffectiveDate : 2020-05-31
     * EnterpriseID : 3165
     * EnterpriseName : 美国merit医疗设备有限公司
     * LicenceList : [{"EndDate":"2019-12-04","RegistrationCard":"国械注进20143665578(2014-12-05~2019-12-04)","StartDate":"2014-12-05"},{"EndDate":"2015-06-07","RegistrationCard":"国食药监械(进)字2011第3661948号(2011-06-08~2015-06-07)","StartDate":"2011-06-08"}]
     * Lot : 210101
     * SubBarCodeSource : 1720050010210101
     * OperateID : 0000172083
     * OperateName : 李朝阳-172083
     * SubBarCode : 1720050010210101
     * ProductInfoName : 压力延长管
     * RegisterNum : 国食药监械(进)字2011第3661948号(2011-06-08~2015-06-07)
     * SN :
     * Specification : PM6148
     * ProductInfoID : 371044
     * Amount : 0
     * MobileInventorybalanceID : 0
     */

    private String DepartmentCollarID;
    private String  TreasuryDepartment;// ":"200000",
    private String  HRCode;
    private String  EmployeeName;

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public String getDepartmentCollarID() {
        return DepartmentCollarID;
    }

    public void setDepartmentCollarID(String departmentCollarID) {
        DepartmentCollarID = departmentCollarID;
    }

    public String getTreasuryDepartment() {
        return TreasuryDepartment;
    }

    public void setTreasuryDepartment(String treasuryDepartment) {
        TreasuryDepartment = treasuryDepartment;
    }

    public String getHRCode() {
        return HRCode;
    }

    public void setHRCode(String HRCode) {
        this.HRCode = HRCode;
    }

    int Reg_id ;

    public int getReg_id() {
        return Reg_id;
    }

    public void setReg_id(int reg_id) {
        Reg_id = reg_id;
    }
}
