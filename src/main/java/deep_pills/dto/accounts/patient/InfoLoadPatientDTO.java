package deep_pills.dto.accounts.patient;

import deep_pills.model.enums.lists.Allergy;
import deep_pills.model.enums.lists.BloodType;
import deep_pills.model.enums.lists.City;
import deep_pills.model.enums.lists.EPS;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public record InfoLoadPatientDTO(
        @NotNull Long id,
        String patientPersonalId,
        String name,
        String lastName,
        Date dateOfBirth,
        String phone,
        @Email String email,
        City city,
        String pictureUrl,
        BloodType bloodType,
        EPS eps,
        List<Allergy> allergies){
}
