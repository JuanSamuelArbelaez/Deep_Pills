package deep_pills.dto.appointments;

import deep_pills.model.enums.lists.City;
import deep_pills.model.enums.lists.Specialization;
import deep_pills.model.enums.lists.Symptom;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AppointmentSearchDTO(
        Long physicianId,
        Specialization specialization,
        City city) {
}
