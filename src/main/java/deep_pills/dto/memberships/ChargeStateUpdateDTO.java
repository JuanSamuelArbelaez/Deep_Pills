package deep_pills.dto.memberships;

import deep_pills.model.enums.states.ChargeState;
import jakarta.validation.constraints.NotNull;

public record ChargeStateUpdateDTO(
        @NotNull Long adminId,
        @NotNull Long membershipChargeId,
        @NotNull ChargeState state) {
}
