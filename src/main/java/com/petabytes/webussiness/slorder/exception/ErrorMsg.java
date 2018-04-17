package com.petabytes.webussiness.slorder.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 系统错误信息
 */
public class ErrorMsg {
    @JsonProperty(value = "errcode")
    private long errCode;

    @JsonProperty(value = "errmsg")
    private String errMsg;

    public ErrorMsg(OrderException e) {
        errCode = e.getErrCode();
        errMsg = e.getErrMsg();
    }

    public ErrorMsg(long errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public ErrorMsg() {
        errCode = -1L;
        errMsg = "系统繁忙";
    }

    public long getErrCode() {
        return errCode;
    }

    public void setErrCode(long errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
