package com.picpaysimplified.services;

import com.picpaysimplified.domain.user.User;
import com.picpaysimplified.domain.user.UserType;
import com.picpaysimplified.dtos.UserDto;
import com.picpaysimplified.exceptions.UserNotFoundException;
import com.picpaysimplified.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserType() == UserType.MERCHANT){
            throw new Exception("UserType Merchant is not allowed to make transactions, only receive it");
        }

        if(sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Not enough balance :(");

        }
    }

    public User findUserById(Long id) throws RuntimeException {
        return this.repository.findUserById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User createUser(UserDto dto) {
        User newUser = new User(dto);
        this.saveUser(newUser);
        return newUser;
    }

    public void saveUser(User user){
        this.repository.save(user);
    }

    public List<User> getAllUsers(){
        return this.repository.findAll();
    }
}
