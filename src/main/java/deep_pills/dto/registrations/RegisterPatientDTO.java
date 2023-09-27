package deep_pills.dto.registrations;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record RegisterPatientDTO(
        @NotNull @Length(max = 25) @Email
        String email,

        @NotNull @Length(min = 6, max = 20)
        String password,

        @NotNull @Length(max = 15)
        String personalId) {
}
