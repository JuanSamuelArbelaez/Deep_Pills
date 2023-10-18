package deep_pills.dto.memberships;

import deep_pills.model.enums.states.PaymentState;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record PaymentDTO(
        @NotNull Long membershipPaymentId,
        @NotNull double amount,
        @NotNull Date dateTime,
        @NotNull String concept,
        @NotNull PaymentState paymentState) {
}
