package deep_pills.repositories.appointments;

import deep_pills.model.entities.accounts.users.patients.PatientAllergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientAllergyRepository extends JpaRepository<PatientAllergy, Long> {
}
