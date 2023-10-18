package deep_pills.dto.memberships;

import deep_pills.model.enums.states.ChargeState;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record ChargeDTO(
        @NotNull Long membershipChargeId,
        @NotNull Double chargeAmount,
        @NotNull ChargeState chargeState,
        @NotNull Date dateTime) {
}
