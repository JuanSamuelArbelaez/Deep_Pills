package deep_pills.services.implementations;

import deep_pills.dto.Emails.EMailDTO;
import deep_pills.services.interfaces.EMailService;
import org.springframework.stereotype.Service;

@Service
public class EMailServiceImpl implements EMailService {
    @Override
    public String sendEmail(EMailDTO emailDTO) throws Exception {
        return null;
    }
}
