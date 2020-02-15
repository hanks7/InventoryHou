package com.easyway.mismclient.dao;

/**
 * @author 侯建军 47466436@qq.com
 * @class com.easyway.mismclient.dao.DBContants
 * @time 2018/7/23 16:42
 * @description 请填写描述
 */
public class DBContants {

    /**
     * 雇员表
     */
    public static final String TB_EMPLOYEE = "TB_Employee";
    /**
     * 产品库存明细表名称
     */
//    public static final String TB_PRODUCT_INVENTORY_DETAIL = "TP_ProductInventoryDetail";
    /**
     * 存取科室
     */
    public static final String TB_DEPARTMENT = "TB_Department";
    /**
     * 条形码信息表名称
     */
    public static final String TB_BARCODE_INFO = "TB_BarcodeInfo";
    /**
     * 产品基本信息表名称
     */
    public static final String TB_PRODUCT_INFO = "TB_ProductInfo";
    /**
     * 供应商表名
     */
    public static final String TB_ENTERPRISE_INFO = "TB_EnterpriseInfo";
    /**
     * 验收开始结束的那张表的表名
     */
    public static final String TB_INVENTORY = "TB_Inventory";
    /**
     * 验收明细表
     */
    public static final String TB_INVENTORY_DETAIL = "TB_InventoryDetail";
    /**
     * 规格明细表
     */
    public static final String TB_SPECUNIT = "TB_SpecUnit";


    /**
     * 用户登录表
     */
    public static final String create_tb_employee = "Create table if not exists " + TB_EMPLOYEE + " (" +
            "isLogin  INTEGER," +
            "HRCode  char(10)," +
            "DeptCode  char(6)," +
            "WorkDeptCode  char(6)," +
            "EmployeeName  nvarchar(50)," +
            "DutyName  nvarchar(20)," +
            "Password  varchar(50)," +
            "HosptialID  integer," +
            "HosptialName  nvarchar(50)," +
            "DepartmentName  nvarchar(1000)," +
            "PinYin  nvarchar(10)," +
            "WuBi  nvarchar(10)," +
            "Status  char(10)," +
            "IsVoid  char(1)," +
            "HisCode  varchar(20)," +
            "PRIMARY KEY (HRCode ASC)" +
            ");";
    /**
     * 产品基本信息表
     */
    public static final String create_tb_productinfo = "Create table if not exists " + TB_PRODUCT_INFO + " ( " +
            "ProductInfoID  integer , " +
            "isChecked  integer , " +
            "BrandID           integer," +
            "CategoryCode       char(10)," +
            "CusCategoryCode  nvarchar(50)," +
            "EnterpriseID integer,DetailID  integer ," +
            "DefaultSupplierID  integer ," +
            "Code nvarchar(50),Name nvarchar(300)," +
            "GeneralName nvarchar(300)," +
            "Specification  integer," +
            "Model integer ,Place  nvarchar(500) ," +
            "Unit  integer ,PackageUnit  integer ," +
            "PackageQuantity  integer ," +
            "StorageCondition  nvarchar(200) ," +
            "MatInUnit  integer ," +
            "MatInSplitNum  integer ," +
            "UseUnit  integer ," +
            "UseSplitNum  integer ," +
            "PurchasePrice  decimal(18,2) ," +
            "PriceCeiling  decimal(18,2) ," +
            "PriceSource  nvarchar(500) ," +
            "MinPurchaseAmount  decimal(18,2) ," +
            "CreateTime  text ," +
            "UpdateTime  text ," +
            "ShelfLife  integer ," +
            "PackingInstruction  nvarchar(200) ," +
            "IsVoid  char(1) ," +
            "WhetherAudit  integer ," +
            "ChargePrice  decimal(18,2) ," +
            "AuditPriceCeiling  decimal(18,2) ," +
            "HealthPermitID  integer ," +
            "Attachment  integer ," +
            "MapID  char(20) ," +
            "Qpenod  text ," +
            "IsMandatoryNeedProductionDate  char(1) ," +
            "SpecialFlag  char(20) ," +
            "Frequency  integer  ," +
            "CanQuery  char(1) ," +
            "ChargeState  char(1) ," +
            "ChargeProportion  decimal(18,2) ," +
            "BeforeTaxPrice  decimal(18,2) ," +
            "ConversionUnit  integer ,TopLimit  integer ,DownLimit  integer ,AlarmLimit  integer ,limitYear  integer ,limitMonth  integer ,limitDay  integer ,CodeAuditStatus  char(1) ,Buy  char(1) ,HospitalSpec  nvarchar(300) ,MedicalTypeID  nvarchar(50) ,ChargeStatus  char(1) ,PaceMakerTypeID  nvarchar(500) ,MedicalTypeAuditStatus  char(1) ,MedicalTypeAuditDate  text  ,PriceCodeAuditStatus  char(1) ,Remark  nvarchar(500) ,TryCount  integer ,TryTime  text ,MaterialCode  nvarchar(500) ,MaterialCodeAuditStatus  char(1) ,HisCode  varchar(20));";

