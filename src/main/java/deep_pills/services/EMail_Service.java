package deep_pills.services;

import deep_pills.dto.Emails.EMail_DTO;

public interface EMail_Service {
    String sendEmail(EMail_DTO emailDTO) throws Exception;
}
