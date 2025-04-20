package com.picpaysimplified.services;

import com.picpaysimplified.domain.transaction.Transaction;
import com.picpaysimplified.domain.user.User;
import com.picpaysimplified.dtos.TransactionDto;
import com.picpaysimplified.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final UserService userService;
    private final TransactionRepository repository;

    // make http requests  with RestTemplate
    private final RestTemplate restTemplate;

    private final NotificationService notificationService;

    public Transaction createTransaction(TransactionDto dto) throws Exception {
        User sender = this.userService.findUserById(dto.senderId());
        User receiver = this.userService.findUserById(dto.receiverId());

        userService.validateTransaction(sender, dto.value());

        boolean isAuthorized = authorizeTransaction(sender, dto.value());
        if (!isAuthorized) {
            throw new Exception("Transaction not authorized");
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(dto.value());
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setTimestamp(LocalDateTime.now());

        // update balances (for sender and receiver)
        sender.setBalance(sender.getBalance().subtract(dto.value()));
        receiver.setBalance(receiver.getBalance().add(dto.value()));

        this.repository.save(transaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        this.notificationService.sendNotification(sender, "Transaction sent successfully");

        this.notificationService.sendNotification(receiver, "Transaction received successfully");
        return transaction;
    }

    public boolean authorizeTransaction(User sender, BigDecimal value) {
        return true;
//        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity(
//                "https://util.devi.tools/api/v2/authorize",
//                Map.class);
//
////
//        if (authorizationResponse.getStatusCode() == HttpStatus.OK) {
//            assert authorizationResponse.getBody() != null;
//            String message = (String) authorizationResponse.getBody().get("status");
//            return "success".equalsIgnoreCase(message);
//        } else return false;
    }
}
