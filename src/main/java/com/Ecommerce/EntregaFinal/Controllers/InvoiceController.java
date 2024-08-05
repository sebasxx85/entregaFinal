package com.Ecommerce.EntregaFinal.Controllers;

import com.Ecommerce.EntregaFinal.Entities.Invoice;
import com.Ecommerce.EntregaFinal.Services.InvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Operation(summary = "Generate an invoice", description = "Generate a new invoice")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Invoice generated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Invoice.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        Invoice createdInvoice = invoiceService.createInvoice(invoice);
        return ResponseEntity.status(201).body(createdInvoice);
    }

    @Operation(summary = "Get invoices by client ID", description = "Get all invoices by client ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Invoices retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Invoice.class))),
            @ApiResponse(responseCode = "404", description = "Client not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/{clientId}")
    public ResponseEntity<List<Invoice>> getInvoicesByClientId(@PathVariable Long clientId) {
        List<Invoice> invoices = invoiceService.getInvoicesByClientId(clientId);
        return ResponseEntity.ok(invoices);
    }
}