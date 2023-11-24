package deep_pills.services.implementations;

import deep_pills.dto.accounts.physician.PhysicianListingItemPatientDTO;
import deep_pills.dto.appointments.*;
import deep_pills.dto.claims.patient.ClaimItemPatientDTO;
import deep_pills.dto.emails.EMailDTO;
import deep_pills.dto.schedule.FreeDayRequestDTO;
import deep_pills.dto.schedule.HourOfferDTO;
import deep_pills.dto.schedule.HourSearchDTO;
import deep_pills.dto.schedule.ScheduleOfferDTO;
import deep_pills.model.entities.accounts.users.patients.Patient;
import deep_pills.model.entities.accounts.users.physicians.Physician;
import deep_pills.model.entities.appointments.Appointment;
import deep_pills.model.entities.appointments.AppointmentSymptoms;
import deep_pills.model.entities.claims.ClaimInfo;
import deep_pills.model.entities.memberships.Membership;
import deep_pills.model.entities.memberships.Policy;
import deep_pills.model.entities.notifications.EMail;
import deep_pills.model.entities.schedule.FreeDay;
import deep_pills.model.entities.schedule.PhysicianAppointmentSchedule;
import deep_pills.model.entities.schedule.Schedule;
import deep_pills.model.entities.symptomsTreatmentDiagnosis.Treatment;
import deep_pills.model.entities.symptomsTreatmentDiagnosis.TreatmentPlan;
import deep_pills.model.enums.lists.Specialization;
import deep_pills.model.enums.lists.Symptom;
import deep_pills.model.enums.states.AppointmentState;
import deep_pills.model.enums.states.FreeDayStatus;
import deep_pills.model.enums.states.ScheduleState;
import deep_pills.model.enums.states.TreatmentState;
import deep_pills.model.enums.types.EMailType;
import deep_pills.repositories.accounts.users.PatientRepository;
import deep_pills.repositories.accounts.users.PhysicianRepository;
import deep_pills.repositories.appointments.*;
import deep_pills.repositories.schedules.FreeDayRepository;
import deep_pills.repositories.schedules.ScheduleRepository;
import deep_pills.services.interfaces.AppointmentService;
import deep_pills.services.interfaces.EMailService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

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
    private final FreeDayRepository freeDayRepository;
    private final EMailService eMailService;

    @Override
    @Transactional
    public List<HourOfferDTO> getHoursForScheduling(@NotNull HourSearchDTO hourSearchDTO) throws Exception{
        if(!physicianRepository.existsById(hourSearchDTO.physicianId())) throw new Exception("No such Physician Id found: " + hourSearchDTO.physicianId());
        if(!scheduleRepository.existsById(hourSearchDTO.scheduleId())) throw new Exception("No such Schedule Id found: "+ hourSearchDTO.scheduleId());

        Physician physician = physicianRepository.getReferenceById(hourSearchDTO.physicianId());
        Schedule schedule = scheduleRepository.getReferenceById(hourSearchDTO.scheduleId());
        List<AppointmentState> states = new ArrayList<>();
        states.add(AppointmentState.SCHEDULED);
        states.add(AppointmentState.RESCHEDULED);

        List<PhysicianAppointmentSchedule> results = physicianAppointmentScheduleRepository.findByPhysicianPersonalIdAndDateAndStatus(physician.getPersonalId(), schedule.getScheduleId(), states);
        List<HourOfferDTO> availableHours = new ArrayList<>();

        LocalTime startTime = schedule.getShift().getStartTime().toLocalTime();
        LocalTime endTime = schedule.getShift().getEndTime().toLocalTime();

        // Iterar en pasos de media hora
        while (!startTime.isAfter(endTime)) {
            LocalTime endTimeOfSlot = startTime.plusMinutes(30);

            // Verificar si hay alguna coincidencia en el rango actual
            LocalTime finalStartTime = startTime;
            if (results.stream().noneMatch(s -> isOverlap(finalStartTime, endTimeOfSlot, s))) {
                availableHours.add(new HourOfferDTO(schedule.getScheduleId(), startTime, endTimeOfSlot));
            }

            startTime = endTimeOfSlot;
        }

        return availableHours;
    }

    // Método para verificar si hay una coincidencia de rango entre la cita existente y el rango actual
    private boolean isOverlap(LocalTime startTime, LocalTime endTime, PhysicianAppointmentSchedule schedule) {
        LocalTime scheduleStartTime = schedule.getSchedule().getShift().getStartTime().toLocalTime();
        LocalTime scheduleEndTime = schedule.getSchedule().getShift().getEndTime().toLocalTime();

        return !(endTime.isBefore(scheduleStartTime) || startTime.isAfter(scheduleEndTime));
    }

    @Override
    @Transactional
    public List<ScheduleOfferDTO> getSchedulesForScheduling(Long physicianId) throws Exception{
        if(!physicianRepository.existsById(physicianId)) throw new Exception("No such physician Id found: " + physicianId);

        List<Object[]> results = scheduleRepository.getNonFreeDayUpcomingSchedulesWithPhysicianIdAndScheduleState(physicianId, ScheduleState.ACTIVE);

        List<ScheduleOfferDTO> schedules = results.stream()
                .map(result -> new ScheduleOfferDTO(
                        (Long) result[0],
                        (Date) result[1],
                        (Time) result[2],
                        (Time) result[3]
                ))
                .collect(Collectors.toList());

        return schedules;
    }

    @Override
    @Transactional
    public Long scheduleFreeDayForPhysician(FreeDayRequestDTO freeDayRequestDTO) throws Exception {
        Physician physician = getPhysicianFromOptional(freeDayRequestDTO.physicianPersonalId());
        Schedule schedule = getScheduleFromOptional(freeDayRequestDTO.scheduleId());

        if (!schedule.getShift().equals(physician.getShift())) throw new Exception("This schedule does not belong to the physicians shift");

        if(freeDayRepository.countByPhysicianPersonalIdAndStatus(physician.getPersonalId(), FreeDayStatus.SCHEDULED)>1) throw new Exception("Physician "+freeDayRequestDTO.physicianPersonalId()+" already has a Free Day scheduled");

        List<AppointmentState> states = new ArrayList<>();
        states.add(AppointmentState.SCHEDULED);
        states.add(AppointmentState.RESCHEDULED);

        if(physicianAppointmentScheduleRepository.countByPhysicianPersonalIdAndDateAndStatus(physician.getPersonalId(), freeDayRequestDTO.scheduleId(), states)>0) throw new Exception("Appointments already scheduled for this day and time");

        FreeDay freeDay = new FreeDay();
        freeDay.setFreeDayStatus(FreeDayStatus.SCHEDULED);
        freeDay.setPhysician(physician);
        freeDay.setSchedule(schedule);
        eMailService.sendEmail(new EMailDTO(physician.getEmail(),
                "This JUAN from the DeepPills Team! Your free day has been scheduled for "+schedule.getDate()+".",
                "Free Day",
                EMailType.FREE_DAY,
                physician.getId()));
        return freeDayRepository.save(freeDay).getFreeDayId();
    }

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

        eMailService.sendEmail(new EMailDTO(physician.getEmail(),
                "This JUAN from the DeepPills Team! Appointment "+appointment.getAppointmentId()+" served.",
                "Appointment Service",
                EMailType.APPOINT_SERVED,
                appointment.getAppointmentId()));

        eMailService.sendEmail(new EMailDTO(appointment.getPatient().getEmail(),
                "This JUAN from the DeepPills Team! Appointment "+appointment.getAppointmentId()+" served.",
                "Appointment Service",
                EMailType.APPOINT_SERVED,
                appointment.getAppointmentId()));

        return "Appointment service updated successfully.";
    }

    @Override
    @Transactional
    public String cancellAppointment(Long appointmentId) throws Exception {
        Appointment appointment = getAppointmentFromOptional(appointmentId);
        if(appointment.getAppointmentState().equals(AppointmentState.CANCELLED) || appointment.getAppointmentState().equals(AppointmentState.COMPLETED)) throw new Exception("Cannot cancel an already cancelled or completed appointment");
        appointment.setAppointmentState(AppointmentState.CANCELLED);
        appointmentRepository.save(appointment);
        eMailService.sendEmail(new EMailDTO(appointment.getPatient().getEmail(),
                "This JUAN from the DeepPills Team! Your appointment "+
                        appointment.getAppointmentId()+" for "+appointment.getDate()+
                        " at "+appointment.getTime().getTime()+" on "+appointment.getLocation()+
                        " has been cancelled.",

                "Appointment Cancelled",
                EMailType.APPOINT_CANCELLED,
                appointment.getAppointmentId()));
        return "Appointment " + appointment.getAppointmentId() + " cancelled";
    }

    @Override
    @Transactional
    public String rescheduleAppointment(AppointmentRescheduleDTO appointmentRescheduleDTO) throws Exception {
        Physician physician = getPhysicianFromOptional(appointmentRescheduleDTO.physicianPersonalId());
        Appointment appointment = getAppointmentFromOptional(appointmentRescheduleDTO.appointmentId());
        Schedule schedule = getScheduleFromOptional(appointmentRescheduleDTO.scheduleId());

        Date date = appointment.getDate(), time = appointment.getTime();
        String location = appointment.getLocation();

        if(freeDayRepository.findByPhysicianPersonalIdAndScheduleAndStatus(physician.getPersonalId(), schedule.getScheduleId(), FreeDayStatus.SCHEDULED).isPresent()) throw new Exception("This is a scheduled free day for the physician");

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
        appointment.getPhysicianAppointmentSchedule().setSchedule(schedule);
        appointmentRepository.save(appointment);

        eMailService.sendEmail(new EMailDTO(appointment.getPatient().getEmail(),
                "This JUAN from the DeepPills Team! Your appointment "+
                        appointment.getAppointmentId()+
                        " for "+date+
                        " at "+time.getTime()+
                        " on "+location+
                        " has been rescheduled"+
                        " for "+appointment.getDate()+
                        " at "+appointment.getTime().getTime()+
                        " on "+appointment.getLocation()
                ,
                "Appointment Rescheduled",
                EMailType.APPOINT_RESCHEDULED,
                appointment.getAppointmentId()));

        return "Appointment "+appointment.getAppointmentId()+" rescheduled for "+appointmentDate+" at "+appointment.getTime();
    }

    @Override
    @Transactional
    public Long scheduleAppointment(AppointmentScheduleDTO appointmentScheduleDTO) throws Exception {

        Physician physician;
        Patient patient;
        if(!physicianRepository.existsById(appointmentScheduleDTO.physicianId())) throw new Exception("Physician id:"+appointmentScheduleDTO.physicianId()+" not found");
        else physician = physicianRepository.getReferenceById(appointmentScheduleDTO.physicianId());
        if(!patientRepository.existsById(appointmentScheduleDTO.patientId())) throw new Exception("Patient id:"+appointmentScheduleDTO.patientId()+" not found");
        else patient = patientRepository.getReferenceById(appointmentScheduleDTO.patientId());
        Schedule schedule = getScheduleFromOptional(appointmentScheduleDTO.scheduleId());

        if(freeDayRepository.findByPhysicianPersonalIdAndScheduleAndStatus(physician.getPersonalId(), schedule.getScheduleId(), FreeDayStatus.SCHEDULED).isPresent()) throw new Exception("This is a scheduled free day for the physician");

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

        eMailService.sendEmail(new EMailDTO(appointment.getPatient().getEmail(),
                "This JUAN from the DeepPills Team! Your appointment "+
                        appointment.getAppointmentId()+
                        " has been scheduled"+
                        " for "+appointment.getDate()+
                        " at "+appointment.getTime().getTime()+
                        " on "+appointment.getLocation(),

                "Appointment Scheduled",
                EMailType.APPOINT_SCHEDULED,
                appointment.getAppointmentId()));
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
    public List<AppointmentGenericDTO> dateSpecificAppointmentsByPhysicianId(AppointmentDatePhysicianSearchDTO appointmentDatePhysicianSearchDTO) throws Exception {
        Physician physician = getPhysicianFromOptional(appointmentDatePhysicianSearchDTO.physicianPersonalId());
        List<PhysicianAppointmentSchedule> appointments = appointmentRepository.findAppointmentsForPhysicianOnDate(physician.getId(), appointmentDatePhysicianSearchDTO.date());
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
    public List<AppointmentGenericDTO> dateSpecificAppointmentsByPatientId(@NotNull AppointmentDatePatientSearchDTO appointmentDatePatientSearchDTO) throws Exception {
        Patient patient = getPatientFromOptional(appointmentDatePatientSearchDTO.patientPersonalId());
        System.out.println(appointmentDatePatientSearchDTO.date());
        List<PhysicianAppointmentSchedule> appointments = appointmentRepository.findAppointmentsForPatientOnDate(patient.getId(), appointmentDatePatientSearchDTO.date());
        return mapAppointmentDTOS(appointments);
    }

    @Override
    @Transactional
    public AppointmentDetailsDTO getAppointmentDetails(@NotNull Long appointmentId) throws Exception {
        Appointment appointment = getAppointmentFromOptional(appointmentId);

        List<ClaimItemPatientDTO> claims = new ArrayList<>();
        for(ClaimInfo clms: appointment.getClaimInfoList()){
            claims.add(new ClaimItemPatientDTO(
                    clms.getClaim().getClaimId(),
                    clms.getClaim().getClaimDate(),
                    clms.getClaim().getClaimType(),
                    clms.getClaim().getClaimStatus()
            ));
        }

        List<AppointmentTreatmentPlanDTO> treatments = new ArrayList<>();
        for(TreatmentPlan treatment: appointment.getTreatmentPlanList()){
            treatments.add(new AppointmentTreatmentPlanDTO(
                    treatment.getTreatment().getTreatment(),
                    treatment.getDiagnosis()
            ));
        }

        List<Long> emails = new ArrayList<>();
        for(EMail email: appointment.getEmails()){
            emails.add(email.getEmailId());
        }

        Physician physician = appointment.getPhysicianAppointmentSchedule().getPhysician();
        PhysicianListingItemPatientDTO physicianInfo = new PhysicianListingItemPatientDTO(
                physician.getId(),
                physician.getName(),
                physician.getLastName()
        );

        List<Symptom> symptoms = new ArrayList<>();
        for(AppointmentSymptoms symps: appointment.getAppointmentSymptoms()){
            symptoms.add(symps.getSymptom());
        }

        return new AppointmentDetailsDTO(
                appointmentId,
                appointment.getPatient().getPersonalId(),
                appointment.getDate(),
                appointment.getTime(),
                appointment.getLocation(),
                appointment.getDuration(),
                appointment.getRequestTime(),
                appointment.getDetailedReasons(),
                appointment.getDoctorsNotes(),
                appointment.getAppointmentState(),
                claims,
                treatments,
                emails,
                physicianInfo,
                symptoms
        );
    }

    @Override
    @Transactional
    public List<AppointmentGenericDTO>
    getAppointmentsByPatientIdAndSpecialization(
            @NotNull AppointmentSpecializationSearchDTO appointmentSpecializationSearchDTO)
            throws Exception{
                Long patientId = appointmentSpecializationSearchDTO.patientId();
                String specialization = appointmentSpecializationSearchDTO.specialization();
                if(!patientRepository.existsById(patientId)){
                    throw new Exception("Patient " + patientId+" not found");
                }
                Specialization s = null;
                for(int i=0; i<Specialization.values().length ; i++){
                    if(Specialization.values()[i].name().equals(specialization)) s=Specialization.values()[i];
                }
                if(s==null) throw new Exception("Specialization not found");
                List<AppointmentGenericDTO> appointments = new ArrayList<>();
                for(Appointment a: appointmentRepository.findByPatientIdAndPhysicianSpecialization(patientId, s)){
                    appointments.add(new AppointmentGenericDTO(
                            a.getAppointmentId(),
                            a.getPatient().getPersonalId(),
                            a.getDate(),
                            a.getTime(),
                            a.getDuration(),
                            a.getAppointmentState()
                    ));
                }
                return appointments;
    }

}
