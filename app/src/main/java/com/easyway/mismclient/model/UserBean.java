package com.easyway.mismclient.model;

import com.easyway.mismclient.base.BaseModel;

import java.util.List;

@SuppressWarnings("serial")
public class UserBean extends BaseModel {
    /**
     * HRCode : 0000172083
     * EmployeeName : 李朝阳-172083
     * ValidCode : b895ece8-e0b8-46e4-95e1-3b8e860b7cbe,0000172083
     * HosID : 317736
     * UserDept : [{"HRCode":"0000172083","DeptCode":"000005","DepartmentName":"介入二手术室"},{"HRCode":"0000172083","DeptCode":"200000","DepartmentName":"医学装备部"}]
     * UserModule : [
     * {"ModuleId":2342,"ModuleName":"PDA盘点","ParentId":22,"Orders":2,"ModuleClass":"","PrivilegeCode":"pandian","ModuleType":"9","IsRequirePermissions":"0","IsNavigationMenu":"0","IsUnderline":"0","IsVoid":"0","ParentModule":null,"MaxOrder":0,"MinOrder":0,"Mark":null},
     * {"ModuleId":2343,"ModuleName":"PDA期初","ParentId":22,"Orders":3,"ModuleClass":"","PrivilegeCode":"MoblileInventoryBalance","ModuleType":"9","IsRequirePermissions":"0","IsNavigationMenu":"0","IsUnderline":"0","IsVoid":"0","ParentModule":null,"MaxOrder":0,"MinOrder":0,"Mark":null}]
     */
    private String HRCode;
    private String EmployeeName;
    private String Password;
    private int Result;
    private int HospitalID;
    private String ValidCode;

    private boolean isLogin;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    private List<UserDeptBean> UserDept;
    private List<UserModuleBean> UserModule;

    public UserBean() {
        super();

    }

    public UserBean(String HRCode) {
        this.HRCode = HRCode;
    }

    public UserBean(String HRCode, String Password) {
        this.HRCode = HRCode;
        this.Password = Password;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getHRCode() {
        return HRCode;
    }

    public void setHRCode(String HRCode) {
        this.HRCode = HRCode;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String EmployeeName) {
        this.EmployeeName = EmployeeName;
    }

    public void setValidCode(String ValidCode) {
        this.ValidCode = ValidCode;
    }

    public String GetValidCode() {
        return ValidCode;
    }

    public void setResult(int Result) {
        this.Result = Result;
    }

    public List<UserDeptBean> GetUserDepartment() {
        return UserDept;
    }

    public void SetDetails(List<UserDeptBean> UserDept) {
        this.UserDept = UserDept;
    }

    public List<UserModuleBean> GetModule() {
        return UserModule;
    }

    public void SetModule(List<UserModuleBean> Module) {
        this.UserModule = Module;
    }

    public int getResult() {
        return Result;
    }


    public void setHospitalID(int HospitalID) {
        this.HospitalID = HospitalID;
    }

    public int getHospitalID() {
        return HospitalID;
    }
}
