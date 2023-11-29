package by.bsuir.phoneshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product extends Entity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String model;
    private String description;
    private BigDecimal price;
    private int stock;
    private String imageUrl;
}