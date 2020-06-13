package cn.project.util;

public class HttpResult {
	private String success = "true";
	private String errorCode = "0";
	private String msg;
	private Object data;

	public HttpResult(String success, String errorCode, String msg, Object data) {
		this.success = success;
		this.errorCode = errorCode;
		this.msg = msg;
		this.data = data;
	}

	public static HttpResult ok(){
		return new HttpResult("true","0","",null);
	}
	public static HttpResult ok(String msg,Object data){
		return new HttpResult("true","0",msg,data);
	}
	public static HttpResult ok(String msg){
		return new HttpResult("true","0",msg,null);
	}

	public static HttpResult error(String errorCode,String msg){
		return new HttpResult("false",errorCode,msg,null);
	}

	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}