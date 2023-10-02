package deep_pills.dto.accounts.physician;

import jakarta.validation.constraints.NotNull;

public record PhysicianListingItemPatientDTO(
        @NotNull Long physicianId,

        @NotNull String name,

        @NotNull String lastName) {
}
