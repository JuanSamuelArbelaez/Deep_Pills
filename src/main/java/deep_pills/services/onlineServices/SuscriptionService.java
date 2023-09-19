package deep_pills.services.onlineServices;

import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.Flow;
import com.stripe.Stripe;

@Service
public class SubscriptionService {

    //@Autowired
    private Stripe stripe;

    public void createSubscription(String token) throws StripeException {
        Map<String, Object> params = new HashMap<>();
        params.put("customer", token);
        params.put("items", Arrays.asList(new MapBuilder<String, Object>()
                .put("plan", "your_plan_id")
                .build()));

        Flow.Subscription subscription = Subscription.create(params);
    }
}