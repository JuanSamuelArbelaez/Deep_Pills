package deep_pills.dto.appointments;

import deep_pills.model.enums.lists.Diagnosis;
import jakarta.validation.constraints.NotNull;

public record AppointmentTreatmentPlanDTO(
        @NotNull String treatment,
        @NotNull Diagnosis diagnosis) {
}
