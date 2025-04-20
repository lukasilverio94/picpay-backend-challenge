package com.picpaysimplified.dtos;

import com.picpaysimplified.domain.user.UserType;

import java.math.BigDecimal;

public record UserDto(
        String firstName,
        String lastName,
        String document,
        BigDecimal balance,
        UserType userType,
        String email,
        String password
) {
}
