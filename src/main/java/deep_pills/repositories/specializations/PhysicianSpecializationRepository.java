package deep_pills.repositories.specializations;

import deep_pills.model.entities.accounts.users.physicians.PhysicianSpecialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicianSpecializationRepository extends JpaRepository<PhysicianSpecialization, Long> {

}
