package deep_pills.dto.claims.admin;

import deep_pills.model.enums.states.ClaimState;
import deep_pills.model.enums.types.ClaimType;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record ClaimDetailedItemAdminDTO(
        @NotNull
        Long claimId,

        @NotNull
        String patientPersonalId,

        @NotNull
        Long adminId,

        @NotNull
        Long appointmentId,

        @NotNull
        Date date,

        @NotNull
        ClaimType claimType,

        @NotNull
        String details,

        @NotNull
        ClaimState claimStatus) {
}
