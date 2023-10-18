package deep_pills.dto.accounts.patient;

import deep_pills.model.entities.accounts.users.patients.PatientAllergy;
import deep_pills.model.enums.lists.BloodType;
import deep_pills.model.enums.lists.City;
import deep_pills.model.enums.states.AccountState;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

public record AccountDetailsPatientDTO(
        @NotNull Long id,
        @NotNull String personalId,
        @NotNull String name,
        @NotNull String lastName,
        @NotNull String email,
        @NotNull String phone,
        @NotNull City city, Date dateOfBirth,
        @NotNull BloodType bloodType,
        @NotNull List<PatientAllergy> patientAllergies,
        @NotNull AccountState state) {
}
