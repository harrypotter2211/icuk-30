package com.invsales.order.domain;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "order_detail")
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = -7920182028129856917L;

    @Id
    private int orderDetailId;

    private String productName;
    private int quantity;
    private Double price;

    @JsonBackReference("orders-orderdetail")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Orders order;

    public OrderDetail() {}

    public OrderDetail(int orderDetailId, String productName, int quantity, Double price, Orders order) {
        this.orderDetailId = orderDetailId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.order = order;
    }

    // Getters and Setters

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderDetailId=" + orderDetailId +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    @Override
    public int hashCode() {
        int result = orderDetailId;
        result = 31 * result + productName.hashCode();
        result = 31 * result + quantity;
        result = 31 * result + price.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDetail)) return false;
        OrderDetail that = (OrderDetail) o;
        return orderDetailId == that.orderDetailId &&
                quantity == that.quantity &&
                productName.equals(that.productName) &&
                price.equals(that.price);
    }
}
