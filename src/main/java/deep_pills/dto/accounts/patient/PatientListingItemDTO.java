package deep_pills.dto.accounts.patient;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record PatientListingItemDTO(
        @NotNull Long patientID,

        @NotNull String personalID,

        @NotNull String name,

        @NotNull String LastName,

        @Email String email) {
}
