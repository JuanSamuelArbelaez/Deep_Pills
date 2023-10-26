package deep_pills.services.implementations;

import deep_pills.dto.emails.EMailDTO;
import deep_pills.dto.registrations.*;
import deep_pills.model.entities.accounts.Account;
import deep_pills.model.entities.accounts.users.patients.*;
import deep_pills.model.entities.accounts.users.physicians.*;
import deep_pills.model.entities.schedule.*;
import deep_pills.model.enums.states.ScheduleState;
import deep_pills.model.enums.types.EMailType;
import deep_pills.model.enums.types.ShiftType;
import deep_pills.model.enums.lists.Specialization;
import deep_pills.model.enums.states.AccountState;
import deep_pills.repositories.accounts.*;
import deep_pills.repositories.accounts.users.*;
import deep_pills.repositories.accounts.users.registrations.*;
import deep_pills.repositories.schedules.*;
import deep_pills.repositories.specializations.*;
import deep_pills.services.interfaces.EMailService;
import deep_pills.services.interfaces.RegistrationService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final AdminRepository adminRepository;
    private final AccountRepository accountRepository;
    private final PatientRepository patientRepository;
    private final PatientRegistrationRepository patientRegistrationRepository;
    private final PhysicianRepository physicianRepository;
    private final PhysicianRegistrationRepository physicianRegistrationRepository;
    private final PhysicianSpecializationRepository physicianSpecializationRepository;
    private final ScheduleRepository schedulerRepository;
    private final ShiftRepository shiftRepository;

    private final EMailService eMailService;

    @Override
    @Transactional
    public Long registerPhysician(@NotNull RegisterPhysicianDTO physicianForm) throws Exception{
        Physician physicianEntity =  physicianSetUp(physicianForm); //Basic info setup

        List<PhysicianSpecialization> physicianSpecializations = specializationSetUp(physicianEntity, physicianForm); //Specializations setup

        shiftSetUp(physicianEntity, physicianForm); //Shift setup

        Physician registeredPhysician  = physicianRepository.save(physicianEntity);

        PhysicianRegistration registration = new PhysicianRegistration();
        registration.setDate(new Date());
        registration.setPhysician(registeredPhysician);
        registration.setAdmin(adminRepository.findById(physicianForm.adminId()).get());
        physicianRegistrationRepository.save(registration);
        physicianSpecializationRepository.saveAll(physicianSpecializations);

        eMailService.sendEmail(new EMailDTO(registeredPhysician.getEmail(),
                "This JUAN from the DeepPills Team! We are happy that you decided to join us. Go to your dashboard so we can start giving you the care you deserve.",
                "Welcome to DeepPills",
                EMailType.REGISTRATION,
                registeredPhysician.getId()
                ));

        return registeredPhysician.getId();
    }

    private void shiftSetUp(Physician physicianEntity, RegisterPhysicianDTO physicianForm) {
        Long shiftId = shiftRepository.findShiftIdByStartTimeAndEndTimeAndDays(physicianForm.shift().startTime(), physicianForm.shift().endTime(), physicianForm.shift().days());
        Shift shift;
        if(shiftId == null){
            Shift newShift = new Shift();
            newShift.setStartTime(physicianForm.shift().startTime());
            newShift.setEndTime(physicianForm.shift().endTime());
            newShift.setShiftType(ShiftType.CUSTOM_SHIFT);
            newShift.setDays(physicianForm.shift().days());
            shift = shiftRepository.save(newShift);
            createInitialSchedulesForShif(shift);
        } else shift = shiftRepository.getReferenceById(shiftId);
        physicianEntity.setShift(shift);
    }
    private void createInitialSchedulesForShif(Shift shift) {
        String[] dayTokens = shift.getDays().split(" ");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, 2); // Move to two months ahead
        calendar.set(Calendar.DAY_OF_MONTH, 1); // Move to the first day of that month
        calendar.add(Calendar.DATE, -1); // Move to the last day of the previous month

        Date lastDayOfFollowingMonth = calendar.getTime();

        // Create Schedule entities for each day from today to the last day of the following month
        Date currentDate = new Date();
        while (currentDate.before(lastDayOfFollowingMonth)) {

            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            String dayOfWeekString = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(currentDate);

            if (Arrays.asList(dayTokens).contains(dayOfWeekString)) {
                Schedule schedule = new Schedule();
                schedule.setShift(shift);
                schedule.setDate(currentDate);
                schedule.setScheduleState(ScheduleState.ACTIVE);
                schedulerRepository.save(schedule);
            }

            // Move to the next day
            calendar.setTime(currentDate);
            calendar.add(Calendar.DATE, 1);
            currentDate = calendar.getTime();
        }
    }

    private List<PhysicianSpecialization> specializationSetUp(Physician physicianEntity, RegisterPhysicianDTO physicianForm) throws Exception {
        List<PhysicianSpecialization> specializations = new ArrayList<>();

        for(Specialization specialization : physicianForm.specializations()){
            PhysicianSpecialization physicianSpecialization = new PhysicianSpecialization();
            physicianSpecialization.setPhysician(physicianEntity);
            physicianSpecialization.setSpecialization(specialization);
            specializations.add(physicianSpecialization);
        }

        return specializations;
    }

    private Physician physicianSetUp(RegisterPhysicianDTO physicianForm) throws Exception {
        if(emailExists(physicianForm.email())) throw new Exception("Email already in use");
        if(personalPhysicianIdExists(physicianForm.personalId())) throw new Exception("Personal ID already in use");
        if(adminRepository.findById(physicianForm.adminId()).isEmpty()) throw new Exception("Admin not found");

        Physician physicianEntity = new Physician();
        physicianEntity.setName(physicianForm.name());
        physicianEntity.setLastName(physicianForm.lastName());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncriptada = passwordEncoder.encode(physicianForm.password());
        physicianEntity.setPassword(passwordEncriptada);

        physicianEntity.setEmail(physicianForm.email());
        physicianEntity.setPersonalId(physicianForm.personalId());
        physicianEntity.setState(AccountState.ACTIVE);
        return physicianEntity;
    }

    @Override
    @Transactional
    public Long registerPatient(RegisterPatientDTO patientForm) throws Exception {
        if(emailExists(patientForm.email())) throw new Exception("Email already in use");
        if(personalPatientIdExists(patientForm.personalId()))throw new Exception("Personal ID already in use");

        Patient patientEntity =  new Patient();

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncriptada = passwordEncoder.encode(patientForm.password());
        patientEntity.setPassword(passwordEncriptada);

        patientEntity.setEmail(patientForm.email());
        patientEntity.setPersonalId(patientForm.personalId());
        patientEntity.setState(AccountState.ACTIVE);

        Patient registeredPatient = patientRepository.save(patientEntity);

        PatientRegistration registration = new PatientRegistration();
        registration.setPatient(registeredPatient);
        registration.setDate(new Date());
        patientRegistrationRepository.save(registration);
        eMailService.sendEmail(new EMailDTO(registeredPatient.getEmail(),
                "This JUAN from the DeepPills Team! We are happy that you decided to join us. Go to your dashboard so we can start giving you the care you deserve.",
                "Welcome to DeepPills",
                EMailType.REGISTRATION,
                registeredPatient.getId()
        ));
        return registeredPatient.getId();
    }

    private boolean emailExists(String email){
        Optional<Account> optional = accountRepository.findByEmail(email);
        return optional.isPresent();
    }
    private boolean personalPatientIdExists(String personalId){return patientRepository.findByPersonalId(personalId).isPresent();}
    private boolean personalPhysicianIdExists(String personalId) {return physicianRepository.findByPersonalId(personalId).isPresent();}
}
