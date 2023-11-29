package by.bsuir.phoneshop.model.order;

import by.bsuir.phoneshop.model.cart.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;
    private List<CartItem> items;
    private String secureId;
    private BigDecimal totalCost;
    private String firstName;
    private String lastName;
    private String phone;
    private String deliveryAddress;
}
