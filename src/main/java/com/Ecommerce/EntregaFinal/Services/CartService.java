package com.Ecommerce.EntregaFinal.Services;

import com.Ecommerce.EntregaFinal.Entities.Cart;
import com.Ecommerce.EntregaFinal.Repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Optional<Cart> getCartById(Long id) {
        return cartRepository.findById(id);
    }

    public ResponseEntity<Cart> updateCart(Long id, Cart cartDetails) {
        Optional<Cart> cartOptional = cartRepository.findById(id);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            cart.setPrice(cartDetails.getPrice());
            cart.setAmount(cartDetails.getAmount());
            cart.setDelivered(cartDetails.getDelivered());
            cart.setClient(cartDetails.getClient());
            cart.setProduct(cartDetails.getProduct());
            cartRepository.save(cart);
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}