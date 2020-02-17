package com.easyway.mismclient.model;

public class Attachments
{
    private int AttachmenID;

    private String InfoId;

    private String Type;

    private String FileName;

    private String FilePath;

    private String SuffixName;

    private int FileSize;

    private String Sequence;

    private String ShowName;

    private String CreateTime;

    private String CreateUser;

    private String IsVoid;

    public void setAttachmenID(int AttachmenID){
        this.AttachmenID = AttachmenID;
    }
    public int getAttachmenID(){
        return this.AttachmenID;
    }
    public void setInfoId(String InfoId){
        this.InfoId = InfoId;
    }
    public String getInfoId(){
        return this.InfoId;
    }
    public void setType(String Type){
        this.Type = Type;
    }
    public String getType(){
        return this.Type;
    }
    public void setFileName(String FileName){
        this.FileName = FileName;
    }
    public String getFileName(){
        return this.FileName;
    }
    public void setFilePath(String FilePath){
        this.FilePath = FilePath;
    }
    public String getFilePath(){
        return this.FilePath;
    }
    public void setSuffixName(String SuffixName){
        this.SuffixName = SuffixName;
    }
    public String getSuffixName(){
        return this.SuffixName;
    }
    public void setFileSize(int FileSize){
        this.FileSize = FileSize;
    }
    public int getFileSize(){
        return this.FileSize;
    }
    public void setSequence(String Sequence){
        this.Sequence = Sequence;
    }
    public String getSequence(){
        return this.Sequence;
    }
    public void setShowName(String ShowName){
        this.ShowName = ShowName;
    }
    public String getShowName(){
        return this.ShowName;
    }
    public void setCreateTime(String CreateTime){
        this.CreateTime = CreateTime;
    }
    public String getCreateTime(){
        return this.CreateTime;
    }
    public void setCreateUser(String CreateUser){
        this.CreateUser = CreateUser;
    }
    public String getCreateUser(){
        return this.CreateUser;
    }
    public void setIsVoid(String IsVoid){
        this.IsVoid = IsVoid;
    }
    public String getIsVoid(){
        return this.IsVoid;
    }
}
