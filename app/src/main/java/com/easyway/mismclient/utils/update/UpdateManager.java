package com.easyway.mismclient.utils.update;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.easyway.mismclient.R;
import com.easyway.mismclient.model.UpdateBean;
import com.easyway.mismclient.utils.UToast;
import com.easyway.mismclient.utils.Ulog;
import com.easyway.mismclient.utils.UtilSystem;
import com.easyway.mismclient.utils.permission.PermissionReq;
import com.xiaochen.progressroundbutton.AnimButtonLayout;
import com.xiaochen.progressroundbutton.AnimDownloadProgressButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author coolszy
 * @version 现在只显示通知，不显示dialog
 * @date 2012-4-26
 * @blog http://blog.92coding.com
 */

public class UpdateManager {


    private Context mContext;
    String mName;
    String mUrl;
    int mVersion;
    boolean isInstall;

    public UpdateManager(Context context, UpdateBean bean) {
        this.mContext = context;
//        mHashMap = new HashMap<>();
//        mHashMap.put("url", "http://gdown.baidu.com/data/wisegame/f98d235e39e29031/baiduxinwen.apk");
//        mHashMap.put("version", "1");

        mUrl = bean.getUrl();
        mName = mUrl.substring(mUrl.lastIndexOf("/") + 1);
        mVersion = bean.getVersion();

        Ulog.i("mUrl", mUrl);
        Ulog.i("mName", mName);
        Ulog.i("mVersion", mVersion);

    }


    /* 下载保存路径 */
    private String mSavePath;
    /* 记录进度条数量 */
    private int progress;
    /* 更新进度条 */
    private AnimButtonLayout mAnimButtonLayout;
    private Dialog mDownloadDialog;


    /* 下载中 */
    private static final int DOWNLOAD = 1;
    /* 下载结束 */
    private static final int DOWNLOAD_FINISH = 2;
    private static final int ERROR = 3;


    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                // 正在下载
                case DOWNLOAD:
                    // 设置进度条位置
                    mAnimButtonLayout.setState(AnimDownloadProgressButton.DOWNLOADING);
                    mAnimButtonLayout.setProgress(progress);
                    mAnimButtonLayout.setProgressText("下载中", mAnimButtonLayout.getProgress());
                    if (mAnimButtonLayout.getProgress() + 1 >= 100) {
                        mAnimButtonLayout.setState(AnimDownloadProgressButton.INSTALLING);
                        mAnimButtonLayout.setCurrentText("安装中");
                    }


                    break;
                case DOWNLOAD_FINISH:
                    // 安装文件
                    installApk();
                    break;
                case ERROR:
                    UToast.showText(String.valueOf(msg.obj));
                    break;
                default:
                    break;
            }
        }

        ;
    };


    /**
     * 显示软件下载对话框
     */
    public boolean showDownloadDialog(boolean isShowToast) {
        if (checkUpdate(isShowToast)) return false;

        LayoutInflater inflaterDl = LayoutInflater.from(mContext);
        View view = inflaterDl.inflate(R.layout.softupdate_progress, null);
        mDownloadDialog = new AlertDialog.Builder(mContext, R.style.CustomDialogII).create();
        mDownloadDialog.setCancelable(false);
        mDownloadDialog.show();
        mDownloadDialog.getWindow().setContentView(view);
        mDownloadDialog.setCanceledOnTouchOutside(false);
        mAnimButtonLayout = (AnimButtonLayout) view.findViewById(R.id.anim_btn3);
        mAnimButtonLayout.setCurrentText("安装");
        mAnimButtonLayout.setTextSize(30f);
        mAnimButtonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!PermissionReq.judgePermisson(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    return;
                }
                downloadApk();//下载文件
            }
        });

        mDownloadDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        return true;

    }

    /**
     * 检查版本升级
     *
     * @return
     */
    private boolean checkUpdate(boolean isShowToast) {
        if (mVersion <= UtilSystem.getVersionCode()) {
            if (isShowToast) {
                UToast.showText("已经是最新版本");
            }
            return true;
        }
        return false;
    }

    private downloadApkThread mDownloadApkThread;

    /**
     * 下载apk文件
     */
    private void downloadApk() {
        // 启动新线程下载软件

        //启动下载线程
        if (mDownloadApkThread == null) {
            mDownloadApkThread = new downloadApkThread();
            mDownloadApkThread.start();
        }
    }

    /**
     * 下载文件线程
     *
     * @author coolszy
     * @date 2012-4-26
     * @blog http://blog.92coding.com
     */
    private class downloadApkThread extends Thread {
        @Override
        public void run() {
            try {
                // 判断SD卡是否存在，并且是否具有读写权限
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    // 获得存储卡的路径
                    String sdpath = Environment.getExternalStorageDirectory() + "/";
                    mSavePath = sdpath + "download";
                    Ulog.i("mSavePath", mSavePath);

                    URL url = new URL(mUrl);
                    // 创建连接
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();
                    // 获取文件大小
                    int length = conn.getContentLength();
                    if (conn.getResponseCode() == 404) {
                        Message message = mHandler.obtainMessage();
                        message.what = ERROR;
                        message.obj = conn.getResponseCode() + "文件未找到";
                        mHandler.sendMessage(message);
                    }
                    // 创建输入流
                    InputStream is = conn.getInputStream();

                    File file = new File(mSavePath);
                    // 判断文件目录是否存在
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    File apkFile = new File(mSavePath, mName);
                    FileOutputStream fos = new FileOutputStream(apkFile);
                    int count = 0;
                    // 缓存
                    byte buf[] = new byte[1024];
                    // 写入到文件中
                    do {
                        int numread = is.read(buf);
                        count += numread;
                        // 计算进度条位置
                        progress = (int) (((float) count / length) * 100);
                        // 更新进度
                        mHandler.sendEmptyMessage(DOWNLOAD);
                        if (numread <= 0) {
                            // 下载完成
                            mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
                            isInstall = true;
                            break;
                        }
                        // 写入文件
                        fos.write(buf, 0, numread);
                    } while (!isInstall);// 点击取消就停止下载.
                    fos.close();
                    is.close();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 取消下载对话框显示
            mDownloadDialog.dismiss();
        }
    }


    /**
     * 安装APK文件
     */
    public boolean installApk() {
        if (!isInstall) return false;
        File apkfile = new File(mSavePath, mName);
        if (!apkfile.exists()) {
            return false;
        }
        // 通过Intent安装APK文件
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(apkfile), "application/vnd.android.package-archive");
        mContext.startActivity(intent);//下载完后 启动应用。
        return true;
    }

    public boolean getIsinstall() {
        return this.isInstall;
    }
}
