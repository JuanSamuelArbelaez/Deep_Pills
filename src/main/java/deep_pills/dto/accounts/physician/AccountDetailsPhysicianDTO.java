package deep_pills.dto.accounts.physician;

import deep_pills.model.entities.schedule.Shift;
import deep_pills.model.enums.lists.City;
import deep_pills.model.enums.states.AccountState;
import jakarta.validation.constraints.NotNull;

public record AccountDetailsPhysicianDTO(@NotNull Long id,
            @NotNull String personalId,
            @NotNull String name,
            @NotNull String lastName,
            @NotNull String email,
            @NotNull String phone,
            @NotNull Shift shift,
            @NotNull City city,
            @NotNull AccountState state) {
}
