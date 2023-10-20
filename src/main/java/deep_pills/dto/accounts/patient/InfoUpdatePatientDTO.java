package deep_pills.dto.accounts.patient;

import deep_pills.model.enums.lists.BloodType;
import deep_pills.model.enums.lists.City;
import deep_pills.model.enums.lists.EPS;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record InfoUpdatePatientDTO(
        @NotNull Long id,
        String name,
        String lastName,
        Date dateOfBirth,
        String phone,
        @Email String email,
        City city,
        String pictureURL,
        BloodType bloodType,
        EPS eps){
}
