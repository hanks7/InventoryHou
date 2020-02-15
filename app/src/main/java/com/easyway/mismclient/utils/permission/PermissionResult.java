package com.easyway.mismclient.utils.permission;

public interface PermissionResult {
    void onGranted();

    void onDenied();
    void onNext();
}
