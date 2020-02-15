package com.easyway.mismclient.dao;

/**
 * @author 侯建军 47466436@qq.com
 * @class com.easyway.mismclient.dao.DbHelper
 * @time 2018/7/23 15:54
 * @description 请填写描述
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.easyway.BarcodeObject;
import com.easyway.mismclient.model.DetailListBean;
import com.easyway.mismclient.model.LicenceListBean;
import com.easyway.mismclient.model.OfflineInverntoryBean;
import com.easyway.mismclient.model.UserDeptBean;
import com.easyway.mismclient.model.UserOFLBean;
import com.easyway.mismclient.utils.UToast;
import com.easyway.mismclient.utils.UTools;
import com.easyway.mismclient.utils.Ugson;
import com.easyway.mismclient.utils.Ulog;
import com.easyway.mismclient.utils.UtilDate;
import com.easyway.mismclient.utils.http.ImpDBSuccessFailListener;
import com.easyway.mismclient.utils.http.MyAsyncTask;

import java.util.ArrayList;
import java.util.List;

import static com.easyway.BarcodeType.Primary;
import static com.easyway.mismclient.dao.DBContants.TB_BARCODE_INFO;
import static com.easyway.mismclient.dao.DBContants.TB_DEPARTMENT;
import static com.easyway.mismclient.dao.DBContants.TB_EMPLOYEE;
import static com.easyway.mismclient.dao.DBContants.TB_ENTERPRISE_INFO;
import static com.easyway.mismclient.dao.DBContants.TB_INVENTORY_DETAIL;
import static com.easyway.mismclient.dao.DBContants.TB_PRODUCT_INFO;
import static com.easyway.mismclient.dao.DBContants.create_tb_barcodeinfo;
import static com.easyway.mismclient.dao.DBContants.create_tb_department;
import static com.easyway.mismclient.dao.DBContants.create_tb_employee;
import static com.easyway.mismclient.dao.DBContants.create_tb_enterpriseInfo;
import static com.easyway.mismclient.dao.DBContants.create_tb_inventory;
import static com.easyway.mismclient.dao.DBContants.create_tb_productinfo;
import static com.easyway.mismclient.dao.DBContants.create_tb_specunit;
import static com.easyway.mismclient.dao.DBContants.create_tb_tb_inventorydetail;


/**
 * 项目名:    DownLoaderManger
 * 包名       com.azhong.downloader.db
 * 文件名:    DbHelper
 * 创建者:    侯建军
 * 创建时间:  2018/2/14 on 15:20
 * 描述:
 */
public class DbHelper extends SQLiteOpenHelper {

    Context context;

    public DbHelper(Context context) {
        super(context, UTools.getMySqliteName(context, "DbHelper.db"), null, 5);
        this.context = context;
    }

