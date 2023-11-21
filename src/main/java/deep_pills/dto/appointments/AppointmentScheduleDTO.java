package deep_pills.dto.appointments;

import deep_pills.model.entities.schedule.Schedule;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record AppointmentScheduleDTO(
        @NotNull Long patientId,
        @NotNull Long physicianId,
        @NotNull String reasons,
        @NotNull Long scheduleId,
        @NotNull Date time) {
}
