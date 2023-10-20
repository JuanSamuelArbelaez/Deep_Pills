package deep_pills.repositories.accounts.users;

import deep_pills.model.entities.accounts.users.patients.Patient;
import deep_pills.model.enums.lists.EPS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("select p from Patient p where p.personalId = :personalId")
    Optional<Patient> findByPersonalId(String personalId);

    @Query("select p from Patient p where p.name = :name")
    List<Patient> findByName(String name);

    @Query("select p from Patient p where p.name = :lastName")
    List<Patient> findByLastName(String lastName);

    @Query("select p from Patient p where p.email = :email")
    Patient findByEmail(String email);

    @Query("select p from Patient p where p.phone = :phone")
    List<Patient> findByPhone(String phone);

    @Query("SELECT p FROM Patient p WHERE p.dateOfBirth = :dateOfBirth")
    List<Patient> findByDateOfBirth(@Param("dateOfBirth") Date dateOfBirth);

    @Query("SELECT p FROM Patient p WHERE p.eps = :eps")
    List<Patient> findByEps(@Param("eps") EPS eps);
}

