package deep_pills.dto.appointments;

import jakarta.validation.constraints.NotNull;

public record AppointmentPatientDTO(
        @NotNull Long appointmentId,
        @NotNull String personalId,
        @NotNull java.util.Date date,
        @NotNull java.util.Date time,
        @NotNull Long duration,
        @NotNull deep_pills.model.enums.states.AppointmentState appointmentState) {
}
