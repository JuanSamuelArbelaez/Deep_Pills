package deep_pills.dto.appointments;

import deep_pills.model.entities.schedule.Schedule;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record AppointmentScheduleDTO(
        @NotNull String patientPersonalId,
        @NotNull String physicianPersonalId,
        @NotNull String reasons,
        @NotNull Long scheduleId,
        @NotNull Date time) {
}
