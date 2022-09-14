package com.revature.repositories;
import com.revature.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
}