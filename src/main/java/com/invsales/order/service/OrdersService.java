package com.invsales.order.service;

import java.util.Date;
import java.util.List;

import com.invsales.order.domain.Orders;

public interface OrdersService {
	
	public void createOrder(Orders orders);
	
	public Orders findOrderById(int orderId);
	
	public List<Orders> findOrdersByCustomerId(String customerId);
	
	public List<Orders> findOrdersByDate(Date date);
	
	//public List<Orders> findOrdersByCustomerIdAndDate(String customerId, Date date);
	
   
}
