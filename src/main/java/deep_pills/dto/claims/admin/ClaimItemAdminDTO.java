package deep_pills.dto.claims.admin;

import deep_pills.model.enums.states.ClaimState;
import deep_pills.model.enums.types.ClaimType;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record ClaimItemAdminDTO(
        @NotNull
        Long id,

        @NotNull Date date,

        @NotNull
        ClaimType type,

        @NotNull
        ClaimState status) {
}
