package com.petabytes.webussiness.slorder.repository;

import com.petabytes.webussiness.slorder.entity.OrderDetails;
import org.springframework.data.repository.CrudRepository;

public interface OrderDetailsRepository extends CrudRepository<OrderDetails, Long> {
    void deleteByOrderId(long orderId);
}