    /**
     * 条形码信息表
     */
    public static final String create_tb_barcodeinfo = "Create table if not exists " + TB_BARCODE_INFO + " ("
            + "BarcodeID             int  ,"//条形码编码
            + "ProductInfoID       int ,"//物资代码
            + "Barcode             nvarchar(100)       ,"//条形码
            + "BarcodeType    char(1)   ,"//条形码类别
            + "CreateTime    text  ,"//创建日期
            + "UpdateTime           text  ,"//更新日期
            + "IsVoid       char(1)           )";//是否有效

    /**
     * 规格信息表
     */
    public static final String create_tb_specunit = "Create table if not exists " + TB_SPECUNIT + " ("
            + "SpecID             int  ,"//主码
            + "Name        nvarchar(100) )";//规格名称


    /**
     * 产品库存明细表
     */
   /* public static final String create_tb_productinventorydetail = "Create table if not exists " + TB_PRODUCT_INVENTORY_DETAIL + " ("
            + "DetailID             int  ,"//库存明细ID
            + "ProductInfoID       int ,"//产品代码
            + "DeptCode             char(6)       ,"//科室编号
            + "SupplierID    int   ,"//供应商ID


            + "ProductBatch    nvarchar(50)  ,"//产品批次
            + "ExpireDate           text  ,"//产品有效期
            + "RetailPrice           decimal(18,2)  ,"//标准零售价
            + "RealUnitPrice           decimal(18,2)  ,"//实际单价
            + "TaxWSalePrice           decimal(18,2)  ,"//含税批发价(单价)
            + "InDate           text  ,"//入库日期
            + "FinalOutDate           text  ,"//最后使用日期

            + "Quantity           decimal(18,2)  ,"//数量
            + "CurQuantity           decimal(18,2)  ,"//现有库存
            + "LockQuantity           decimal(18,2)  ,"//锁定量
            + "LockSign           char(1)  ,"//优先级标志
            + "DiscardSign           char(1)  ,"//报废标志
            + "CustomerCategoryID           char(10)  ,"//产品自定义类别ID

            + "ProductionDate           text  ,"//生产日期
            + "SerialNumber           nvarchar(50)  ,"//序列号
            + "RegistrationCard           nvarchar(100)  ,"//注册证号
            + "InventoryType       char(1)           )";//PDA区分*/

    /**
     * 产品库存明细表
     */
    public static final String create_tb_tb_inventorydetail = "Create table if not exists  "+TB_INVENTORY_DETAIL+"  ( " +
            " DetailID  TEXT, " +
            " InventoryNo  TEXT, " +
            " ProductInfoID  TEXT," +
            " RealTimeAmount  TEXT," +
            " FactAmount  TEXT," +
            " Unit  TEXT, " +
            " UnitPrice  TEXT," +
            " ShelfID  TEXT, " +
            " ProductBatch  TEXT, " +
            " TakeInventoryDate  TEXT," +
            " SerialNo  TEXT, " +
            " SupplierID  TEXT," +
            " ExpireDate  TEXT," +
            " InDate  TEXT, " +
            " CustomCategoryID  TEXT, " +
            " Moneys  TEXT," +
            " Reason  TEXT," +
            " CreateTime  TEXT," +
            " UpdateTime  TEXT, " +
            " IsVoid  TEXT," +
            " ProductionDate  TEXT," +
            " SerialNumber  TEXT," +
            " RegistrationCard  TEXT" +
            ");";

