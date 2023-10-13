package deep_pills.services.interfaces;

import deep_pills.dto.appointments.AppointmentGenericDTO;
import deep_pills.dto.appointments.AppointmentRescheduleDTO;
import deep_pills.dto.appointments.AppointmentScheduleDTO;
import deep_pills.dto.appointments.AppointmentServiceDTO;
import jakarta.validation.constraints.NotNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface AppointmentService {
    @Transactional
    String serviceAppointment(AppointmentServiceDTO appointmentServiceDTO) throws Exception;

    @Transactional
    String cancellAppointment(Long appointmentId) throws Exception;

    @Transactional
    String rescheduleAppointment(AppointmentRescheduleDTO appointmentRescheduleDTO) throws Exception;

    @Transactional
    Long scheduleAppointment(AppointmentScheduleDTO appointmentScheduleDTO) throws Exception;

    List<AppointmentGenericDTO> allAppointmentsByPhysicianId(@NotNull String physicianPersonalID) throws Exception;

    @Transactional
    List<AppointmentGenericDTO> upcomingAppointmentsByPhysicianId(@NotNull String physicianPersonalID) throws Exception;

    @Transactional
    List<AppointmentGenericDTO> pastAppointmentsByPhysicianId(@NotNull String physicianPersonalID) throws Exception;

    @Transactional
    List<AppointmentGenericDTO> dateSpecificAppointmentsByPhysicianId(@NotNull String physicianPersonalID, @NotNull Date date) throws Exception;

    @Transactional
    List<AppointmentGenericDTO> allAppointmentsByPateintId(@NotNull String patientPersonalID) throws Exception;

    @Transactional
    List<AppointmentGenericDTO> upcomingAppointmentsByPatientId(@NotNull String patientPersonalID) throws Exception;

    @Transactional
    List<AppointmentGenericDTO> pastAppointmentsByPatientId(@NotNull String patientPersonalID) throws Exception;

    @Transactional
    List<AppointmentGenericDTO> dateSpecificAppointmentsByPatientnId(@NotNull String patientPersonalID, @NotNull Date date) throws Exception;
}
