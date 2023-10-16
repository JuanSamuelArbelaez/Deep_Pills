package deep_pills.dto.claims.patient;

import deep_pills.model.enums.states.ClaimState;
import deep_pills.model.enums.types.ClaimType;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record ClaimDetailedItemPatientDTO(
        @NotNull Long claimId,
        @NotNull String personalId,
        @NotNull Long appointmentId,
        @NotNull Date claimDate,
        @NotNull ClaimType claimType,
        @NotNull String details,
        @NotNull ClaimState claimStatus) {
}
