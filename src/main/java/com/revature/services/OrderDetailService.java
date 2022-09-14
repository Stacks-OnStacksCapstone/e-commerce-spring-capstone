package com.revature.services;
import com.revature.models.OrderDetail;
import com.revature.repositories.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderDetailService(OrderDetailRepository orderDetailRepository){
        this.orderDetailRepository=orderDetailRepository;
    }

    public List<OrderDetail> findAll(){
        return orderDetailRepository.findAll();
    }

    public Optional<OrderDetail> findById(int id) {
        return findById(id);
    }
    public OrderDetail save(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }
    public boolean delete(int id) {
        OrderDetail foundOrderDetail = orderDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("OrderDetail couldn't be deleted."));
        orderDetailRepository.delete(foundOrderDetail);
        return true;
    }

}
