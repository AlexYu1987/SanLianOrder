package com.petabytes.webussiness.slorder.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PriceKey implements Serializable{

    @Column(name = "commodity_id")
    long commodityId;

    @Column(name = "dealer_level_id")
    long dealerLeverId;

    public PriceKey(long commodityId, long dealerLeverId) {
        this.commodityId = commodityId;
        this.dealerLeverId = dealerLeverId;
    }

    public long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(long commodityId) {
        this.commodityId = commodityId;
    }

    public long getDealerLeverId() {
        return dealerLeverId;
    }

    public void setDealerLeverId(long dealerLeverId) {
        this.dealerLeverId = dealerLeverId;
    }
}
