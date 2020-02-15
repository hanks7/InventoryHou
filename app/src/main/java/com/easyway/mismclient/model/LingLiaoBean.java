package com.easyway.mismclient.model;

import com.easyway.mismclient.base.BaseModel;

import java.util.List;

/**
 * @author 侯建军 47466436@qq.com
 * @class com.easyway.mismclient.model.LingLiaoBean
 * @time 2018/5/28 17:12
 * @description 请填写描述
 */
public class LingLiaoBean extends BaseModel {

    /**
     * HRCode : 0000172083
     * EmployeeName : 李朝阳-172083
     * UserDept : [{"HRCode":"0000172083","DeptCode":"000005","DepartmentName":"介入二手术室"},{"HRCode":"0000172083","DeptCode":"200000","DepartmentName":"医学装备部"}]
     */

    private String HRCode;
    private String EmployeeName;
    private List<UserDeptBean> UserDept;

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

    public List<UserDeptBean> getUserDept() {
        return UserDept;
    }

    public void setUserDept(List<UserDeptBean> UserDept) {
        this.UserDept = UserDept;
    }

}
