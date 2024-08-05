package com.Ecommerce.EntregaFinal.Repositories;

import com.Ecommerce.EntregaFinal.Entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByClientId(Long clientId);

}
