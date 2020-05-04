package br.jss.warehouse.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity(name = "product")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Short id;

    @NotNull(message = "The products'name cannot be null.")
    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @Min(value = 0, message = "The quantity of products cannot be less than 0.")
    @Column(nullable = false)
    private Short amount;

    @Min(value = 0, message = "The product's price cannot be less than 0.")
    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal price;

    @Column
    private LocalDate validateDate;
}
