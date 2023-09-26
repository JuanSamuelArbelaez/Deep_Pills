package deep_pills.repositories.accounts.users;

import deep_pills.model.entities.accounts.users.physicians.Physician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicianRepository extends JpaRepository<Physician, Long> {
    @Query("select ph from Physician ph where ph.personalId = :personalId")
    Physician searchByPersonalId(String personalId);
}
