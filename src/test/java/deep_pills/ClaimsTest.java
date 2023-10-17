package deep_pills;

import deep_pills.dto.claims.patient.ClaimRegisterDTO;
import deep_pills.model.enums.types.ClaimType;
import deep_pills.services.interfaces.ClaimsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClaimsTest {
    @Autowired ClaimsService claimsService;

    @Test public void newClaim(){
        try {
            System.out.println(claimsService.newClaim(new ClaimRegisterDTO(
                    "171.018.271",
                    152L,
                    ClaimType.COMPLAINT,
                    "Initial Message: The doctor's head nurse was a little bit rude."
            )));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
