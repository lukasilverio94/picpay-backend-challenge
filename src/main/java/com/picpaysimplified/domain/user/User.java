package com.picpaysimplified.domain.user;

import com.picpaysimplified.dtos.UserDto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String email;

    private String password;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    //  Constructor
    public User(UserDto dto) {
        this.firstName = dto.firstName();
        this.lastName = dto.lastName();
        this.email = dto.email();
        this.document = dto.document();
        this.password = dto.password();
        this.userType = dto.userType();
        this.balance = dto.balance();
    }
}
