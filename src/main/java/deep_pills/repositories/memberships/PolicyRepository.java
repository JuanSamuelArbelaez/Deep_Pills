package deep_pills.repositories.memberships;

import deep_pills.model.entities.memberships.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {

    @Query("SELECT p FROM Policy p "+
            "WHERE p.cost = :cost "+
            "AND p.maxAppointments = :maxAppointments "+
            "AND p.maxPatients = :maxPatients")
    Optional<Policy> findByCostAndMaxAppointmentsAndMaxPatients(@Param("cost") double cost, @Param("maxAppointments") int maxAppointments, @Param("maxPatients") int maxPatients);
}
