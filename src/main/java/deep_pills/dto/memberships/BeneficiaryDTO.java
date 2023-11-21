package deep_pills.dto.memberships;

import jakarta.validation.constraints.NotNull;

public record BeneficiaryDTO(
        @NotNull String beneficiaryPersonalId,
        @NotNull String name
) {
}
