package com.naman.SweetShop.repo;

import com.naman.SweetShop.model.Order;
import com.naman.SweetShop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order , Long> {
    List<Order> findByUser(User user);
}
