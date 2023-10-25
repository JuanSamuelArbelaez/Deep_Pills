package deep_pills.dto.appointments;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record AppointmentDateSearchDTO(
        @NotNull String patientPersonalId,
        @NotNull Date date) {
}