    /**
     * 存取科室
     */
    public static final String create_tb_department = "Create table if not exists   " + TB_DEPARTMENT + "   ( " +
            " DeptCode   TEXT,  " +
            "DepartmentName   TEXT,  " +
            "Property   TEXT,  MedInsureDeptCode   TEXT,  " +
            "MedInsureDeptName   TEXT,  HisDepCode   TEXT,  " +
            "HisDepType   TEXT,  PinYin   TEXT,  WuBi   TEXT,  " +
            "OrderNum   TEXT,  AliasName   TEXT,  AliasPinYin   TEXT,  " +
            "AliasWuBi   TEXT,  Remark   TEXT,  IsStorehouse   TEXT,  " +
            "IsVoid   TEXT,  IsWarehouse   TEXT,  IsFollowSurgical   TEXT,  " +
            "IsInOutCoincident   TEXT,  " +
            "ParentDeptCode   TEXT,  " +
            "IsAudit   TEXT,  " +
            "ReportCode   TEXT,  " +
            "WarningTime   TEXT);";

    /**
     * 供应商
     */
    public static final String create_tb_enterpriseInfo = "Create table if not exists    " + TB_ENTERPRISE_INFO + " (" +
            "           EnterpriseID  INTEGER," +
            "           EnterpriseName  TEXT," +
            "           EnterpriseAddr  TEXT," +
            "            Type  INTEGER," +
            "            LegalRepresentative  TEXT," +
            "            Manager  TEXT," +
            "            RegisteredCapital  TEXT," +
            "            RegisteredAddr  TEXT," +
            "            Nation  TEXT," +
            "            Province  TEXT," +
            "            City  TEXT," +
            "            Phone  TEXT," +
            "            Post  TEXT," +
            "            Fax  TEXT," +
            "            HomePage  TEXT," +
            "            Bank1  TEXT," +
            "            Bank2  TEXT," +
            "            BankAccount1  TEXT," +
            "            BankAccount2  TEXT," +
            "            ContactName  TEXT," +
            "            ContactPhone  TEXT," +
            "            ContactMobile  TEXT," +
            "            ContactFax  TEXT," +
            "            ContactMail  TEXT," +
            "            ContactDepartment  TEXT," +
            "            ContactDuty  TEXT," +
            "            CreateTime  TEXT," +
            "            UpdateTime  TEXT DEFAULT NULL," +
            "            UseCategory  TEXT," +
            "            PinYin  TEXT," +
            "            WuBi  TEXT," +
            "            MapId  TEXT," +
            "            IsThreeOneLicence  TEXT," +
            "            IsVoid  TEXT," +
            "            QYBM  TEXT," +
            "            ReportCode  TEXT," +
            "            VendorState  TEXT)";

    /**
     * 验收开始结束的那张表
     */
    public static final String create_tb_inventory = "CREATE TABLE  " + TB_INVENTORY + "  ( " +
            " InventoryNo  TEXT, " +
            " DeptCode  TEXT, " +
            " InventoryCode  TEXT, " +
            " Status  TEXT," +
            " BeginDate  TEXT," +
            " EndDate  TEXT, " +
            " InventoryExeMode  TEXT," +
            " Handler  TEXT," +
            " BLMaker  TEXT," +
            " Auditor  TEXT," +
            " BLDate  TEXT," +
            " AuditorDate  TEXT," +
            " AuditorDescription  TEXT," +
            " CreateTime  TEXT, " +
            " UpdateTime  TEXT, " +
            " CustomCategorys  TEXT," +
            " StorehouseID  TEXT, " +
            " Remark  TEXT, " +
            " InventoryType  TEXT" +
            " );" ;


}
