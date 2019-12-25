package com.invsales.order.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invsales.order.domain.Orders;
import com.invsales.order.repository.OrderDetailRepository;
import com.invsales.order.repository.OrderRepository;
import com.invsales.order.service.OrdersService;

@Service
public class OrderServiceImpl implements OrdersService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Override
	public void createOrder(Orders orders) {
		orderRepository.save(orders);
	}

	@Override
	public Orders findOrderById(int orderId) {
		Optional<Orders> orders = orderRepository.findById(orderId);
		return orders.get();
	}

	@Override
	public List<Orders> findOrdersByCustomerId(String customerId) {
		List<Orders> ordersList = orderRepository.findByCustomerId(customerId);
		return ordersList;
	}

	@Override
	public List<Orders> findOrdersByDate(Date orderDate) {
		List<Orders> ordersList = orderRepository.findByOrderDate(orderDate);
		return ordersList;
	}

}
