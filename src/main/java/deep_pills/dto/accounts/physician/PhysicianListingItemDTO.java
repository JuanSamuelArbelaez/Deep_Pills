package deep_pills.dto.accounts.physician;

import jakarta.validation.constraints.NotNull;

public record PhysicianListingItemDTO(
        @NotNull Long physicianId,

        @NotNull String personalId,

        @NotNull String name,

        @NotNull String lastName,

        @NotNull Long shiftId) {
}
