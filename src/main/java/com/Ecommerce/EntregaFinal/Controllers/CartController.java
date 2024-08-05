package com.Ecommerce.EntregaFinal.Controllers;

import com.Ecommerce.EntregaFinal.Entities.Cart;
import com.Ecommerce.EntregaFinal.Services.CartService;
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
@RequestMapping("/api/v1/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @Operation(summary = "Add product to cart", description = "Adds a product to the client's cart with the specified quantity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product added to cart successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cart.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Client or Product not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping("/{clid}/{pid}/{quantity}")
    public ResponseEntity<Cart> addProductToCart(@PathVariable Long clid, @PathVariable Long pid, @PathVariable Integer quantity) {
        Cart cart = cartService.addProductToCart(clid, pid, quantity);
        return ResponseEntity.status(201).body(cart);
    }

    @Operation(summary = "Get carts by client ID", description = "Find carts by client ID and delivered status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carts retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cart.class))),
            @ApiResponse(responseCode = "404", description = "Client not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/{clid}")
    public ResponseEntity<List<Cart>> getCartsByClientId(@PathVariable Long clid) {
        List<Cart> carts = cartService.getCartsByClientId(clid);
        return ResponseEntity.ok(carts);
    }

    @Operation(summary = "Remove product from cart", description = "Remove product from cart by cart ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product removed from cart successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Cart not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> removeProductFromCart(@PathVariable Long cartId) {
        cartService.removeProductFromCart(cartId);
        return ResponseEntity.noContent().build();
    }
}