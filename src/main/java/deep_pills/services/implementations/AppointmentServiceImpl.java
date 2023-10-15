package deep_pills.services.implementations;

import deep_pills.dto.appointments.*;
import deep_pills.model.entities.accounts.users.patients.Patient;
import deep_pills.model.entities.accounts.users.physicians.Physician;
import deep_pills.model.entities.appointments.Appointment;
import deep_pills.model.entities.appointments.AppointmentSymptoms;
import deep_pills.model.entities.memberships.Membership;
import deep_pills.model.entities.memberships.Policy;
import deep_pills.model.entities.schedule.PhysicianAppointmentSchedule;
import deep_pills.model.entities.schedule.Schedule;
import deep_pills.model.entities.symptomsTreatmentDiagnosis.Treatment;
import deep_pills.model.entities.symptomsTreatmentDiagnosis.TreatmentPlan;
import deep_pills.model.enums.lists.Symptom;
import deep_pills.model.enums.states.AppointmentState;
import deep_pills.model.enums.states.TreatmentState;
import deep_pills.repositories.accounts.users.PatientRepository;
import deep_pills.repositories.accounts.users.PhysicianRepository;
import deep_pills.repositories.appointments.*;
import deep_pills.repositories.schedules.ScheduleRepository;
import deep_pills.services.interfaces.AppointmentService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentSymptomRepository appointmentSymptomRepository;
    private final AppointmentRepository appointmentRepository;
    private final PhysicianRepository physicianRepository;
    private final PatientRepository patientRepository;
    private final ScheduleRepository scheduleRepository;
    private final PhysicianAppointmentScheduleRepository physicianAppointmentScheduleRepository;
    private final TreatmentRepository treatmentRepository;
    private final TreatmentPlanRepository treatmentPlanRepository;

    @Override
    @Transactional
    public String serviceAppointment(AppointmentServiceDTO appointmentServiceDTO) throws Exception{
        Physician physician = getPhysicianFromOptional(appointmentServiceDTO.physicianPersonalId());
        Appointment appointment = getAppointmentFromOptional(appointmentServiceDTO.appointmentId());
        appointment.setAppointmentState(AppointmentState.COMPLETED);

        for(Symptom symptom : appointmentServiceDTO.symptoms()){
            AppointmentSymptoms appointmentSymptoms = new AppointmentSymptoms();
            appointmentSymptoms.setAppointment(appointment);
            appointmentSymptoms.setSymptom(symptom);
            appointmentSymptomRepository.save(appointmentSymptoms);
        }

        appointment.setDoctorsNotes(appointmentServiceDTO.doctorNotes());

        for(AppointmentTreatmentPlanDTO treatmentPlanInfo : appointmentServiceDTO.treatmentPlan()){
            Treatment newTreatment = new Treatment();
            newTreatment.setTreatment(treatmentPlanInfo.treatment());
            newTreatment.setTreatmentState(TreatmentState.ACTIVE);

            TreatmentPlan treatmentPlan = new TreatmentPlan();
            treatmentPlan.setTreatment(newTreatment);
            treatmentPlan.setPhysician(physician);
            treatmentPlan.setDiagnosis(treatmentPlanInfo.diagnosis());
            treatmentPlan.setAppointment(appointment);

            treatmentRepository.save(newTreatment);
            treatmentPlanRepository.save(treatmentPlan);
        }
        appointmentRepository.save(appointment);

        return "Appointment service updated successfully.";
    }

    @Override
    @Transactional
    public String cancellAppointment(Long appointmentId) throws Exception {
        Appointment appointment = getAppointmentFromOptional(appointmentId);
        appointment.setAppointmentState(AppointmentState.CANCELLED);
        appointmentRepository.save(appointment);
        return "Appointment " + appointment.getAppointmentId() + " cancelled";
    }

    @Override
    @Transactional
    public String rescheduleAppointment(AppointmentRescheduleDTO appointmentRescheduleDTO) throws Exception {
        Physician physician = getPhysicianFromOptional(appointmentRescheduleDTO.physicianPersonalId());
        Appointment appointment = getAppointmentFromOptional(appointmentRescheduleDTO.appointmentId());
        Schedule schedule = getScheduleFromOptional(appointmentRescheduleDTO.scheduleId());
        if (schedule.getDate().before(new Date())) throw new Exception("Cannot schedule an appointment for a date before today");

        Date appointmentDate = schedule.getDate();
        Date appointmentTime = appointmentRescheduleDTO.time();
        long appointmentDuration = appointment.getDuration();
        Date appointmentEndTime = new Date(appointmentTime.getTime() + (appointmentDuration * 60000));

        List<Appointment> appointmentList = appointmentRepository.findAppointmentsByScheduleAndPhysician(schedule.getScheduleId(), physician.getId());

        verifyOverlap(appointmentList, appointmentDate, appointmentTime, appointmentEndTime);

        appointment.setDate(appointmentDate);
        appointment.setTime(appointmentTime);
        appointment.setAppointmentState(AppointmentState.RESCHEDULED);
        appointmentRepository.save(appointment);

        return "Appointment "+appointment.getAppointmentId()+" rescheduled for "+appointmentDate+" at "+appointmentTime;
    }

    @Override
    @Transactional
    public Long scheduleAppointment(AppointmentScheduleDTO appointmentScheduleDTO) throws Exception {
        Physician physician = getPhysicianFromOptional(appointmentScheduleDTO.physicianPersonalId());
        Patient patient = getPatientFromOptional(appointmentScheduleDTO.patientPersonalId());
        Schedule schedule = getScheduleFromOptional(appointmentScheduleDTO.scheduleId());

        if(schedule.getDate().before(new Date())) throw new Exception("Cannot schedule an appointment for a date before today");

        Long scheduledAppointmentsCount = getScheduledAppointmentsCount(patient);

        // Verifying if the patient has appointment schedule availability
        if (scheduledAppointmentsCount >= getMaxAppointmentsAllowed(patient)) throw new Exception("The patient has reached the maximum allowed appointments.");
        
        Date newAppointmentDate = schedule.getDate();
        Date newAppointmentTime = appointmentScheduleDTO.time();
        long newAppointmentDuration = 30L;

        // Compute the new appointment ending time using its starting time and duration
        Date newAppointmentEndTime = new Date(newAppointmentTime.getTime() + (newAppointmentDuration * 60000)); // Duración en milisegundos

        // Get all appointments for the given schedule and physician
        List<Appointment> appointmentList = appointmentRepository.findAppointmentsByScheduleAndPhysician(schedule.getScheduleId(), physician.getId());

        verifyOverlap(appointmentList, newAppointmentDate, newAppointmentTime, newAppointmentEndTime);

        Appointment appointment = new Appointment();
        appointment.setAppointmentState(AppointmentState.SCHEDULED);
        appointment.setDate(newAppointmentDate);
        appointment.setDetailedReasons(appointmentScheduleDTO.reasons());
        appointment.setDuration(30L);
        appointment.setPatient(patient);
        appointment.setRequestTime(new Date());
        appointment.setTime(newAppointmentTime);
        appointment.setLocation("OnSite");
        Appointment savedAppointment = appointmentRepository.save(appointment);

        PhysicianAppointmentSchedule physicianAppointmentSchedule = new PhysicianAppointmentSchedule();
        physicianAppointmentSchedule.setAppointment(savedAppointment);
        physicianAppointmentSchedule.setSchedule(schedule);
        physicianAppointmentSchedule.setPhysician(physician);
        physicianAppointmentScheduleRepository.save(physicianAppointmentSchedule);
        return savedAppointment.getAppointmentId();
    }

    private void verifyOverlap(@NotNull List<Appointment> appointmentList, @NotNull Date newAppointmentDate, @NotNull Date newAppointmentTime, @NotNull Date newAppointmentEndTime) throws Exception {
        for (Appointment existingAppointment : appointmentList) {
            Date existingAppointmentDate = existingAppointment.getDate();
            Date existingAppointmentTime = existingAppointment.getTime();
            Long existingAppointmentDuration = existingAppointment.getDuration();

            // Computes the existing appointment ending time;
            Date existingAppointmentEndTime = new Date(existingAppointmentTime.getTime() + (existingAppointmentDuration * 60000));

            if(existingAppointment.getAppointmentState().equals(AppointmentState.SCHEDULED) ||
                    existingAppointment.getAppointmentState().equals(AppointmentState.RESCHEDULED)) {

                // Verifies if the existing appointment and the new appointment overlap
                if (newAppointmentDate.equals(existingAppointmentDate) &&
                        ((newAppointmentTime.equals(existingAppointmentTime) ||
                                (newAppointmentTime.after(existingAppointmentTime) && newAppointmentTime.before(existingAppointmentEndTime))) ||
                                (newAppointmentEndTime.after(existingAppointmentTime) && newAppointmentEndTime.before(existingAppointmentEndTime)))) {
                    throw new Exception("Appointment cannot be scheduled for " + newAppointmentDate + " at " + newAppointmentTime + " since it overlaps with an already scheduled appointment");
                }
            }
        }
    }

    private Integer getMaxAppointmentsAllowed(@NotNull Patient patient) throws Exception {
        // Obtaining patient Membership
        Membership membershipOwned = patient.getOwnedMembership();
        Membership membershipBeneficiary = patient.getBeneficiaryMembership();
        Membership membership;
        if (membershipOwned != null) membership = membershipOwned;
        else membership = membershipBeneficiary;

        if (membership == null) return 3;

        Policy policy = membership.getPolicy();
        if (policy == null) throw new Exception("Policy associated with the Membership could not be found");

        return policy.getMaxAppointments();
    }

    public Long getScheduledAppointmentsCount(@NotNull Patient patient) {
        List<AppointmentState> states = Arrays.asList(AppointmentState.SCHEDULED, AppointmentState.RESCHEDULED);
        return appointmentRepository.countScheduledOrRescheduledAppointments(patient, states);
    }

    @Override
    @Transactional
    public List<AppointmentGenericDTO> allAppointmentsByPhysicianId(@NotNull String physicianPersonalID) throws Exception {
        Physician physician = getPhysicianFromOptional(physicianPersonalID);
        List<PhysicianAppointmentSchedule> appointments = appointmentRepository.findByPhysician_Id(physician.getId());
        return mapAppointmentDTOS(appointments);
    }

    @Override
    @Transactional
    public List<AppointmentGenericDTO> upcomingAppointmentsByPhysicianId(@NotNull String physicianPersonalID) throws Exception {
        Physician physician = getPhysicianFromOptional(physicianPersonalID);
        List<PhysicianAppointmentSchedule> appointments = appointmentRepository.findUpcomingAppointmentsForPhysician(physician.getId());
        return mapAppointmentDTOS(appointments);
    }

    @Override
    @Transactional
    public List<AppointmentGenericDTO> pastAppointmentsByPhysicianId(@NotNull String physicianPersonalID) throws Exception {
        Physician physician = getPhysicianFromOptional(physicianPersonalID);
        List<PhysicianAppointmentSchedule> appointments = appointmentRepository.findPastAppointmentsForPhysician(physician.getId());
        return mapAppointmentDTOS(appointments);
    }

    @Override
    @Transactional
    public List<AppointmentGenericDTO> dateSpecificAppointmentsByPhysicianId(@NotNull String physicianPersonalID, @NotNull Date date) throws Exception {
        Physician physician = getPhysicianFromOptional(physicianPersonalID);
        List<PhysicianAppointmentSchedule> appointments = appointmentRepository.findAppointmentsForPhysicianOnDate(physician.getId(), date);
        return mapAppointmentDTOS(appointments);
    }

    private Physician getPhysicianFromOptional(@NotNull String physicianPersonalID) throws Exception {
        Optional<Physician> optional = physicianRepository.findByPersonalId(physicianPersonalID);
        if(optional.isEmpty()) throw new Exception("Physician with PID: "+physicianPersonalID+" not found");
        return optional.get();
    }

    private Patient getPatientFromOptional(@NotNull String patientPersonalID) throws Exception {
        Optional<Patient> optional = patientRepository.findByPersonalId(patientPersonalID);
        if(optional.isEmpty()) throw new Exception("Patient with PID: "+patientPersonalID+" not found");
        return optional.get();
    }

    private Schedule getScheduleFromOptional(@NotNull Long scheduleId) throws Exception {
        Optional<Schedule> optional = scheduleRepository.findById(scheduleId);
        if(optional.isEmpty()) throw new Exception("Schedule with ID: "+scheduleId+" not found");
        return optional.get();
    }

    private Appointment getAppointmentFromOptional(@NotNull Long appointmentId) throws Exception {
        Optional<Appointment> optional = appointmentRepository.findById(appointmentId);
        if(optional.isEmpty()) throw new Exception("Appointment with ID: "+appointmentId+" not found");
        return optional.get();
    }

    private List<AppointmentGenericDTO> mapAppointmentDTOS(@NotNull List<PhysicianAppointmentSchedule> appointments) {
        List<AppointmentGenericDTO> appointmentDTOs = new ArrayList<>();
        for(PhysicianAppointmentSchedule pas: appointments){
            appointmentDTOs.add(new AppointmentGenericDTO(
                    pas.getAppointment().getAppointmentId(),
                    pas.getAppointment().getPatient().getPersonalId(),
                    pas.getAppointment().getDate(),
                    pas.getAppointment().getTime(),
                    pas.getAppointment().getDuration(),
                    pas.getAppointment().getAppointmentState()));
        }
        return appointmentDTOs;
    }

    @Override
    @Transactional
    public List<AppointmentGenericDTO> allAppointmentsByPatientId(@NotNull String patientPersonalID) throws Exception {
        Patient patient = getPatientFromOptional(patientPersonalID);
        List<PhysicianAppointmentSchedule> appointments = appointmentRepository.findByPatient_Id(patient.getId());
        return mapAppointmentDTOS(appointments);
    }

    @Override
    @Transactional
    public List<AppointmentGenericDTO> upcomingAppointmentsByPatientId(@NotNull String patientPersonalID) throws Exception {
        Patient patient = getPatientFromOptional(patientPersonalID);
        List<PhysicianAppointmentSchedule> appointments = appointmentRepository.findUpcomingAppointmentsForPatient(patient.getId());
        return mapAppointmentDTOS(appointments);
    }

    @Override
    @Transactional
    public List<AppointmentGenericDTO> pastAppointmentsByPatientId(@NotNull String patientPersonalID) throws Exception {
        Patient patient = getPatientFromOptional(patientPersonalID);
        List<PhysicianAppointmentSchedule> appointments = appointmentRepository.findPastAppointmentsForPatient(patient.getId());
        return mapAppointmentDTOS(appointments);
    }

    @Override
    @Transactional
    public List<AppointmentGenericDTO> dateSpecificAppointmentsByPatientId(@NotNull String patientPersonalID, @NotNull Date date) throws Exception {
        Patient patient = getPatientFromOptional(patientPersonalID);
        List<PhysicianAppointmentSchedule> appointments = appointmentRepository.findAppointmentsForPatientOnDate(patient.getId(), date);
        return mapAppointmentDTOS(appointments);
    }
}
