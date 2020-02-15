package com.easyway.mismclient.model;

import static com.easyway.mismclient.utils.UProperTies.getPropString;

/**
 * Created by admin on 2018/4/11.
 */

public class IPPortBean {
    /* serverUrl=172.16.1.60:20941
     HRCode=
     CameraState=0
     HosID=317736*/
    private String ipPort = getPropString("ipPort");
    //    private String ipPort = "172.16.0.81:7001";
    //    private String ipPort = "http://116.247.74.76:8682";
    private String HRCode;
    private String CameraState;
    private String HosID = getPropString("HosID");

    public String getIpPort() {
        return ipPort;
    }

    public void setIpPort(String ipPort) {
        this.ipPort = ipPort;
    }

    public String getHRCode() {
        return HRCode;
    }

    public void setHRCode(String HRCode) {
        this.HRCode = HRCode;
    }

    public String getCameraState() {
        return CameraState;
    }

    public void setCameraState(String cameraState) {
        CameraState = cameraState;
    }

    public String getHosID() {
        return HosID;
    }

    public void setHosID(String hosID) {
        HosID = hosID;
    }
}
