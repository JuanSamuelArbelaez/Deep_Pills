package deep_pills.dto.emails;

import deep_pills.model.entities.accounts.Account;
import deep_pills.model.enums.states.EmailState;
import jakarta.validation.constraints.NotNull;

public record EMailDTO(
        @NotNull Account origin,
        @NotNull Account destination,
        @NotNull String message,
        @NotNull String topic,
        @NotNull EmailState Status){
}
