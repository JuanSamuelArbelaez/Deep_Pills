package deep_pills.dto.memberships;

import deep_pills.model.enums.states.PolicyState;
import jakarta.validation.constraints.NotNull;

public record PolicyStateDTO(
        @NotNull Long policyId,
        @NotNull PolicyState policyState) {
}
