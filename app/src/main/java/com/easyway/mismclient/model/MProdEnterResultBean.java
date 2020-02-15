package com.easyway.mismclient.model;

import com.easyway.mismclient.base.BaseModel;

/**
 * Created by admin on 2018/4/25.
 */

public class MProdEnterResultBean extends BaseModel {

    /**
     * Reg_id : 3720
     * Code : 0
     * Message :
     */

    private int Reg_id;
    private String DepartmentCollarID ;

    public int getReg_id() {
        return Reg_id;
    }

    public void setReg_id(int Reg_id) {
        this.Reg_id = Reg_id;
    }

    public String getDepartmentCollarID() {
        return DepartmentCollarID;
    }

    public void setDepartmentCollarID(String departmentCollarID) {
        DepartmentCollarID = departmentCollarID;
    }
}
