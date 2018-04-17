package com.petabytes.webussiness.slorder.controller;

import com.petabytes.webussiness.slorder.domain.OrderAndDetails;
import com.petabytes.webussiness.slorder.exception.ErrorMsg;
import com.petabytes.webussiness.slorder.exception.OrderException;
import com.petabytes.webussiness.slorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(value = "cgi-bin/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping(value = "order")
    public Object createOrder(@RequestBody OrderAndDetails order) {
        long orderId;
        try {
           orderId = orderService.createOrder(order);
        } catch (OrderException e) {
            return new ErrorMsg(e);
        } catch (Exception e) {
            return new ErrorMsg();
        }

        return orderId;
    }

    @PutMapping(value = "/{orderid}")
    public Object updateOrder(@PathVariable("orderid") long orderId, @RequestBody OrderAndDetails order) {
        try {
            return orderService.updateOrder(orderId, order);
        } catch (OrderException e)
        {
            return new ErrorMsg(e);
        } catch (Exception e) {
            return new ErrorMsg();
        }
    }

    @RequestMapping(value = "/uncomplete", method = RequestMethod.GET)
    public Object searchAllUncomplete(@PageableDefault(value = 20, sort = {"createTime"}, direction = Sort.Direction.ASC)
                                        Pageable pageable) {
        try {
            return orderService.searchAllUncomplete(pageable);
        } catch (OrderException e) {
            return  new ErrorMsg(e);
        } catch (Exception e) {
            return new ErrorMsg();
        }
    }

    @RequestMapping(value = "order/createtime", method = RequestMethod.GET)
    public Object searchByCreateTime(Pageable pageable, @RequestParam("begin") String beginTime,
                                     @RequestParam("end") String endTime, @RequestParam("format") String format) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            Date begin = dateFormat.parse(beginTime);
            Date end = dateFormat.parse(endTime);
            return orderService.searchTimeDuration(pageable, new Timestamp(begin.getTime()), new Timestamp(end.getTime()));

        } catch (OrderException e) {
            return new ErrorMsg(e);
        } catch (ParseException e) {
            return new ErrorMsg(42551L, "日期格式错误");
        }catch (Exception e) {
            return new ErrorMsg();
        }
    }
}
