package deep_pills.repositories.appointments;

import deep_pills.model.entities.accounts.Account;
import deep_pills.model.entities.schedule.PhysicianAppointmentSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicianAppointmentScheduleRepository extends JpaRepository<PhysicianAppointmentSchedule, Long> {

}
