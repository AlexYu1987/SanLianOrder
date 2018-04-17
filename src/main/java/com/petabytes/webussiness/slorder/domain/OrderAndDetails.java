package com.petabytes.webussiness.slorder.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OrderAndDetails {
    @JsonProperty(value = "dealerid", required = true)
    private long dealerId;

    @JsonProperty(value = "express", required = true)
    private ExpressInfo expressInfo;

    @JsonProperty(value = "usesf")
    private boolean isSfExpress;

    private String note;

    @JsonProperty(value = "details")
    private List<Details> detailsList;

    public List<Details> getDetailsList() {
        return detailsList;
    }

    public void setDetailsList(List<Details> detailsList) {
        this.detailsList = detailsList;
    }

    public long getDealerId() {
        return dealerId;
    }

    public void setDealerId(long dealerId) {
        this.dealerId = dealerId;
    }

    public boolean isSfExpress() {
        return isSfExpress;
    }

    public void setSfExpress(boolean sfExpress) {
        isSfExpress = sfExpress;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ExpressInfo getExpressInfo() {
        return expressInfo;
    }

    public void setExpressInfo(ExpressInfo expressInfo) {
        this.expressInfo = expressInfo;
    }
}
