package deep_pills.repositories.accounts.users;

import deep_pills.model.entities.accounts.users.patients.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("select p from Patient p where p.personalId = :personalId")
    Patient searchByPersonalId(String personalId);
}
