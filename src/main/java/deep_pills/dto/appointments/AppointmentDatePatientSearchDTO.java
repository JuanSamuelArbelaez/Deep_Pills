package deep_pills.dto.appointments;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record AppointmentDatePatientSearchDTO(
        @NotNull String patientPersonalId,
        @NotNull Date date) {
}
