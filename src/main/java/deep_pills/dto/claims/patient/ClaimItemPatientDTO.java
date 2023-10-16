package deep_pills.dto.claims.patient;

import deep_pills.model.enums.states.ClaimState;
import deep_pills.model.enums.types.ClaimType;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record ClaimItemPatientDTO(
        @NotNull Long claimId,
        @NotNull Date claimDate,
        @NotNull ClaimType claimType,
        @NotNull ClaimState claimStatus) {
}
