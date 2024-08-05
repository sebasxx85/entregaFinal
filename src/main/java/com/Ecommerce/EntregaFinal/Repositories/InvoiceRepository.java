package com.Ecommerce.EntregaFinal.Repositories;

import com.Ecommerce.EntregaFinal.Entities.Cart;
import com.Ecommerce.EntregaFinal.Entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByClientId(Long clientId);
}
