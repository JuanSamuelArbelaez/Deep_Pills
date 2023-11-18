package deep_pills.dto.claims.patient;

import deep_pills.model.enums.types.MessageType;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record ClaimMessageDTO (
        @NotNull Long messageId,
        @NotNull String message,
        @NotNull Date date,
        @NotNull Long senderId,
        @NotNull Long recipientId,
        @NotNull MessageType messageType){
}
