package deep_pills.services;

import deep_pills.dto.EMailDTO;

public interface EMail_Service {
    String sendEmail(EMailDTO emailDTO) throws Exception;
}
