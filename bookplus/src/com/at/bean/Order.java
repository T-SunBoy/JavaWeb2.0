package com.at.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author tao
 * @version 1.0
 * 描述:订单类
 * @date 2020-07-27 16:17
 */
public class Order {
    //订单id
    private String orderId;
    //订单创建时间
    private Date creatTime;
    //订单的价格
    private BigDecimal price;
    //订单状态，0未发货，1已经发货、2已签收
    private Integer status=0;
    //订单对应的用户id
    private Integer userId;

    public Order() {
    }

    public Order(String orderId, Date creatTime, BigDecimal price, Integer status, Integer userId) {
        this.orderId = orderId;
        this.creatTime = creatTime;
        this.price = price;
        this.status = status;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", creatTime=" + creatTime +
                ", price=" + price +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
