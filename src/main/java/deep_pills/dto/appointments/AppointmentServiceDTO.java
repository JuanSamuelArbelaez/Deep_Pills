package deep_pills.dto.appointments;


import deep_pills.model.entities.symptomsTreatmentDiagnosis.TreatmentPlan;
import deep_pills.model.enums.lists.Symptom;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AppointmentServiceDTO(
        @NotNull Long appointmentId,
        @NotNull String physicianPersonalId,
        @NotNull String doctorNotes,
        @NotNull List<Symptom> symptoms,
        @NotNull List<String> treatments) {
}
