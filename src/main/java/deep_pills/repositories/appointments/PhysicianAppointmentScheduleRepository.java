package deep_pills.repositories.appointments;

import deep_pills.model.entities.accounts.Account;
import deep_pills.model.entities.schedule.PhysicianAppointmentSchedule;
import deep_pills.model.enums.states.AppointmentState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PhysicianAppointmentScheduleRepository extends JpaRepository<PhysicianAppointmentSchedule, Long> {
    @Query("SELECT count(pas) FROM PhysicianAppointmentSchedule pas "+
            "WHERE pas.physician.personalId = :physicianPersonalId "+
            "AND pas.schedule.scheduleId = :scheduleId "+
            "AND pas.appointment.appointmentState IN :states")
    int countByPhysicianPersonalIdAndDateAndStatus(@Param("physicianPersonalId") String physicianPersonalId, @Param("scheduleId") Long scheduleId, @Param("states") List<AppointmentState> states);

    @Query("SELECT pas FROM PhysicianAppointmentSchedule pas "+
            "WHERE pas.physician.personalId = :physicianPersonalId "+
            "AND pas.schedule.scheduleId = :scheduleId "+
            "AND pas.appointment.appointmentState IN :states")
    List<PhysicianAppointmentSchedule> findByPhysicianPersonalIdAndDateAndStatus(@Param("physicianPersonalId") String physicianPersonalId, @Param("scheduleId") Long scheduleId, @Param("states") List<AppointmentState> states);
}
