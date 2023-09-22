package deep_pills.services.concreteServices;

import deep_pills.dto.Emails.EMailDTO;

public interface EMailService {
    String sendEmail(EMailDTO emailDTO) throws Exception;
}
