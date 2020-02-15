package com.easyway.mismclient.model;

public class UserModuleBean {
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
    public String ModuleId;
    public String ModuleName;
    public String PrivilegeCode;

    public String getModuleID() {
        return ModuleId;
    }

    public void setModuleId(String ModuleId) {
        this.ModuleId = ModuleId;
    }

    public String getModuleName() {
        return ModuleName;
    }

    public void setModuleName(String ModuleName) {
        this.ModuleName = ModuleName;
    }

    public String getPrivilegeCode() {
        return PrivilegeCode;
    }

    public void setPrivilegeCode(String PrivilegeCode) {
        this.PrivilegeCode = PrivilegeCode;
    }
}
