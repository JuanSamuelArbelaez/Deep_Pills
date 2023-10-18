package deep_pills.dto.memberships;

import jakarta.validation.constraints.NotNull;

public record ChargeListDTO(
        @NotNull String patientPersonalId,
        @NotNull Long membershipId) {
}
