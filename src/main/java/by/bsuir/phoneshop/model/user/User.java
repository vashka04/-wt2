package by.bsuir.phoneshop.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String login;
    private String hashPassword;
    private UserRole role;
    private double discount;
    private UserStatus status;
}
