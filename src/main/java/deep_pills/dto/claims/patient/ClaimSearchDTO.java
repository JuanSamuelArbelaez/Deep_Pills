package deep_pills.dto.claims.patient;

import jakarta.validation.constraints.NotNull;

public record ClaimSearchDTO(
        @NotNull Long claimId,
        @NotNull String patientPersonalId) {
}
