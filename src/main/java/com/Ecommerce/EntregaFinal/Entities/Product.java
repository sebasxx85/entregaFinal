package com.Ecommerce.EntregaFinal.Entities;

import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import lombok.*;

import java.util.List;


@Entity
@Data @NoArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Integer stock;
    private Double price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> carts;
}