    /**
     * eg:DbHelper DbHelper = new DbHelper(getApplicationContext(),"FAIDB.db",null,1);//创建一个空的数据库数据库
     * com.easyway.mismclient/DbHelper.db
     *
     * @param context
     * @param name
     * @param factory 可以设置为空
     * @param version 升级时的版本号
     */
    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, UTools.getMySqliteName(context, name), factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        createDB(db);//创建数据库
    }

    /**
     * 删除所有表
     *
     * @param db
     */
    void dropDb(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type ='table' AND name != 'sqlite_sequence'", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Ulog.i("dropDb", "删除表 = " + cursor.getString(0));

                db.execSQL("DROP TABLE " + cursor.getString(0));

            }
        }
        if (cursor != null) {
            cursor.close();
            cursor = null;
        }
    }

    private void createDB(SQLiteDatabase db) {
        db.execSQL(create_tb_employee);//登录
        db.execSQL(create_tb_department);//存放科室
        db.execSQL(create_tb_barcodeinfo);//条形码信息表
        db.execSQL(create_tb_productinfo);//产品基本信息表
        db.execSQL(create_tb_enterpriseInfo);//供应商
        db.execSQL(create_tb_tb_inventorydetail);//产品库存明细表
        db.execSQL(create_tb_inventory);//验收开始结束的那张表
        db.execSQL(create_tb_specunit);//规格信息表
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        UToast.showText("数据库更新");
        Ulog.i("onUpgrade-oldVersion", oldVersion);
        Ulog.i("onUpgrade-newVersion", newVersion);
        if (oldVersion < newVersion) {
            dropDb(db);//删除所有表
            createDB(db);//创建数据库
        }

    }

    /**
     * 查询已经存在的一条信息
     */
    public void queryEmployee(final String HRCode, final String passWord, final ImpDBSuccessFailListener listener) {
        new MyAsyncTask<UserOFLBean>(context, listener) {
            UserOFLBean userOFLBean;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                userOFLBean = new UserOFLBean();
            }

            @Override
            protected UserOFLBean doInBackground(Void... params) {
                Cursor cursor = getReadableDatabase().query(TB_EMPLOYEE, null, "HRCode = ?", new String[]{HRCode}, null, null, null, null);
                List<UserOFLBean> list = new ArrayList<>();
                Ulog.i("cursor.getCount()", cursor.getCount());
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        UserOFLBean bean = new UserOFLBean();
                        String HRCode2 = cursor.getString(cursor.getColumnIndex("HRCode"));
                        String DeptCode = cursor.getString(cursor.getColumnIndex("DeptCode"));
                        String WorkDeptCode = cursor.getString(cursor.getColumnIndex("WorkDeptCode"));
                        String EmployeeName = cursor.getString(cursor.getColumnIndex("EmployeeName"));
                        String DutyName = cursor.getString(cursor.getColumnIndex("DutyName"));
                        String Password = cursor.getString(cursor.getColumnIndex("Password"));
                        int HosptialID = cursor.getInt(cursor.getColumnIndex("HosptialID"));
                        String HosptialName = cursor.getString(cursor.getColumnIndex("HosptialName"));
                        String DepartmentName = cursor.getString(cursor.getColumnIndex("DepartmentName"));

                        bean.setHRCode(HRCode2);
                        bean.setDeptCode(DeptCode);
                        bean.setDutyName(DutyName);
                        bean.setEmployeeName(EmployeeName);
                        bean.setPassword(Password);
                        bean.setWorkDeptCode(WorkDeptCode);

                        bean.setHosptialID(HosptialID);
                        bean.setHosptialName(HosptialName);
                        bean.setDepartmentName(DepartmentName);
                        list.add(bean);
                    }
                    cursor.close();
                    getReadableDatabase().close();
                    if (list.size() == 0) {

                        userOFLBean.setMessage("账号不存在,请更新数据");
                        return userOFLBean;
                    }
                    if (passWord.equals(list.get(0).getPassword())) {
                        list.get(0).setLogin(true);
                        updateEmployee(list.get(0));
                        return (list.get(0));
                    } else {
                        userOFLBean.setMessage("密码错误");
                        return userOFLBean;
                    }
                } else {
                    userOFLBean.setMessage("cursor 为空,请联系管理员");
                    return userOFLBean;
                }
            }


        }.execute();
    }


    /**
     * 查询已经存在的一条信息
     */
    public UserOFLBean queryEmployee() {
        Cursor cursor = getReadableDatabase().query(TB_EMPLOYEE, null, "isLogin = ?", new String[]{"1"}, null, null, null, null);
        UserOFLBean bean = new UserOFLBean();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String HRCode2 = cursor.getString(cursor.getColumnIndex("HRCode"));
                String DeptCode = cursor.getString(cursor.getColumnIndex("DeptCode"));
                String WorkDeptCode = cursor.getString(cursor.getColumnIndex("WorkDeptCode"));
                String EmployeeName = cursor.getString(cursor.getColumnIndex("EmployeeName"));
                String DutyName = cursor.getString(cursor.getColumnIndex("DutyName"));
                String Password = cursor.getString(cursor.getColumnIndex("Password"));

                int HosptialID = cursor.getInt(cursor.getColumnIndex("HosptialID"));
                String HosptialName = cursor.getString(cursor.getColumnIndex("HosptialName"));
                String DepartmentName = cursor.getString(cursor.getColumnIndex("DepartmentName"));
                int isLogin = cursor.getInt(cursor.getColumnIndex("isLogin"));

                bean.setHRCode(HRCode2);
                bean.setDeptCode(DeptCode);
                bean.setDutyName(DutyName);
                bean.setEmployeeName(EmployeeName);
                bean.setPassword(Password);
                bean.setWorkDeptCode(WorkDeptCode);

                bean.setHosptialID(HosptialID);
                bean.setHosptialName(HosptialName);
                bean.setDepartmentName(DepartmentName);
                bean.setLogin(isLogin == 1);

            }
            cursor.close();
            getReadableDatabase().close();

        }
        return bean;
    }


    /**
     * 更改订单详情
     */
    public void updateEmployee(UserOFLBean bean) {

        SQLiteDatabase db = getWritableDatabase();
        // 开启事务
        db.beginTransaction();
        ContentValues values = new ContentValues();

        values.put("islogin", bean.isLogin() ? 1 : 0);
        db.update(TB_EMPLOYEE, values, "HRCode = ?", new String[]{bean.getHRCode() + ""});

        //设置事务标志为成功，当结束事务时就会提交事务
        db.setTransactionSuccessful();
        // 结束事务
        db.endTransaction();

        db.close();
    }


    /**
     * 通过DeptCode查询科室
     */
    public String queryDataUserDept(String strDeptCode) {
        if (strDeptCode == null) {
            return null;
        }
        Cursor cursor = getReadableDatabase().rawQuery("select * from " + TB_DEPARTMENT + " where DeptCode=? ",
                new String[]{strDeptCode});
        UserDeptBean bean = new UserDeptBean();
        if (cursor != null) {
            while (cursor.moveToNext()) {


                String DeptCode = cursor.getString(cursor.getColumnIndex("DeptCode"));
                String DepartmentName = cursor.getString(cursor.getColumnIndex("DepartmentName"));

                bean.setDeptCode(DeptCode);
                bean.setDepartmentName(DepartmentName);

            }
            cursor.close();
            getReadableDatabase().close();
        }
        return bean.getDepartmentName();
    }

    /**
     * 根据条码查询产品基础信息
     * SELECT ProductInfoID , Name, Model from TB_ProductInfo where ProductInfoID like '%378267%'
     */
    public OfflineInverntoryBean queryProductInfo(String barCode) {
        if (barCode == null) {
            return null;
        }
        String strSql = "select DISTINCT b.Name as specUnitName,d.EnterpriseName as EnterpriseName  ,a.*    " +
                "from TB_ProductInfo a  " +
                "join TB_SpecUnit b on a.Unit=b.SpecID  " +
                "join TB_BarcodeInfo c on a.ProductInfoID=c.ProductInfoID   " +
                "join TB_EnterpriseInfo d on d.EnterpriseID=a.EnterpriseID " +
                "where  Barcode=? ";
        Ulog.i("queryProductInfo", strSql);
        Ulog.i("barCode", barCode);
        Cursor cursor = getReadableDatabase().rawQuery(strSql, new String[]{barCode});

        OfflineInverntoryBean mInventoryBean = new OfflineInverntoryBean();
        if (cursor != null) {
            while (cursor.moveToNext()) {

                int ProductInfoID = cursor.getInt(cursor.getColumnIndex("ProductInfoID"));
                int isChecked = cursor.getInt(cursor.getColumnIndex("isChecked"));
                String Name = cursor.getString(cursor.getColumnIndex("Name"));
                String Model = cursor.getString(cursor.getColumnIndex("Model"));
                String Unit = cursor.getString(cursor.getColumnIndex("specUnitName"));
                String EnterpriseName = cursor.getString(cursor.getColumnIndex("EnterpriseName"));


                mInventoryBean.setProductInfoID(ProductInfoID);
                mInventoryBean.setProductInfoName(Name);
                mInventoryBean.setEnterpriseName(Model);
                mInventoryBean.setSpecification(Unit);
                mInventoryBean.setEnterpriseName(EnterpriseName);
                mInventoryBean.setIsChecked(isChecked);

            }
            cursor.close();
            getReadableDatabase().close();
        }
        Ulog.i(Ugson.toJson(mInventoryBean));
        return mInventoryBean;
    }


    /**
     * 查询存放科室
     * SELECT ProductInfoID , Name, Model from TB_ProductInfo where ProductInfoID like '%378267%'
     * <p>
     * SELECT decode,dename from (select p.DeptCode as decode,
     * d.DepartmentName as dename
     * from  TP_ProductInventoryDetail p ,TB_Department d
     * where p.DeptCode=d.DeptCode  and p.ProductInfoID like '378267') group by decode;
     */
    public ArrayList<UserDeptBean> queryDepartments() {
        ArrayList<UserDeptBean> list = new ArrayList<>();


        Cursor cursor = getReadableDatabase().rawQuery("select d.DepartmentName as dename,d.DeptCode as decode " +
                "from  TB_Department d ,TB_Inventory i where d.DeptCode=i.DeptCode " +
                "group by d.DeptCode", new String[]{});


        if (cursor != null) {
            while (cursor.moveToNext()) {
                UserDeptBean bean = new UserDeptBean();

                String DepartmentName = cursor.getString(cursor.getColumnIndex("dename"));
                String DeptCode = cursor.getString(cursor.getColumnIndex("decode"));

                bean.setDepartmentName(DepartmentName);
                bean.setDeptCode(DeptCode);

                list.add(bean);

            }
            cursor.close();
            getReadableDatabase().close();
        }
        return list;
    }


    /**
     * 查询注册证
     */
    public ArrayList<LicenceListBean> queryRegistrationCard(String barCode, BarcodeObject barcodeObject) {

        ArrayList<LicenceListBean> list = new ArrayList<>();

        if (barcodeObject.getBarcodeType() == Primary) {
            return list;
        }
        if (barCode == null) {
            return list;
        }
        Cursor cursor = getReadableDatabase().rawQuery("select RegistrationCard from " + TB_INVENTORY_DETAIL + " " +
                        "  where ProductInfoID like " +
                        "  (select ProductInfoID from " + TB_BARCODE_INFO + " where Barcode=?)   " +
                        "   and ProductBatch = ?  " +
                        "   and ExpireDate like  ?    " +
                        "   group by RegistrationCard",
                new String[]{barCode, barcodeObject.getLot(), getDate(barcodeObject.getExpirationDate())});

        Ulog.i("", "select RegistrationCard from " + TB_INVENTORY_DETAIL + " " +
                "  where ProductInfoID like " +
                "  (select ProductInfoID from " + TB_BARCODE_INFO + " where Barcode=?)   " +
                "   and ProductBatch = ?  " +
                "   and ExpireDate like  ?    " +
                "   group by RegistrationCard");
        Ulog.i("barCode", barCode);
        Ulog.i("barcodeObject.getLot()", barcodeObject.getLot());
        Ulog.i("barcodeObject.getExpirationDate()", getDate(barcodeObject.getExpirationDate()));


        if (cursor != null) {
            while (cursor.moveToNext()) {

                LicenceListBean bean = new LicenceListBean();

                String RegistrationCard = cursor.getString(cursor.getColumnIndex("RegistrationCard"));

                bean.setRegistrationCard(RegistrationCard);

                list.add(bean);

            }
            cursor.close();
            getReadableDatabase().close();
        }
        return list;
    }

    /**
     * 得到数据的时间,如果为空则返回1900/1/1 0:00:00
     *
     * @return
     */
    @NonNull
    private String getDate(String date) {
        return null == date ? "1900/1/1 0:00:00" : "%" + UtilDate.getFormDate(date) + "%";
    }

    /**
     * 查询产品详情
     *
     * @param barCode          主码
     * @param barcodeObject
     * @param DeptCode         科室号
     * @param RegistrationCard 注册证号
     * @return SupplierName : 上海任远商贸有限公司
     * barCode: 0108935221210810
     * ProductBatch: 170616V
     * DeptCode: 210000
     * RegistrationCard: 国械注进20153772566(2015-08-25~2020-08-24)
     * ExpireDate: 2019-05-31
     */
    public ArrayList<DetailListBean> queryProductInfoList(String barCode, BarcodeObject barcodeObject, String DeptCode, String RegistrationCard) {


        ArrayList<DetailListBean> list = new ArrayList<>();

        if (barcodeObject.getBarcodeType() == Primary) {
            return list;
        }

        Cursor cursor = getReadableDatabase().rawQuery(
                "  select ei.EnterpriseName, * " +
                        "  from " + TB_INVENTORY_DETAIL + " pd," + TB_ENTERPRISE_INFO + " ei   " +
                        "  where ei.EnterpriseID = pd.SupplierID " +
                        "  and ProductInfoID = (select ProductInfoID from " + TB_BARCODE_INFO + " where Barcode= ? )  " +
                        "  and ProductBatch = ?  " +
                        "  and ExpireDate like  ?   " +
//                        "  and DeptCode = ?  " +
                        "  and RegistrationCard = ?  " +
                        "  and SerialNumber = ?  " +
                        "  and ProductionDate like ?  " +
                        " ;",
                new String[]{
                        barCode,
                        barcodeObject.getLot(),
                        getDate(barcodeObject.getExpirationDate()),
//                        DeptCode,
                        RegistrationCard + "",
                        barcodeObject.getSN() != null ? barcodeObject.getSN() : "",
                        getDate(barcodeObject.getMakeDate()),
                });

        Ulog.i("  select ei.EnterpriseName, * " +
                "  from " + TB_INVENTORY_DETAIL + " pd," + TB_ENTERPRISE_INFO + " ei   " +
                "  where ei.EnterpriseID = pd.SupplierID " +
                "  and ProductInfoID = (select ProductInfoID from " + TB_BARCODE_INFO + " where Barcode= ? )  " +
                "  and ProductBatch = ?  " +
                "  and ExpireDate like  ?   " +
//                        "  and DeptCode = ?  " +
                "  and RegistrationCard = ?  " +
                "  and SerialNumber = ?  " +
                "  and ProductionDate like ?  " +
                " ;");
//2016/6/1  2016-06-01

        Ulog.i("barCode", barCode);
        Ulog.i(" barcodeObject.getLot()", barcodeObject.getLot());
        Ulog.i("barcodeObject.getExpirationDate()", getDate(barcodeObject.getExpirationDate()));
        Ulog.i("RegistrationCard", RegistrationCard);
        Ulog.i("getSN()", null == barcodeObject.getSN() ? "" : barcodeObject.getSN());
        Ulog.i("getMakeDate", getDate(barcodeObject.getMakeDate()));

        if (cursor != null) {
            while (cursor.moveToNext()) {

                DetailListBean bean = new DetailListBean();

                int SupplierID = cursor.getInt(cursor.getColumnIndex("SupplierID"));
                String EnterpriseName = cursor.getString(cursor.getColumnIndex("EnterpriseName"));
                int RealTimeAmount = cursor.getInt(cursor.getColumnIndex("RealTimeAmount"));
                int FactAmount = cursor.getInt(cursor.getColumnIndex("FactAmount"));
                int DetailID = cursor.getInt(cursor.getColumnIndex("DetailID"));

                bean.setSupplierName(EnterpriseName);
                bean.setSupplierID(SupplierID);
                bean.setFactAmount(FactAmount);
                bean.setRealTimeAmount(RealTimeAmount);
                bean.setDetailID(DetailID);

                list.add(bean);

            }
            cursor.close();
            getReadableDatabase().close();
        }
        return list;
    }

    /**
     * 得到返回生产日期,因为三目运算实现有问题,所以在这里费点事
     *
     * @param barcodeObject
     */
    private String getMakeDate(BarcodeObject barcodeObject) {
        if (barcodeObject == null) {
            return "";
        }
        String mDate = barcodeObject.getMakeDate();

        boolean tb = mDate == null;
        if (tb) {
            return "";
        } else {
            return barcodeObject.getMakeDate();
        }
    }

    /**
     * 更改订单详情
     */
    public void updateDetailListBean(List<DetailListBean> list, int ProductInfoID, ImpDBSuccessFailListener<String> listener) {

        if (null == list || list.size() <= 0) {
            listener.onFail("没有订单");
        }

        SQLiteDatabase db = getWritableDatabase();
        // 开启事务
        db.beginTransaction();
        try {
            ContentValues values2 = new ContentValues();
            values2.put("isChecked", 1);
            db.update(TB_PRODUCT_INFO, values2, "ProductInfoID = ?", new String[]{ProductInfoID + ""});

            for (DetailListBean bean : list) {
                ContentValues values = new ContentValues();

                values.put("FactAmount", bean.getFactAmount());
                db.update(TB_INVENTORY_DETAIL, values, "DetailID = ?", new String[]{bean.getDetailID() + ""});
            }
        } catch (Exception e) {
            e.printStackTrace();
            listener.onFail(e.getMessage());
        }
        //设置事务标志为成功，当结束事务时就会提交事务
        db.setTransactionSuccessful();
        // 结束事务
        db.endTransaction();

        db.close();
        listener.onSuccess("提交成功");
    }


    /**
     * 查询盘点结束时间
     */
    public boolean queryInventoryStatus() {

        String msg = "";

        Cursor cursor = getReadableDatabase().rawQuery("select Status from TB_Inventory",
                new String[]{});
        if (cursor != null) {
            while (cursor.moveToNext()) {
                msg = cursor.getString(cursor.getColumnIndex("Status"));
            }
            cursor.close();
            getReadableDatabase().close();
        }
        Ulog.i("queryInventoryStatus", msg);
        return msg == "11";
    }

    /**
     * 查询盘点开始时间
     */
    public String queryInventoryBeginDate() {

        String msg = "";

        Cursor cursor = getReadableDatabase().rawQuery("select * from TB_Inventory",
                new String[]{});

        if (cursor != null) {
            while (cursor.moveToNext()) {
                msg = cursor.getString(cursor.getColumnIndex("BeginDate"));
            }
            cursor.close();
            getReadableDatabase().close();
        }
        return msg;
    }

    /**
     * 查询盘点开始时间
     */
    public String queryInventoryInventoryCode() {

        String msg = "";

        Cursor cursor = getReadableDatabase().rawQuery("select * from TB_Inventory",
                new String[]{});
        if (cursor != null) {
            while (cursor.moveToNext()) {
                msg = cursor.getString(cursor.getColumnIndex("InventoryCode"));
            }
            cursor.close();
            getReadableDatabase().close();
        }
        return msg;
    }


}

