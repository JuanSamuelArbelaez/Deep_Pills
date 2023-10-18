package deep_pills.dto.memberships;

import jakarta.validation.constraints.NotNull;

public record MembershipPatientModificationDTO(
        @NotNull Long membershipId,
        @NotNull String ownerPersonalId,
        @NotNull String patientPersonalId) {
}
