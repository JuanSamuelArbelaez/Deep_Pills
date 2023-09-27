package deep_pills.dto.claims.admin;

import deep_pills.model.enums.states.ClaimState;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record ClaimDetailedItemAdminDTO(
        @NotNull
        Long claimId,

        @NotNull
        Long patientId,

        @NotNull
        Long adminId,

        @NotNull
        Long appointmentId,

        @NotNull
        Date date,

        @NotNull
        ClaimState status,

        @NotNull
        String notes) {
}
