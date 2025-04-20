package com.picpaysimplified.services;

import com.picpaysimplified.domain.user.User;
import com.picpaysimplified.dtos.NotificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationDto notificationRequest = new NotificationDto(email, message);

//        ResponseEntity<String> notificationResponse = restTemplate.postForEntity(
//                "https://util.devi.tools/api/v1/notify",
//                notificationRequest,
//                String.class);
//
//        if (!(notificationResponse.getStatusCode() == HttpStatus.OK)) {
//            System.out.println("Error when try to send notification");
//            throw new Exception("Notification service is out at the moment, try again later");
//        }
        System.out.println("Notification sent to the user");
    }
}
