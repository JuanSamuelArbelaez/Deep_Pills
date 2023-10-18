package deep_pills.dto.memberships;

import deep_pills.model.enums.states.PolicyState;
import jakarta.validation.constraints.NotNull;

public record PolicyDetailsDTO(
        @NotNull Long policyId, // 0L for non-existent Id
        @NotNull String name,
        @NotNull String description,
        @NotNull double cost,
        @NotNull int maxAppointments,
        @NotNull int maxPatients,
        @NotNull PolicyState policyState) {
}
