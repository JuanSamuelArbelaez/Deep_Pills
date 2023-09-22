package deep_pills.dto.Emails;

import deep_pills.model.entities.accounts.Account;
import deep_pills.model.enums.states.EmailState;

public record EMailDTO(
        Account origin,
        Account destination,
        String message,
        String topic,
        EmailState Status){
}
