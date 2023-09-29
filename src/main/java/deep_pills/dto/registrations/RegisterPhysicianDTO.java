package deep_pills.dto.registrations;

import deep_pills.dto.schedule.ShiftDTO;
import deep_pills.model.enums.lists.Specialization;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RegisterPhysicianDTO(
        @NotNull
        String name,

        @NotNull
        String lastName,

        @NotNull
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
