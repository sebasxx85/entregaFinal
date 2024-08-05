package com.Ecommerce.EntregaFinal.Controllers;

import com.Ecommerce.EntregaFinal.Entities.Client;
import com.Ecommerce.EntregaFinal.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/register")
    public ResponseEntity<Client> registerClient(@RequestBody Client client) {
        Client registeredClient = clientService.registerClient(client);
        return ResponseEntity.ok(registeredClient);
    }

    @PutMapping("/me")
    public ResponseEntity<Client> updateClient(@RequestBody Client clientDetails) {
        return clientService.updateClient(clientDetails.getId(), clientDetails);
    }
}