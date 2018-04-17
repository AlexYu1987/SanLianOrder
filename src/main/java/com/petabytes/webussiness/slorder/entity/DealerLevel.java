package com.petabytes.webussiness.slorder.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_dealer_level")
public class DealerLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;

    //充值金額（不同的代理等級和充值金額掛鉤)
    String amount;

    String comment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long leverId) {
        this.id = id;
    }
}
