package deep_pills.services.interfaces;

import deep_pills.dto.emails.EMailDTO;

public interface EMailService {
    String sendEmail(EMailDTO emailDTO) throws Exception;
}
