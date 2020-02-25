package com.easyway.mismclient.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/4/24.
 */

public class UploadImageBean {


    private String path;
    private String text;

    public UploadImageBean(String path, String text) {
        this.path = path;
        this.text = text;
    }

    public UploadImageBean(String text) {
        this.text = text;
    }

    public UploadImageBean() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public static List<TestBean> setDataList() {
        List<TestBean> list;
        list = new ArrayList<>();

        TestBean bean1 = new TestBean();
        bean1.setText("正     面");
        TestBean bean2 = new TestBean();
        bean2.setText("侧     面");
        TestBean bean3 = new TestBean();
        bean3.setText("条形码");
        TestBean bean4 = new TestBean();
        bean4.setText("条形码");
        TestBean bean5 = new TestBean();
        bean5.setText("条形码");

        list.add(bean1);
        list.add(bean2);
        list.add(bean3);
        list.add(bean4);
        list.add(bean5);

        return list;
    }
}
