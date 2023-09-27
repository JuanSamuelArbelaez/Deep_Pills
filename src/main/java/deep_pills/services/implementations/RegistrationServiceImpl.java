package deep_pills.services.implementations;

import deep_pills.dto.registrations.*;
import deep_pills.model.entities.accounts.users.patients.Patient;
import deep_pills.model.entities.accounts.users.patients.PatientRegistration;
import deep_pills.model.entities.accounts.users.physicians.*;
import deep_pills.model.entities.schedule.Schedule;
import deep_pills.model.entities.schedule.Shift;
import deep_pills.model.enums.types.ShiftType;
import deep_pills.model.enums.lists.Specialization;
import deep_pills.model.enums.states.AccountState;
import deep_pills.repositories.accounts.*;
import deep_pills.repositories.accounts.users.*;
import deep_pills.repositories.accounts.users.registrations.*;
import deep_pills.repositories.appointments.*;
import deep_pills.repositories.specializations.*;
import deep_pills.services.interfaces.RegistrationService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    @Override
    @Transactional
    public Long registerPhysician(@NotNull RegisterPhysicianDTO physicianForm) throws Exception{
        Physician physicianEntity =  physicianSetUp(physicianForm); //Basic info setup

        List<PhysicianSpecialization> physicianSpecializations = specializationSetUp(physicianEntity, physicianForm); //Specializations setup

        shiftSetUp(physicianEntity, physicianForm); //Shift setup

        Physician registeredPhysician  = physicianRepository.save(physicianEntity);

        PhysicianRegistration registration = new PhysicianRegistration();
        registration.setPhysician(registeredPhysician);
        registration.setAdmin(adminRepository.getReferenceById(physicianForm.adminId()));
        physicianRegistrationRepository.save(registration);
        physicianSpecializationRepository.saveAll(physicianSpecializations);

        return registeredPhysician.getId();
    }

    private void shiftSetUp(Physician physicianEntity, RegisterPhysicianDTO physicianForm) {
        Long shiftId = shiftRepository.findShiftIdByStartTimeAndEndTime(physicianForm.shift().startTime(), physicianForm.shift().endTime());
        Shift shift;
        if(shiftId == null){
            Shift newShift = new Shift();
            newShift.setStartTime(physicianForm.shift().startTime());
            newShift.setEndTime(physicianForm.shift().endTime());
            newShift.setShiftType(ShiftType.CUSTOM_SHIFT);
            shift = shiftRepository.save(newShift);
            createInitialSchedulesForShif(shift);
        } else shift = shiftRepository.getReferenceById(shiftId);
        physicianEntity.setShift(shift);
    }
    private void createInitialSchedulesForShif(Shift shift) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, 2); // Move to two months ahead
        calendar.set(Calendar.DAY_OF_MONTH, 1); // Move to the first day of that month
        calendar.add(Calendar.DATE, -1); // Move to the last day of the previous month

        Date lastDayOfFollowingMonth = calendar.getTime();

        // Create Schedule entities for each day from today to the last day of the following month
        Date currentDate = new Date();
        while (currentDate.before(lastDayOfFollowingMonth)) {
            Schedule schedule = new Schedule();
            schedule.setShift(shift);
            schedule.setDate(currentDate);
            schedulerRepository.save(schedule);

            // Move to the next day
            calendar.setTime(currentDate);
            calendar.add(Calendar.DATE, 1);
            currentDate = calendar.getTime();
        }
    }

    private List<PhysicianSpecialization> specializationSetUp(Physician physicianEntity, RegisterPhysicianDTO physicianForm) throws Exception {
        List<PhysicianSpecialization> specializations = new ArrayList<>();

        for(Integer specializationId: physicianForm.specializationsId()){
            if(specializationId<0 || specializationId>=physicianForm.specializationsId().size())throw new Exception("Invalid specialization ID: " + specializationId);
        }

        for(Integer specializationId : physicianForm.specializationsId()){
            PhysicianSpecialization physicianSpecialization = new PhysicianSpecialization();
            physicianSpecialization.setPhysician(physicianEntity);
            physicianSpecialization.setSpecialization(Specialization.values()[specializationId]);
            specializations.add(physicianSpecialization);
        }

        return specializations;
    }

    private Physician physicianSetUp(RegisterPhysicianDTO physicianForm) throws Exception {
        if(emailExists(physicianForm.email())) throw new Exception("Email already in use");
        if(personalPhysicianIdExists(physicianForm.personalId())) throw new Exception("Personal ID already in use");
        if(adminRepository.getReferenceById(physicianForm.adminId()) == null) throw new Exception("Admin not found");

        Physician physicianEntity = new Physician();
        physicianEntity.setName(physicianForm.name());
        physicianEntity.setLastName(physicianForm.lastName());
        physicianEntity.setPassword(physicianForm.password());
        physicianEntity.setEmail(physicianForm.email());
        physicianEntity.setPersonalId(physicianForm.personalId());
        physicianEntity.setState(AccountState.ACTIVE);
        return physicianEntity;
    }

    @Override
    @Transactional
    public Long registerPatient(RegisterPatientDTO patientForm) throws Exception {
        Patient patientEntity =  new Patient();
        patientEntity.setPassword(patientForm.password());
        patientEntity.setEmail(patientForm.email());
        patientEntity.setPersonalId(patientForm.personalId());
        Patient registeredPatient = patientRepository.save(patientEntity);
        PatientRegistration registration = new PatientRegistration();
        registration.setPatient(registeredPatient);
        patientRegistrationRepository.save(registration);
        return registeredPatient.getId();
    }

    private boolean emailExists(String email){
        return accountRepository.searchByEmail(email)!=null;
    }
    private boolean personalPatientIdExists(String personalId){
        return patientRepository.searchByPersonalId(personalId) != null;
    }
    private boolean personalPhysicianIdExists(String personalId){
        return physicianRepository.searchByPersonalId(personalId) != null;
    }
}
