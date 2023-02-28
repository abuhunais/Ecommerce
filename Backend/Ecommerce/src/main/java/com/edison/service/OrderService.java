package com.edison.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.PathVariable;

import com.edison.model.Customer;
//import com.edison.model.Customer;
import com.edison.model.Order;
import com.edison.model.OrderItem;
import com.edison.model.OrderStatus;
import com.edison.model.Product;
import com.edison.model.SalesReport;
import com.edison.repo.CustomerRepository;
//import com.edison.model.SalesReport;
//import com.edison.repo.CustomerRepository;
import com.edison.repo.OrderRepository;
import com.edison.repo.ProductRepository;
import com.edison.repo.ReportRepository;
//import com.edison.repo.ReportRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired 
	private CustomerRepository customerRepository;
	@Autowired
	private ReportRepository reportRepository;

//	@Autowired
//	private CustomerRepository customerRepository;
//	@Autowired
//	private ReportRepository reportRepository;
//	@Autowired
//	private ReportService reportService;


	public List<Order> getOrderHistory() {
		return orderRepository.findAll();
	}

	public Order getOrderStatus(Long orderId) {
		return orderRepository.findById(orderId).orElseThrow();
	}
//	public Order createOrder(Long productId, Long orderId) throws Exception {
//	    Product product = productRepository.findById(productId).orElse(null);
//	    if (product == null) {
//	      throw new Exception("Product with id " + productId + " not found");
//	    }
//
//	    Order order = orderRepository.findById(orderId).orElse(null);
//	    if (order == null) {
//	      throw new Exception("Order with id " + orderId + " not found");
//	    }
//
//	    double totalPrice = product.getPrice() * order.getQuantity();
//	    Date date = new Date(productId);
//	    Long customerId = order.getCustomer().getId();
//	    SalesReport salesReport = new SalesReport(date, date, totalPrice);
//
//	    order.setOrderDate(date);
//        order.setTotalPrice(totalPrice);
//	    order.setCustomerId(customerId);
//	    order.setSalesReport(salesReport);
//
//	    return orderRepository.save(order);
//	  }

	public Order createOrder(Long productId, Long customerId) {
		Product product = productRepository.findById(productId).orElseThrow();
		Customer customer = customerRepository.findById(customerId).orElse(null);
		//SalesReport salesReport = reportRepository.
		List<OrderItem> orderItems = new ArrayList<>();
		OrderItem orderItem = new OrderItem();
		orderItem.setProduct(product);
		orderItems.add(orderItem);
		Order order = new Order();
		order.setOrderItems(orderItems);
		order.setCustomerId(customer);
		order.setStatus(OrderStatus.PROCESSING.toString());
		//order.setSalesReport(null);
		order.setTotalPrice(product.getPrice());
		orderRepository.save(order);
		return order;
	}
//	public Order createOrder(Long productId, Long orderId, Long customerId, Date date, double totalPrice) {
//		Product product = productRepository.findById(productId).get();
//		Customer customer = customerRepository.findById(customerId).get();
//
//		Order order = new Order(orderId, date, totalPrice, product, customer);
//		Order savedOrder = orderRepository.save(orderId);
//
//		SalesReport salesReport = reportService.generateSalesReport(date, date);
//		salesReport.setTotalSales(salesReport.getTotalSales() + totalPrice);
//		salesReport = reportRepository.save(salesReport);
//
//		return savedOrder;
//	}

	

	public Order createOrder1(List<OrderItem> orderItems) {
		Order order = new Order();
		order.setOrderItems(orderItems);
		return orderRepository.save(order);
	}
//	public List<Order> getOrders() {
//		return orderRepository.findAll();
//	    }
}
