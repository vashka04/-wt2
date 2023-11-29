package by.bsuir.phoneshop.model.cart;

import by.bsuir.phoneshop.model.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    private List<CartItem> items = new ArrayList<>();
    private int totalQuantity;
    private BigDecimal totalCost;
}
