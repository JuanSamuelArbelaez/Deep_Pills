package deep_pills.controllers;

import deep_pills.dto.CreateSubscriptionRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscriptionController {

    @PostMapping("/create-subscription")
    public ResponseEntity<String> createSubscription(@RequestBody CreateSubscriptionRequest request) {
        // Call your service to create the subscription using the provided token
        // Return a response indicating the success or failure of the operation
        return null;
    }
}
