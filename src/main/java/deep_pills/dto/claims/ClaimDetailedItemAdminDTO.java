package deep_pills.dto.claims;

import deep_pills.model.enums.states.ClaimState;

import java.util.Date;

public record ClaimDetailedItemAdminDTO(
        Long claimId,
        Long patientId,
        Long adminId,
        Long appointmentId,
        Date date,
        ClaimState status,
        String notes) {
}
