package deep_pills.dto.claims.patient;

import deep_pills.model.enums.types.ClaimType;
import jakarta.validation.constraints.NotNull;

public record ClaimRegisterDTO(
        @NotNull String patientPersonalId,
        @NotNull Long appointmentId,
        @NotNull ClaimType claimType,
        @NotNull String details) {
}
