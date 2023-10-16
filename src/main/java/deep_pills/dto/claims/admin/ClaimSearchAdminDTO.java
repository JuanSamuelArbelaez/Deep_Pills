package deep_pills.dto.claims.admin;

import deep_pills.model.enums.states.ClaimState;
import jakarta.validation.constraints.NotNull;

public record ClaimSearchAdminDTO(
        @NotNull Long claimId,
        @NotNull Long adminId,
        @NotNull ClaimState claimState) {
}
