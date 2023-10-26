package deep_pills.dto.claims.admin;

import deep_pills.model.enums.states.ClaimState;
import jakarta.validation.constraints.NotNull;

public record ClaimStateDTO(
        @NotNull Long claimId,
        @NotNull ClaimState claimState) {
}
