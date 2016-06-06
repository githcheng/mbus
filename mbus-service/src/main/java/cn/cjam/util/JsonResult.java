package cn.cjam.util;

import com.alibaba.fastjson.JSONObject;

/**
 * 符合 函数式 编程风格
 */
public class JsonResult extends JSONObject{

    private static final long serialVersionUID = 1L;

    private static final int CODE_SUCCESS = 0;
    
    private static final int CODE_FAIL = -1;
    
    private int code;
    
    private String message;
    
    private Object data;
    
    public static JsonResult build(){
        return new JsonResult();
    }
    
    public JsonResult success(){
        this.put("code", CODE_SUCCESS);
        this.put("msg", "success");
        return this;
    }
    
    public JsonResult fail(){
        this.put("code", CODE_FAIL);
        return this;
    }
    public JsonResult withCode(int code){
        this.put("code", code);
        return this;
    }
    
    public JsonResult withData(Object data){
        this.put("data", data);
        return this;
    }
    public JsonResult withMessage(String message){
        this.put("msg", message);
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
    
}
