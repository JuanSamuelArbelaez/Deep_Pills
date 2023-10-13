package deep_pills.repositories.appointments;

import deep_pills.model.entities.accounts.users.patients.Patient;
import deep_pills.model.entities.appointments.Appointment;
import deep_pills.model.entities.schedule.PhysicianAppointmentSchedule;
import deep_pills.model.enums.states.AppointmentState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("SELECT pas FROM PhysicianAppointmentSchedule pas "+
            "WHERE pas.physician.id = :physicianId")
    List<PhysicianAppointmentSchedule> findByPhysician_Id(@Param("physicianId") Long physicianId);

    @Query("SELECT pas FROM PhysicianAppointmentSchedule pas " +
            "WHERE pas.physician.id = :physicianId " +
            "AND pas.appointment.date > CURRENT_DATE")
    List<PhysicianAppointmentSchedule> findUpcomingAppointmentsForPhysician(@Param("physicianId") Long physicianId);

    @Query("SELECT pas FROM PhysicianAppointmentSchedule pas " +
            "WHERE pas.physician.id = :physicianId " +
            "AND pas.appointment.date <= CURRENT_DATE")
    List<PhysicianAppointmentSchedule> findPastAppointmentsForPhysician(@Param("physicianId") Long physicianId);

    @Query("SELECT pas FROM PhysicianAppointmentSchedule pas " +
            "WHERE pas.physician.id = :physicianId " +
            "AND pas.appointment.date = :date")
    List<PhysicianAppointmentSchedule> findAppointmentsForPhysicianOnDate(@Param("physicianId") Long physicianId, @Param("date") Date date);

    @Query("SELECT pas FROM PhysicianAppointmentSchedule pas " +
            "WHERE pas.appointment.patient.id = :patientId")
    List<PhysicianAppointmentSchedule> findByPatient_Id(@Param("patientId") Long patientId);

    @Query("SELECT pas FROM PhysicianAppointmentSchedule pas " +
            "WHERE pas.appointment.patient.id = :patientId "+
            "AND pas.appointment.date > CURRENT_DATE")
    List<PhysicianAppointmentSchedule> findUpcomingAppointmentsForPatient(@Param("patientId")Long patientId);

    @Query("SELECT pas FROM PhysicianAppointmentSchedule pas " +
            "WHERE pas.appointment.patient.id = :patientId " +
            "AND pas.appointment.date <= CURRENT_DATE")
    List<PhysicianAppointmentSchedule> findPastAppointmentsForPatient(@Param("patientId")Long patientId);

    @Query("SELECT pas FROM PhysicianAppointmentSchedule pas " +
            "WHERE pas.appointment.patient.id= :patientId " +
            "AND pas.appointment.date = :date")
    List<PhysicianAppointmentSchedule> findAppointmentsForPatientOnDate(@Param("patientId") Long patientId, @Param("date") Date date);

    @Query("SELECT a FROM Appointment a " +
            "JOIN a.physicianAppointmentSchedule pas " +
            "WHERE pas.schedule.scheduleId = :scheduleId " +
            "AND pas.physician.id = :physicianId")
    List<Appointment> findAppointmentsByScheduleAndPhysician(@Param("scheduleId") Long scheduleId, @Param("physicianId") Long physicianId);

    @Query("SELECT COUNT(a) FROM Appointment a " +
            "WHERE a.patient = :patient " +
            "AND a.appointmentState IN :states")
    Long countScheduledOrRescheduledAppointments(Patient patient, List<AppointmentState> states);
}

