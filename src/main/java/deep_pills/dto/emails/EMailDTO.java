package deep_pills.dto.emails;

import deep_pills.model.enums.types.EMailType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record EMailDTO(
        @Email String destination,
        @NotNull String body,
        @NotNull String subject,
        @NotNull EMailType emailType,
        @NotNull Long objectId){
}
