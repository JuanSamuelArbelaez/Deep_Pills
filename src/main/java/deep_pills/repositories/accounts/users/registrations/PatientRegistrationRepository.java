package deep_pills.repositories.accounts.users.registrations;

import deep_pills.model.entities.accounts.users.patients.PatientRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRegistrationRepository extends JpaRepository<PatientRegistration, Long> {

}
