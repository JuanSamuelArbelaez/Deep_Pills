package deep_pills.dto.memberships;

import jakarta.validation.constraints.NotNull;

public record MembershipPaymentDTO(
        @NotNull String patientPersonalId,
        @NotNull Long membershipChargeId,
        @NotNull double amount) {
}
