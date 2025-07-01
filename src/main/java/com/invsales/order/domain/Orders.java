package com.invsales.order.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "orders")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1235524755717619937L;

    @Id
    private int orderId;

    private String customerId;
    private Date orderDate;
    private int totalProducts;
    private Double totalPrice;

    @JsonManagedReference("orders-orderdetail")
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetailList;

    public Orders() {}

    public Orders(int orderId, String customerId, Date orderDate, int totalProducts, Double totalPrice,
                  List<OrderDetail> orderDetailList) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.totalProducts = totalProducts;
        this.totalPrice = totalPrice;
        this.orderDetailList = orderDetailList;
    }

    // Getters and Setters

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(int totalProducts) {
        this.totalProducts = totalProducts;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", customerId='" + customerId + '\'' +
                ", orderDate=" + orderDate +
                ", totalProducts=" + totalProducts +
                ", totalPrice=" + totalPrice +
                ", orderDetailList=" + orderDetailList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Orders)) return false;
        Orders orders = (Orders) o;
        return orderId == orders.orderId &&
                totalProducts == orders.totalProducts &&
                customerId.equals(orders.customerId) &&
                orderDate.equals(orders.orderDate) &&
                totalPrice.equals(orders.totalPrice);
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + customerId.hashCode();
        result = 31 * result + orderDate.hashCode();
        result = 31 * result + totalProducts;
        result = 31 * result + totalPrice.hashCode();
        return result;
    }
}
