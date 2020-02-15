package com.easyway.mismclient.model;

import com.easyway.mismclient.base.BaseModel;

/**
 * @author 侯建军 47466436@qq.com
 * @class com.easyway.mismclient.model.UserOFLBean
 * @time 2018/7/24 10:15
 * @description 请填写描述
 *
 *
 *
 *
"DepartmentName": "200000",
"DeptCode": "200000",
"DutyName": "医生",
"EmployeeName": "颜喜峰",
"HRCode": "0000000123",
"HosptialID": 317736,
"HosptialName": "郑州大学第一附属医院",
"Password": "123",
"WorkDeptCode": "NULL",
"isLogin": true,
"Code": 0

 */
public class UserOFLBean extends BaseModel {

    String HRCode;
    String DeptCode;
    String WorkDeptCode;
    String EmployeeName;
    String DutyName;
    String Password;
    int HosptialID;
    String HosptialName;
    String DepartmentName;
    String PinYin;
    String WuBi;
    String Status;
    String IsVoid;
    String HisCode;
    private boolean isLogin;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getHRCode() {
        return HRCode;
    }

    public void setHRCode(String HRCode) {
        this.HRCode = HRCode;
    }

    public String getDeptCode() {
        return DeptCode;
    }

    public void setDeptCode(String deptCode) {
        DeptCode = deptCode;
    }

    public String getWorkDeptCode() {
        return WorkDeptCode;
    }

    public void setWorkDeptCode(String workDeptCode) {
        WorkDeptCode = workDeptCode;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public String getDutyName() {
        return DutyName;
    }

    public void setDutyName(String dutyName) {
        DutyName = dutyName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getHosptialID() {
        return HosptialID;
    }

    public void setHosptialID(int hosptialID) {
        HosptialID = hosptialID;
    }

    public String getHosptialName() {
        return HosptialName;
    }

    public void setHosptialName(String hosptialName) {
        HosptialName = hosptialName;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    public String getPinYin() {
        return PinYin;
    }

    public void setPinYin(String pinYin) {
        PinYin = pinYin;
    }

    public String getWuBi() {
        return WuBi;
    }

    public void setWuBi(String wuBi) {
        WuBi = wuBi;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getIsVoid() {
        return IsVoid;
    }

    public void setIsVoid(String isVoid) {
        IsVoid = isVoid;
    }

    public String getHisCode() {
        return HisCode;
    }

    public void setHisCode(String hisCode) {
        HisCode = hisCode;
    }

    @Override
    public String toString() {
        return "UserOFLBean{" +
                "HRCode='" + HRCode + '\'' +
                ", DeptCode='" + DeptCode + '\'' +
                ", WorkDeptCode='" + WorkDeptCode + '\'' +
                ", EmployeeName='" + EmployeeName + '\'' +
                ", DutyName='" + DutyName + '\'' +
                ", Password='" + Password + '\'' +
                ", HosptialID=" + HosptialID +
                ", HosptialName='" + HosptialName + '\'' +
                ", DepartmentName='" + DepartmentName + '\'' +
                ", PinYin='" + PinYin + '\'' +
                ", WuBi='" + WuBi + '\'' +
                ", Status='" + Status + '\'' +
                ", IsVoid='" + IsVoid + '\'' +
                ", HisCode='" + HisCode + '\'' +
                ", isLogin=" + isLogin +
                '}';
    }
}
