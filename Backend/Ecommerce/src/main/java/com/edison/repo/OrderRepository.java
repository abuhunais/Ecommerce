package com.edison.repo;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edison.model.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	List<Order> findByOrderDateBetween(Date startDate, Date endDate);
	//double totalSales = orders.stream().mapToDouble(order -> order.getTotalAmount()).sum();


}
