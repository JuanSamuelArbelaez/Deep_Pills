package deep_pills.dto.appointments;

import deep_pills.model.entities.schedule.Schedule;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record AppointmentRescheduleDTO(
        @NotNull Long appointmentId,
        @NotNull Long scheduleId,
        @NotNull String patientPersonalId,
        @NotNull String physicianPersonalId,
        @NotNull Date time) {
}
