package com.ksuwimon.atmsimulation.model;

public class ResultJson {

	private Integer success;
	private Object result;
	private String message;
	private String errorCode;

	public ResultJson() {
	}

	public ResultJson(Integer success, Object result, String message) {
		super();
		this.success = success;
		this.result = result;
		this.message = message;
	}
	
	public ResultJson(Integer success, Object result, String message, String errorCode) {
		super();
		this.success = success;
		this.result = result;
		this.message = message;
		this.errorCode=errorCode;
	}
	
	public Integer getSuccess() {
		return success;
	}

	public void setSuccess(Integer success) {
		this.success = success;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
