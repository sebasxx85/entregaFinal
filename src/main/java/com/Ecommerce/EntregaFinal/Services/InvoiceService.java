package com.Ecommerce.EntregaFinal.Services;

import com.Ecommerce.EntregaFinal.Entities.Invoice;
import com.Ecommerce.EntregaFinal.Repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public Invoice createInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> getInvoiceById(Long id) {
        return invoiceRepository.findById(id);
    }

    public ResponseEntity<Invoice> updateInvoice(Long id, Invoice invoiceDetails) {
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(id);
        if (invoiceOptional.isPresent()) {
            Invoice invoice = invoiceOptional.get();
            invoice.setCreated_at(invoiceDetails.getCreated_at());
            invoice.setTotal(invoiceDetails.getTotal());
            invoice.setClient(invoiceDetails.getClient());
            invoiceRepository.save(invoice);
            return new ResponseEntity<>(invoice, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}