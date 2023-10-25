package deep_pills.dto.claims.patient;

import deep_pills.model.enums.states.ClaimState;
import jakarta.validation.constraints.NotNull;

public record ClaimListingPatientDTO(
        @NotNull String patientPersonalId,
        @NotNull ClaimState claimState) {
}
