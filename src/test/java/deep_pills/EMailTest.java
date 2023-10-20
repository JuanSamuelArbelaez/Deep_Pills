package deep_pills;

import deep_pills.dto.emails.EMailDTO;
import deep_pills.model.enums.types.EMailType;
import deep_pills.services.interfaces.EMailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EMailTest {
    @Autowired private EMailService  eMailService;

    @Test public void sendEmail(){
        try{
            String destination = "juane.astaizaf@uqvirtual.edu.co";
            String body = "Hello dummy email body. See you later.";
            String subject = "Hi";
            EMailType eType = EMailType.ACCOUNT_STATE_UPDATED;
            Long id = 502L;
            eMailService.sendEmail(
                    new EMailDTO(
                            destination,
                            body,
                            subject,
                            eType,
                            id
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
