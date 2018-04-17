package com.petabytes.webussiness.slorder.exception;

public class OrderException extends Exception {
    private String errMsg;

    private long errCode;

    public OrderException(String errMsg, long errCode) {
        this.errMsg = errMsg;
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public long getErrCode() {
        return errCode;
    }

    public void setErrCode(long errCode) {
        this.errCode = errCode;
    }
}
