package deep_pills.repositories.EMails;

import deep_pills.model.entities.notifications.EMail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EMailsRepository extends JpaRepository<EMail, Long> {
}
