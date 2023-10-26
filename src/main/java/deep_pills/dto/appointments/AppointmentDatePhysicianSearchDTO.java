package deep_pills.dto.appointments;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record AppointmentDatePhysicianSearchDTO(
        @NotNull String physicianPersonalId,
        @NotNull Date date) {
}
