package com.Ecommerce.EntregaFinal.Entities;

import jakarta.persistence.Entity;
import lombok.Data;


@Entity
@Data
public class Client {

    private Long id;
    private String nombre;

}
