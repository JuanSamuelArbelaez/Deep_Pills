package deep_pills.dto.appointments;

import deep_pills.model.enums.lists.Specialization;

public record AppointmentSpecializationSearchDTO(
        Long patientId,
        String specialization) {
}
