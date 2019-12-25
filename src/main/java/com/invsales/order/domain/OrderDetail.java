package com.invsales.order.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "order_detail")
public class OrderDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4468614038348145955L;

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderDetailId;

	private String productId;
	private int quantity;

	@JsonBackReference("orders-orderdetail")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Orders order;

	public OrderDetail() {
	}

	public OrderDetail(int orderDetailId, String productId, int quantity, Orders order) {
		super();
		this.orderDetailId = orderDetailId;
		this.productId = productId;
		this.quantity = quantity;
		this.order = order;
	}

	public int getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "OrderDetail [orderDetailId=" + orderDetailId + ", productId=" + productId + ", quantity=" + quantity
				+ ", order=" + order + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + orderDetailId;
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + quantity;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetail other = (OrderDetail) obj;
		if (orderDetailId != other.orderDetailId)
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}

}
