package com.revature.repositories;

import com.revature.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {
    @Query(value = "FROM Payment where user_id = :userId")
    Optional<List<Payment>> findCardsByUser(int userId);
}
