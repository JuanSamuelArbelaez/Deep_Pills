package deep_pills.services.implementations;

import deep_pills.dto.emails.EMailDTO;
import deep_pills.services.interfaces.EMailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EMailServiceImpl implements EMailService {
    private final JavaMailSender javaMailSender;
    @Override
    public void sendEmail(EMailDTO emailDTO) throws Exception {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setSubject(emailDTO.subject());
        helper.setText(emailDTO.body(), true);
        helper.setTo(emailDTO.destination());
        helper.setFrom("no_reply@deep.pills.com");
        javaMailSender.send(message);
    }
}
