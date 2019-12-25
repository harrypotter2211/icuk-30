package com.invsales.order.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invsales.order.domain.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer>{

	public List<Orders> findByCustomerId(String customerId);
	
	public List<Orders> findByOrderDate(Date orderDate);
}
