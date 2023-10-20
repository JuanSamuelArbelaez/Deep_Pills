package deep_pills.repositories;

import deep_pills.model.entities.passwordRecovery.PasswordRecoveryRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRecoveryRequestRepository extends JpaRepository<PasswordRecoveryRequest, Long> {
}
