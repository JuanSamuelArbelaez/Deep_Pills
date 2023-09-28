package deep_pills.dto.accounts.physician;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record PhysicianSearchDTO(
        @NotNull @Length(max = 4) Integer searchParameter,

        @NotNull String searchValue) { //None = 0, ID = 1, Name = 2, LastName = 3, Shift = 4
}
