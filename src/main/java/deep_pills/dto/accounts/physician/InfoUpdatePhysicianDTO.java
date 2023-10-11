package deep_pills.dto.accounts.physician;

import deep_pills.model.enums.lists.City;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record InfoUpdatePhysicianDTO(
    @NotNull Long id,
    String name,
    String lastName,
    String phone,
    @Email String email,
    City city,
    String pictureURL){
}
