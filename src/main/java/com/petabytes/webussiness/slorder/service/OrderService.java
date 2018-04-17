package com.petabytes.webussiness.slorder.service;

import com.petabytes.webussiness.slorder.domain.OrderAndDetails;
import com.petabytes.webussiness.slorder.domain.OrderPage;
import com.petabytes.webussiness.slorder.entity.Order;
import com.petabytes.webussiness.slorder.exception.OrderException;
import javafx.util.converter.TimeStringConverter;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.sql.Time;
import java.sql.Timestamp;

public interface OrderService {

    /**创建订单
     * @param order 订单
     * @return 订单id
     */
    @Transactional
    long createOrder(OrderAndDetails order) throws OrderException;

    /**
     * 修改订单
     * @param orderId
     * @param orderAndDetails
     * @return 新订单号
     * @throws OrderException
     */
    @Transactional
    long updateOrder(long orderId, OrderAndDetails orderAndDetails) throws OrderException;

    /**
     * 分页查询所有未完成的订单
     * @param pageable
     * @return
     * @throws OrderException
     */
    OrderPage<Order> searchAllUncomplete(Pageable pageable) throws OrderException;

    /**
     * 分页查询自己未完成的订单
     * @param pageable
     * @return
     * @throws OrderException
     */
    OrderPage<Order> searchMyUncomplete(Pageable pageable, long dealerId) throws OrderException;

    /**
     * 通过创建时间分页查询订单
     * @param pageable
     * @param beginTime
     * @param endTime
     * @return
     * @throws OrderException
     */
    OrderPage<Order> searchTimeDuration(Pageable pageable, Timestamp beginTime, Timestamp endTime) throws OrderException;
}