package com.easyway.mismclient.model;

import java.util.List;

/**
 * Created by admin on 2018/4/8.
 */

public class MEmployeeBean {

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
    private String ValidCode;
    private int HosID;
    private List<UserDeptBean> UserDept;
    private List<UserModuleBean> UserModule;

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

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String EmployeeName) {
        this.EmployeeName = EmployeeName;
    }

    public String getValidCode() {
        return ValidCode;
    }

    public void setValidCode(String ValidCode) {
        this.ValidCode = ValidCode;
    }

    public int getHosID() {
        return HosID;
    }

    public void setHosID(int HosID) {
        this.HosID = HosID;
    }

    public List<UserDeptBean> getUserDept() {
        return UserDept;
    }

    public void setUserDept(List<UserDeptBean> UserDept) {
        this.UserDept = UserDept;
    }

    public List<UserModuleBean> getUserModule() {
        return UserModule;
    }

    public void setUserModule(List<UserModuleBean> UserModule) {
        this.UserModule = UserModule;
    }

    public static class UserDeptBean {
        /**
         * HRCode : 0000172083
         * DeptCode : 000005
         * DepartmentName : 介入二手术室
         */

        private String HRCode;
        private String DeptCode;
        private String DepartmentName;

        public String getHRCode() {
            return HRCode;
        }

        public void setHRCode(String HRCode) {
            this.HRCode = HRCode;
        }

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
    }

    public static class UserModuleBean {
        /**
         * ModuleId : 2342
         * ModuleName : PDA盘点
         * ParentId : 22
         * Orders : 2
         * ModuleClass :
         * PrivilegeCode : pandian
         * ModuleType : 9
         * IsRequirePermissions : 0
         * IsNavigationMenu : 0
         * IsUnderline : 0
         * IsVoid : 0
         * ParentModule : null
         * MaxOrder : 0
         * MinOrder : 0
         * Mark : null
         */

        private int ModuleId;
        private String ModuleName;
        private int ParentId;
        private int Orders;
        private String ModuleClass;
        private String PrivilegeCode;
        private String ModuleType;
        private String IsRequirePermissions;
        private String IsNavigationMenu;
        private String IsUnderline;
        private String IsVoid;
        private Object ParentModule;
        private int MaxOrder;
        private int MinOrder;
        private Object Mark;

        public int getModuleId() {
            return ModuleId;
        }

        public void setModuleId(int ModuleId) {
            this.ModuleId = ModuleId;
        }

        public String getModuleName() {
            return ModuleName;
        }

        public void setModuleName(String ModuleName) {
            this.ModuleName = ModuleName;
        }

        public int getParentId() {
            return ParentId;
        }

        public void setParentId(int ParentId) {
            this.ParentId = ParentId;
        }

        public int getOrders() {
            return Orders;
        }

        public void setOrders(int Orders) {
            this.Orders = Orders;
        }

        public String getModuleClass() {
            return ModuleClass;
        }

        public void setModuleClass(String ModuleClass) {
            this.ModuleClass = ModuleClass;
        }

        public String getPrivilegeCode() {
            return PrivilegeCode;
        }

        public void setPrivilegeCode(String PrivilegeCode) {
            this.PrivilegeCode = PrivilegeCode;
        }

        public String getModuleType() {
            return ModuleType;
        }

        public void setModuleType(String ModuleType) {
            this.ModuleType = ModuleType;
        }

        public String getIsRequirePermissions() {
            return IsRequirePermissions;
        }

        public void setIsRequirePermissions(String IsRequirePermissions) {
            this.IsRequirePermissions = IsRequirePermissions;
        }

        public String getIsNavigationMenu() {
            return IsNavigationMenu;
        }

        public void setIsNavigationMenu(String IsNavigationMenu) {
            this.IsNavigationMenu = IsNavigationMenu;
        }

        public String getIsUnderline() {
            return IsUnderline;
        }

        public void setIsUnderline(String IsUnderline) {
            this.IsUnderline = IsUnderline;
        }

        public String getIsVoid() {
            return IsVoid;
        }

        public void setIsVoid(String IsVoid) {
            this.IsVoid = IsVoid;
        }

        public Object getParentModule() {
            return ParentModule;
        }

        public void setParentModule(Object ParentModule) {
            this.ParentModule = ParentModule;
        }

        public int getMaxOrder() {
            return MaxOrder;
        }

        public void setMaxOrder(int MaxOrder) {
            this.MaxOrder = MaxOrder;
        }

        public int getMinOrder() {
            return MinOrder;
        }

        public void setMinOrder(int MinOrder) {
            this.MinOrder = MinOrder;
        }

        public Object getMark() {
            return Mark;
        }

        public void setMark(Object Mark) {
            this.Mark = Mark;
        }
    }
}
