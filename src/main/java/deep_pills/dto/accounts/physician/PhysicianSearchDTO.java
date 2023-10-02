package deep_pills.dto.accounts.physician;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record PhysicianSearchDTO(
        @NotNull @Min(0) @Max(6) Integer searchParameter,

        @NotNull String searchValue) {
    // None = 0,
    // ID = 1,
    // Personal ID = 2,
    // Name = 3,
    // LastName = 4,
    // Shift = 5,
    // Specialization.name = 6,
    // City = 7
}
