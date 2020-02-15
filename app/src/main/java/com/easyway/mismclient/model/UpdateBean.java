package com.easyway.mismclient.model;

import com.easyway.mismclient.base.BaseConstants;
import com.easyway.mismclient.base.BaseModel;

/**
 * Created by admin on 2018/4/13.
 */

public class UpdateBean extends BaseModel{

    /**
     * SoftID : 00000000000001
     * SoftwareName : MIS物资管理系统Android版
     * Version : -1
     * DisplayVersion : 0.8.0
     */
    private String SoftID;
    private String SoftwareName;
    private int Version;
    private String DisplayVersion;
    private String url;

    public String getUrl() {
        return BaseConstants.FORMAL_URL+"/MISAppendixDir/MISMClient.apk";
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSoftID() {
        return SoftID;
    }

    public void setSoftID(String SoftID) {
        this.SoftID = SoftID;
    }

    public String getSoftwareName() {
        return SoftwareName;
    }

    public void setSoftwareName(String SoftwareName) {
        this.SoftwareName = SoftwareName;
    }

    public int getVersion() {
        return Version;
    }

    public void setVersion(int Version) {
        this.Version = Version;
    }

    public String getDisplayVersion() {
        return DisplayVersion;
    }

    public void setDisplayVersion(String DisplayVersion) {
        this.DisplayVersion = DisplayVersion;
    }
}
