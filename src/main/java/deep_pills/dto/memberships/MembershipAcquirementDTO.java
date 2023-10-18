package deep_pills.dto.memberships;

import jakarta.validation.constraints.NotNull;

public record MembershipAcquirementDTO(
        @NotNull String patientPersonalId,
        @NotNull Long policyId
) {
}
