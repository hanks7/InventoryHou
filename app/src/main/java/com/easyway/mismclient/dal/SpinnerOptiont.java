package com.easyway.mismclient.dal;

public class SpinnerOptiont {
	private String value = "";  
    private String text = "";  
  
    public SpinnerOptiont() {  
        value = "";  
        text = "";  
    }  
  
    public SpinnerOptiont(String value, String text) {  
        this.value = value;  
        this.text = text;  
    }  
  
    @Override  
    public String toString() {   
        return text;  
    } 
    
    public void SetText(String text)
    {
    	this.text = text;
    }
    
    public void SetValue(String value)
    {
    	this.value = value;
    }
    
    public String getValue() {  
        return value;  
    }  
    public String getText() {  
        return text;  
    }  
}
