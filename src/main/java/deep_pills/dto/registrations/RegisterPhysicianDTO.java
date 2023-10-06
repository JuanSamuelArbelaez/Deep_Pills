package deep_pills.dto.registrations;

import deep_pills.dto.schedule.ShiftDTO;
import deep_pills.model.enums.lists.Specialization;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record RegisterPhysicianDTO(
        @NotNull
        String name,

        @NotNull
        String lastName,

        @Email @Length(max = 50)
        String email,

        @NotNull
        String password,

        @NotNull
        String personalId,

        @NotNull
        Long adminId,

        @NotNull
        List<Specialization> specializations,

        @NotNull
        ShiftDTO shift) {
}
