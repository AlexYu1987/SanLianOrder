package com.petabytes.webussiness.slorder.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Details {

    @JsonProperty(value = "merchandiseid")
    private long merchandiseId;

    @JsonProperty(value = "quantity")
    private int count;

    public long getMerchandiseId() {
        return merchandiseId;
    }

    public void setMerchandiseId(long merchandiseId) {
        this.merchandiseId = merchandiseId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
