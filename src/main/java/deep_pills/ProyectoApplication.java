package deep_pills;
import com.stripe.Stripe;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class ProyectoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProyectoApplication.class, args);
        Stripe.apiKey = "sk_test_51Ns6bMAy053Gk3OXYhO8XaGA5ELvF9mY01vRv06nUqZV0CFBogrrjDTbcg5UFeEhaqBkcO7Bytl0OL2dWrVdzgqi00tWtmpuz5";
    }

}
