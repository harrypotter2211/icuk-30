package com.invsales.order.resource;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invsales.order.domain.Orders;
import com.invsales.order.service.OrdersService;

@RestController
@RequestMapping("/orders")
public class OrdersResource {

	@Autowired
	private OrdersService ordersService;
	
	@PostMapping("/create")
	public void createOrder(@RequestBody Orders orders) {
		ordersService.createOrder(orders);
	}

	@GetMapping("/findById/{orderId}")
	public Orders findOrderById(@PathVariable int orderId) {
		Orders orders  = ordersService.findOrderById(orderId);		
		return orders;
	}

	@GetMapping("/findByCustomerId/{customerId}")
	public List<Orders> findOrdersByCustomerId(@PathVariable String customerId) {
		List<Orders> ordersList  = ordersService.findOrdersByCustomerId(customerId);
		return ordersList;
	}

	@GetMapping("/findByDate/{date}")
	public List<Orders> findOrdersByDate(@PathVariable Date date) {
		List<Orders> ordersList  = ordersService.findOrdersByDate(date);
		return ordersList;
	}
}
