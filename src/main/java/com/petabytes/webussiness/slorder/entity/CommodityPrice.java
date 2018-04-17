package com.petabytes.webussiness.slorder.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_commodity_Price")
public class CommodityPrice {

    @EmbeddedId
    PriceKey priceKey;

    float price;

    public PriceKey getPriceKey() {
        return priceKey;
    }

    public void setPriceKey(PriceKey priceKey) {
        this.priceKey = priceKey;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
