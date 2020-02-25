package com.easyway.mismclient.utils.http;


import com.easyway.mismclient.base.BaseModel;
import com.easyway.mismclient.model.LingLiaoBean;
import com.easyway.mismclient.model.MDepartmentBean;
import com.easyway.mismclient.model.MInventoryBean;
import com.easyway.mismclient.model.MProdEnterBean;
import com.easyway.mismclient.model.MProdEnterDetailBean;
import com.easyway.mismclient.model.MProdEnterResultBean;
import com.easyway.mismclient.model.MWareHouseBean;
import com.easyway.mismclient.model.UpdateBean;
import com.easyway.mismclient.model.UserBean;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * @author 侯建军 47466436@qq.com
 * @date 2018/6/4
 * @description 所有请求的地址 和 请求的参数
 */
public interface HttpApis {

    /**
     * 登陆
     *
     * @param HRCode   账号
     * @param Password 密码
     * @param HosID    医院id
     * @param Version  版本号
     * @return
     */
    @FormUrlEncoded
    @POST("/api/MEmployee")
    Call<UserBean> login(
            @Field("HRCode") String HRCode,
            @Field("Password") String Password,
            @Field("HosptialID") String HosID,
            @Field("Version") String Version
    );

    /**
     * 升级
     *
     * @return
     */
    @GET("/api/AutoUpdate?id=00000000000001")
    Call<UpdateBean> autoUpdate();

    /**
     * 根据主码次码 获得盘点信息
     *
     * @param BarCodeSource    主码
     * @param SubBarCodeSource 次码
     * @param DeptCode         部门id
     * @return
     */
    @GET("/api/MInventory")
    Call<MInventoryBean> mInventory(
            @Query("BarCodeSource") String BarCodeSource,
            @Query("SubBarCodeSource") String SubBarCodeSource,
            @Query("DeptCode") String DeptCode
    );

    /**
     * 提交盘点信息
     *
     * @param JsonStr 盘点信息
     * @return
     */
    @FormUrlEncoded
    @POST("/api/MInventory")
    Call<BaseModel> MInventory(
            @Field("JsonStr") String JsonStr,
            @Field("type") int type
    );


    /**
     * 2.存放科室获取
     *
     * @return
     */
    @GET("/api/MDepartment")
    Call<MDepartmentBean> getMDepartment();

    /**
     * 验收产品条码解析
     *
     * @param BarCodeSource    主码
     * @param SubBarCodeSource 次码
     * @return
     */
    @GET("/api/MProdEnter")
    Call<MProdEnterBean> MProdEnter(
            @Query("BarCodeSource") String BarCodeSource,
            @Query("SubBarCodeSource") String SubBarCodeSource
    );

    /**
     * 验收产品提交
     *
     * @param JsonStr 盘点信息
     * @return
     */
    @FormUrlEncoded
    @POST("/api/MProdEnter")
    Call<MProdEnterResultBean> setMProdEnter(
            @Field("JsonStr") String JsonStr,
            @Field("type") int type
    );

    /**
     * 验收产品条码解析
     *
     * @return
     */
    @GET("/api/MProdEnterDetail")
    Call<MProdEnterDetailBean> MProdEnterDetail(@Query("Reg_id") int Reg_id);

    /**
     * 验收单据确认
     *
     * @param JsonStr 由实体类序列化出来的json串
     * @return
     */
    @FormUrlEncoded
    @POST("/api/MProdEnterDetail")
    Call<BaseModel> setMProdEnterDetail(
            @Field("JsonStr") String JsonStr
    );

    /**
     * 领用人获取
     *
     * @param HRCode
     * @return
     */
    @GET("/api/MEmployee")
    Call<LingLiaoBean> MEmployee(
            @Query("HRCode") String HRCode
    );

    /**
     * 领用产品条码解析
     *
     * @param BarCodeSource    主码
     * @param SubBarCodeSource 次码
     * @return
     */
    @GET("/api/MDepartmentCollar")
    Call<MProdEnterBean> MDepartmentCollar(
            @Query("BarCodeSource") String BarCodeSource,
            @Query("SubBarCodeSource") String SubBarCodeSource,
            @Query("StorehouseID") String StorehouseID
    );


    /**
     * 领用产品提交验证
     *
     * @param JsonStr 盘点信息
     * @return
     */
    @FormUrlEncoded
    @POST("/api/MDepartmentCollar")
    Call<MProdEnterResultBean> MDepartmentCollar(
            @Field("JsonStr") String JsonStr,
            @Field("type") int type
    );


    /**
     * 领用整张单据明细数据获取
     *
     * @return
     */
    @GET("/api/MDepartmentCollarDetail")
    Call<MProdEnterDetailBean> MDepartmentCollarDetail(@Query("DepartmentCollarID") String departmentCollarID);

    /**
     * 领用整单确认
     *
     * @return
     */
    @FormUrlEncoded
    @POST("/api/MDepartmentCollarDetail")
    Call<BaseModel> MDepartmentCollarDetail(@Field("JsonStr") String JsonStr,
                                            @Field("type") int type);

    /**
     * 领用还库产品条码解析
     *
     * @param BarCodeSource    主码
     * @param SubBarCodeSource 次码
     * @return
     */
    @GET("/api/MDepartmentCollarBack")
    Call<MProdEnterBean> MDepartmentCollarBack(
            @Query("BarCodeSource") String BarCodeSource,
            @Query("SubBarCodeSource") String SubBarCodeSource,
            @Query("DeptCode") String DeptCode,
            @Query("StorehouseID") String StorehouseID,
            @Query("HRCode") String HRCode
    );

    /**
     * 领用还库数据提交
     *
     * @param JsonStr 盘点信息
     * @return
     */
    @FormUrlEncoded
    @POST("/api/MDepartmentCollarBack")
    Call<MProdEnterResultBean> MDepartmentCollarBack(
            @Field("JsonStr") String JsonStr,
            @Field("type") int type
    );

    /**
     * 入库单信息获取
     *
     * @param InstoreCode
     * @return
     */
    @GET("/api/MWareHouse")
    Call<MWareHouseBean> MWareHouse(
            @Query("InstoreCode") String InstoreCode
    );

    /**
     * 图片上传
     * @param body
     * @param parts
     * @return
     */
    @Multipart
    @POST("/MWareHouse")
    Call<BaseModel> upLoadImg(
            @Part("description") RequestBody body,
            @Part() List<MultipartBody.Part> parts
    );

    @Multipart
    @POST("/MWareHouse")
    Call<BaseModel> upload(@Part("description") RequestBody description,
                           @Part  MultipartBody.Part part);
    @Multipart
    @POST("/MWareHouse")
    Call<BaseModel> upload(@Part("description") RequestBody description,
                           @Part("file") List<MultipartBody.Part> parts);

    @Multipart
    @POST("/MWareHouse")
    Call<BaseModel> uploadFilesFeedback(@PartMap Map<String, RequestBody> params);

}
