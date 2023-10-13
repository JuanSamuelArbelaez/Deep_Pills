package deep_pills.repositories.appointments;

import deep_pills.model.entities.appointments.AppointmentSymptoms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentSymptomRepository extends JpaRepository<AppointmentSymptoms, Long> {
}
