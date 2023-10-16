package deep_pills.dto.claims.admin;

import deep_pills.model.enums.types.MessageType;
import jakarta.validation.constraints.NotNull;

public record ClaimAnswerDTO(
        @NotNull Long claimId,

        @NotNull String text,

        @NotNull MessageType messageType

) {

}
