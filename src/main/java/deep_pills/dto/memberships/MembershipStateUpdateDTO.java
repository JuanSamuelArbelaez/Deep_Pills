package deep_pills.dto.memberships;

import deep_pills.model.enums.states.ChargeState;
import deep_pills.model.enums.states.MembershipState;
import jakarta.validation.constraints.NotNull;

public record MembershipStateUpdateDTO(
        @NotNull Long adminId,
        @NotNull Long membershipId,
        @NotNull MembershipState state) {
}
