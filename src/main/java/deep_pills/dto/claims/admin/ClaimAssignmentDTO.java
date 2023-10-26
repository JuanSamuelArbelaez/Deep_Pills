package deep_pills.dto.claims.admin;

import jakarta.validation.constraints.NotNull;

public record ClaimAssignmentDTO (
        @NotNull Long claimId,
        @NotNull Long adminId){
}
