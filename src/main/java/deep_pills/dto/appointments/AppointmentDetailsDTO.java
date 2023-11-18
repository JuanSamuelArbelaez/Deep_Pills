package deep_pills.dto.appointments;

import deep_pills.dto.accounts.physician.PhysicianListingItemPatientDTO;
import deep_pills.dto.claims.patient.ClaimItemPatientDTO;
import deep_pills.model.enums.lists.Symptom;
import deep_pills.model.enums.states.AppointmentState;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

public record AppointmentDetailsDTO(
        @NotNull Long appointmentId,

        @NotNull String patientPersonalId,

        @NotNull Date date,

        @NotNull Date time,

        @NotNull String location,

        @NotNull Long duration,

        @NotNull Date requestTime,

        @NotNull String detailedReasons,

        @NotNull String doctorsNotes,

        @NotNull AppointmentState appointmentState,

        @NotNull List<ClaimItemPatientDTO> claims,

        @NotNull List<AppointmentTreatmentPlanDTO> treatments,

        @NotNull List<Long> emailsIds,

        @NotNull PhysicianListingItemPatientDTO physicianInfo,

        @NotNull List<Symptom> symptoms) {
}
