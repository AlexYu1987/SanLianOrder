package com.petabytes.webussiness.slorder.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "t_dealer")
public class Dealer {

    public Dealer(Long id) {
        this.id = id;
    }

    public Dealer(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @ManyToOne
    @JoinColumn(name = "level_id")
    DealerLevel dealerLevel;

    Timestamp createTime;

    String nickName;

    String wechatOpenId;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    ContactInfo contactInfo;

    float balance;

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getWechatOpenId() {
        return wechatOpenId;
    }

    public void setWechatOpenId(String wechatOpenId) {
        this.wechatOpenId = wechatOpenId;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DealerLevel getDealerLevel() {
        return dealerLevel;
    }

    public void setDealerLevel(DealerLevel dealerLever) {
        this.dealerLevel = dealerLevel;
    }
}
