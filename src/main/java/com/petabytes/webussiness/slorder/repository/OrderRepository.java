package com.petabytes.webussiness.slorder.repository;

import com.petabytes.webussiness.slorder.entity.Order;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    Order save(Order order);
}
