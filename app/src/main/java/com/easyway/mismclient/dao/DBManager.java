package com.easyway.mismclient.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.easyway.mismclient.model.MDepartmentBean;
import com.easyway.mismclient.utils.Ulog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * author Bro0cL on 2016/1/26.
 */
public class DBManager {

    private final MySQLiteHelper myHelper;
    private Context mContext;

    private static final String TABLE_NAME = "resource";
    /**
     * DeptCode : 000001
     * DepartmentName : 设备科
     * PinYin : sbk
     */
    private static final String DEPARTMENT_NAME = "DepartmentName";
    private static final String PIN_YIN = "PinYin";
    private static final String DEPT_CODE = "DeptCode";


    public DBManager(Context context, String DB_NAME) {
        this.mContext = context;
        myHelper = new MySQLiteHelper(mContext, DB_NAME, null, 1);
    }

    /**
     * 向数据库中插入和更新数据
     *
     * @param list
     */
    public void insertAndUpdateData(List<MDepartmentBean.DetailsBean> list) {
        //获取数据库对象
        SQLiteDatabase db = myHelper.getWritableDatabase();
        Ulog.i("insertAndUpdateData",list.size());
        //使用execSQL方法向表中插入数据
        db.execSQL("delete  from " + TABLE_NAME);
        for (MDepartmentBean.DetailsBean cBean : list) {
//            update customer set name='AA' where id=1;
            db.execSQL("insert into " + TABLE_NAME + "(" +
                    DEPARTMENT_NAME + "," +
                    DEPT_CODE + "," +
                    PIN_YIN + ") " +
                    "values('" +
                    cBean.getDepartmentName() + "','" +
                    cBean.getDeptCode() + "','" +
                    cBean.getPinYin() + "')");
        }
        db.close();
    }
    /**
     * 从数据库中得到所有城市集合
     *
     * @return
     */
    public ArrayList<MDepartmentBean.DetailsBean> getAllCities() {


        //获取数据库对象
        SQLiteDatabase db = myHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);
        ArrayList<MDepartmentBean.DetailsBean> result = new ArrayList<>();
        MDepartmentBean.DetailsBean cBean;

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(DEPARTMENT_NAME));
            String pinyin = cursor.getString(cursor.getColumnIndex(PIN_YIN));
            String cgs_id = cursor.getString(cursor.getColumnIndex(DEPT_CODE));
            cBean = new MDepartmentBean.DetailsBean(cgs_id, name, pinyin);
            result.add(cBean);
        }
        cursor.close();
        db.close();
        Collections.sort(result, new CityComparator());
        Ulog.i("getAllCities",result.size());

        return result;
    }

    /**
     * 搜索关键词,基于数据的 条件查询, 基于"like 语句"
     *
     * @param keyword
     * @return
     */
    public List<MDepartmentBean.DetailsBean> searchCity(final String keyword) {
        //获取数据库对象
        SQLiteDatabase db = myHelper.getWritableDatabase();
        db.beginTransaction();//开启事务
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + DEPARTMENT_NAME + " like \"%" + keyword
                + "%\" or " + PIN_YIN + " like \"%" + keyword + "%\"", null);
        List<MDepartmentBean.DetailsBean> result = new ArrayList<>();

        MDepartmentBean.DetailsBean cBean;
        while (cursor.moveToNext()) {
            String departmentName = cursor.getString(cursor.getColumnIndex(DEPARTMENT_NAME));
            String pinyin = cursor.getString(cursor.getColumnIndex(PIN_YIN));
            String deptCode = cursor.getString(cursor.getColumnIndex(DEPT_CODE));
            cBean = new MDepartmentBean.DetailsBean(deptCode, departmentName, pinyin);
            result.add(cBean);
        }
        db.endTransaction();//结束事务
        cursor.close();
        db.close();
        Collections.sort(result, new CityComparator());

        return result;
    }

    /**
     * sort by a-z
     */
    private class CityComparator implements Comparator<MDepartmentBean.DetailsBean> {
        @Override
        public int compare(MDepartmentBean.DetailsBean lhs, MDepartmentBean.DetailsBean rhs) {
            String a = lhs.getPinYin().substring(0, 1);
            String b = rhs.getPinYin().substring(0, 1);
            return a.compareTo(b);
        }
    }




    /**
     * 从数据库中查询数据
     *
     * @return
     */
    public String queryData() {
        String result = "";
        //获得数据库对象
        SQLiteDatabase db = myHelper.getReadableDatabase();
        //查询表中的数据
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, "id asc");
        //获取name列的索引
        int nameIndex = cursor.getColumnIndex(DEPARTMENT_NAME);
        //获取level列的索引
        int levelIndex = cursor.getColumnIndex(PIN_YIN);
        for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()) {
            result = result + cursor.getString(nameIndex) + "\t\t";
            result = result + cursor.getInt(levelIndex) + "       \n";
        }
        cursor.close();//关闭结果集
        db.close();//关闭数据库对象
        return result;
    }

    class MySQLiteHelper extends SQLiteOpenHelper {
        /**
         * 调用父类构造器
         *
         * @param context
         * @param name
         * @param factory
         * @param version
         */
        public MySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                              int version) {
            super(context, name, factory, version);
        }

        /**
         * 当数据库首次创建时执行该方法，一般将创建表等初始化操作放在该方法中执行.
         * 重写onCreate方法，调用execSQL方法创建表
         */
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table " + TABLE_NAME + "("
                    + "id integer primary key,"
                    + DEPARTMENT_NAME + " varchar,"
                    + DEPT_CODE + " varchar,"
                    + PIN_YIN + " varchar)");
        }

        /**
         * 当打开数据库时传入的版本号与当前的版本号不同时会调用该方法
         *
         * @param db
         * @param oldVersion
         * @param newVersion
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

    }
}
