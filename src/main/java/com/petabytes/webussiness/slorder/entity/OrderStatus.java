package com.petabytes.webussiness.slorder.entity;

import javax.persistence.Embeddable;

public enum OrderStatus {
    READY,
    PROCESSING,
    COMPLETED;
}
