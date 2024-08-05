package com.Ecommerce.EntregaFinal.Controllers;

import com.Ecommerce.EntregaFinal.Entities.Client;
import com.Ecommerce.EntregaFinal.Services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Operation(summary = "Register a new client", description = "Register a new client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client registered successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping("/register")
    public ResponseEntity<Client> registerClient(@RequestBody Client client) {
        Client registeredClient = clientService.registerClient(client);
        return ResponseEntity.status(201).body(registeredClient);
    }

    @Operation(summary = "Update client profile", description = "Update client profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client profile updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class))),
            @ApiResponse(responseCode = "404", description = "Client not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping("/me")
    public ResponseEntity<Client> updateClient(@RequestBody Client clientDetails) {
        return clientService.updateClient(clientDetails.getId(), clientDetails);
    }
}