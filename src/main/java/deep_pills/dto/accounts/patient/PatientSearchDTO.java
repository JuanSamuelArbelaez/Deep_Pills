package deep_pills.dto.accounts.patient;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record PatientSearchDTO(
        @NotNull @Min(0) @Max(9) Integer searchParameter,

        @NotNull String searchValue) {

    // none = 0,
    // ID = 1,
    // PersonalID = 2,
    // Name = 3,
    // LastName = 4,
    // Email = 5,
    // Phone = 6,
    // DateOfBirth = 7,
    // Membership = 8
    // EPS = 9
}
