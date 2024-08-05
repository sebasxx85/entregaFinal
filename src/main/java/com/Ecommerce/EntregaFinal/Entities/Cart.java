package com.Ecommerce.EntregaFinal.Entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data @NoArgsConstructor
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Double price;
    private Integer amount;
    private Boolean delivered;
}