package com.petabytes.webussiness.slorder.service;

import com.petabytes.webussiness.slorder.domain.Details;
import com.petabytes.webussiness.slorder.domain.ExpressInfo;
import com.petabytes.webussiness.slorder.domain.OrderAndDetails;
import com.petabytes.webussiness.slorder.domain.OrderPage;
import com.petabytes.webussiness.slorder.entity.*;
import com.petabytes.webussiness.slorder.exception.OrderException;
import com.petabytes.webussiness.slorder.repository.CommodityPriceRepository;
import com.petabytes.webussiness.slorder.repository.DealerRepository;
import com.petabytes.webussiness.slorder.repository.OrderDetailsRepository;
import com.petabytes.webussiness.slorder.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    CommodityPriceRepository commodityPriceRepository;

    @Autowired
    DealerRepository dealerRepository;

    @Override
    @Transactional
    public long createOrder(OrderAndDetails orderAndDetails) throws OrderException{
        float charge = calculateCharge(orderAndDetails);

        Dealer dealer = dealerRepository.findById(orderAndDetails.getDealerId()).get();

        if (Float.compare(charge, dealer.getBalance()) > 0) {
            throw new OrderException("余额不足", 42511L);
        }

        Order order = new Order();
        order.setCharge(charge);
        order.setDealer(new Dealer(orderAndDetails.getDealerId()));
        order.setUseSf(orderAndDetails.isSfExpress());
        order.setStaus(OrderStatus.READY);

        ContactInfo info = new ContactInfo();
        ExpressInfo expressInfo = orderAndDetails.getExpressInfo();
        info.setCity(expressInfo.getCity());
        info.setFemale(expressInfo.isFemale());
        info.setProvince(expressInfo.getProvince());
        info.setStreet(expressInfo.getStreet());
        info.setName(expressInfo.getName());
        info.setPhone(expressInfo.getPhoneNumber());

        order.setContactInfo(info);

        Timestamp time = new Timestamp(System.currentTimeMillis());

        order.setCreateTime(time);
        order.setLastModifyTime(time);
        order.setStaus(OrderStatus.READY);

        final Order orderSaved = orderRepository.save(order);

        dealerRepository.chargeBalanceById(orderSaved.getDealer().getId(), charge);

       orderAndDetails.getDetailsList().forEach(details -> {
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setOrder(orderSaved);

            Commodity commodity = new Commodity();
            commodity.setId(details.getMerchandiseId());

            orderDetails.setCommodity(commodity);
            orderDetails.setCount(details.getCount());
            orderDetailsRepository.save(orderDetails);
        });

        return orderSaved.getOrderID();
    }

    @Override
    @Transactional
    public long updateOrder(long orderId, OrderAndDetails orderAndDetails) throws OrderException{
        Order order;
        try {
            order = orderRepository.findById(orderId).get();
        } catch (NoSuchElementException e) {
            throw new OrderException("订单不存在",42527L);
        }
        float charge = order.getCharge();
        long dealerId = orderAndDetails.getDealerId();

        dealerRepository.rechargeBalanceById(dealerId, charge);
        orderDetailsRepository.deleteByOrderId(orderId);
        orderRepository.delete(order);

        return createOrder(orderAndDetails);
    }

    @Override
    public OrderPage<Order> searchMyUncomplete(Pageable pageable, long dealerId) throws OrderException {
        return null;
    }

    @Override
    public OrderPage<Order> searchTimeDuration(Pageable pageable, Timestamp beginTime, Timestamp endTime)
            throws OrderException {
        Page<Order> page = orderRepository.findAll((Specification<Order>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate greetThan = criteriaBuilder.greaterThan(root.<Timestamp>get("createTime"), beginTime);
            Predicate lessThan = criteriaBuilder.lessThan(root.<Timestamp>get("createTime"), endTime);
            return criteriaBuilder.and(greetThan, lessThan);
        }, pageable);

        return new OrderPage<>(page.getTotalElements(), page.getTotalPages(), page.getNumber(), page.getNumberOfElements(),
                page.stream().collect(Collectors.toList()));
    }

    @Override
    @Transactional
    public OrderPage<Order> searchAllUncomplete(Pageable pageable) {
        Page<Order> page = orderRepository.findAll((Specification<Order>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate stateReady = criteriaBuilder.equal(root.<OrderStatus>get("status"), OrderStatus.READY);
            Predicate stateAccep = criteriaBuilder.equal(root.<OrderStatus>get("status"), OrderStatus.PROCESSING);
            return criteriaBuilder.or(stateReady, stateAccep);
        },pageable);

        return new OrderPage<>(page.getTotalElements(), page.getTotalPages(), page.getNumber(), page.getNumberOfElements(),
                page.stream().collect(Collectors.toList()));
    }

    //计算订单费用
    private float calculateCharge(OrderAndDetails orderAndDetails) throws OrderException {
        float sum = 0.0f;
        Dealer dealer;
        try {
           dealer = dealerRepository.findById(orderAndDetails.getDealerId()).get();
        } catch(NoSuchElementException e)
        {
            throw new OrderException("代理不存在", 42516L);
        }

        for (Details details : orderAndDetails.getDetailsList()) {
            PriceKey key = new PriceKey(details.getMerchandiseId(),dealer.getDealerLevel().getId());
            float price;
            try {
                price = commodityPriceRepository.findById(key).get().getPrice();
            } catch (NoSuchElementException e) {
                throw new OrderException("商品不存在", 42517L);
            }
            sum += price * details.getCount();
        }
        return sum;
    }
}
