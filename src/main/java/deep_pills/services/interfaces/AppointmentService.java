package deep_pills.services.interfaces;

import deep_pills.dto.appointments.*;
import deep_pills.dto.schedule.FreeDayRequestDTO;
import jakarta.validation.constraints.NotNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface AppointmentService {
    @Transactional
    Long scheduleFreeDayForPhysician(FreeDayRequestDTO freeDayRequestDTO) throws Exception;

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
    List<AppointmentGenericDTO> dateSpecificAppointmentsByPhysicianId(@NotNull AppointmentDatePhysicianSearchDTO appointmentDatePhysicianSearchDTO) throws Exception;

    @Transactional
    List<AppointmentGenericDTO> allAppointmentsByPatientId(@NotNull String patientPersonalID) throws Exception;

    @Transactional
    List<AppointmentGenericDTO> upcomingAppointmentsByPatientId(@NotNull String patientPersonalID) throws Exception;

    @Transactional
    List<AppointmentGenericDTO> pastAppointmentsByPatientId(@NotNull String patientPersonalID) throws Exception;

    @Transactional
    List<AppointmentGenericDTO> dateSpecificAppointmentsByPatientId(@NotNull AppointmentDatePatientSearchDTO appointmentDatePatientSearchDTO) throws Exception;
}
