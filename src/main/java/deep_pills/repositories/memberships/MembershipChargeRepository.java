package deep_pills.repositories.memberships;

import deep_pills.model.entities.memberships.MembershipCharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipChargeRepository extends JpaRepository<MembershipCharge, Long> {
}
