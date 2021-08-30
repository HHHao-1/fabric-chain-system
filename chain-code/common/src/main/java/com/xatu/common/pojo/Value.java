package com.xatu.common.pojo;

/**
 * @Author hao
 * @create 2021/6/29 9:59 下午
 */
public class Value {
  private String id;
  
  private String type;
  
  private Values values;
  
  public void setId(String id) {
    this.id = id;
  }
  
  public String getId() {
    return this.id;
  }
  
  public void setType(String type) {
    this.type = type;
  }
  
  public String getType() {
    return this.type;
  }
  
  public void setValues(Values values) {
    this.values = values;
  }
  
  public Values getValues() {
    return this.values;
  }
  
  public class Values {
    private String author;
    
    private String time;
    
    private String timestamp;
    
    private String type;
    
    private String value;
    
    public void setAuthor(String author) {
      this.author = author;
    }
    
    public String getAuthor() {
      return this.author;
    }
    
    public void setTime(String time) {
      this.time = time;
    }
    
    public String getTime() {
      return this.time;
    }
    
    public void setTimestamp(String timestamp) {
      this.timestamp = timestamp;
    }
    
    public String getTimestamp() {
      return this.timestamp;
    }
    
    public void setType(String type) {
      this.type = type;
    }
    
    public String getType() {
      return this.type;
    }
    
    public void setValue(String value) {
      this.value = value;
    }
    
    public String getValue() {
      return this.value;
    }
    
  }
}
