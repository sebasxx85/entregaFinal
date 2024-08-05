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

    public Cart addProductToCart(Long clid, Long pid, Integer quantity) {
        Cart cart = new Cart();
        // Aquí deberías agregar la lógica para asociar el cliente y el producto al carrito
        cart.setAmount(quantity);
        // Setear otros atributos necesarios
        return cartRepository.save(cart);
    }

    public List<Cart> getCartsByClientId(Long clid) {
        return cartRepository.findByClientId(clid);
    }

    public ResponseEntity<Void> removeProductFromCart(Long cartId) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if (cartOptional.isPresent()) {
            cartRepository.delete(cartOptional.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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