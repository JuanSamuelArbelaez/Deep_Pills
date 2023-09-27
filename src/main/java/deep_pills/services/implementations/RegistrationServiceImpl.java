package deep_pills.services.implementations;

import deep_pills.dto.registrations.*;
import deep_pills.model.entities.accounts.Admin;
import deep_pills.model.entities.accounts.users.patients.Patient;
import deep_pills.model.entities.accounts.users.physicians.*;
import deep_pills.model.enums.lists.Specialization;
import deep_pills.model.enums.states.AccountState;
import deep_pills.repositories.accounts.*;
import deep_pills.repositories.accounts.users.*;
import deep_pills.repositories.accounts.users.registrations.*;
import deep_pills.repositories.specializations.*;
import deep_pills.services.interfaces.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
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
    @Override
    @Transactional
    public Long registerPhysician(RegisterPhysicianDTO physicianForm) throws Exception{
        //Pre-validation of the DTO and its attributes
        if(physicianForm == null) throw new IllegalArgumentException("DTO cannot be null");
        if(physicianForm.email() == null) throw new IllegalArgumentException("Email cannot be null in the login DTO");
        if(physicianForm.password() == null) throw new IllegalArgumentException("Password cannot be null in the login DTO");
        if(physicianForm.adminId() == null) throw new IllegalArgumentException("Admin ID cannot be null in the login DTO");
        if(physicianForm.personalId() == null) throw new IllegalArgumentException("Personal ID cannot be null in the login DTO");


        if(emailExists(physicianForm.email())) throw new Exception("Email already in use");
        if(personalPhysicianIdExists(physicianForm.personalId())) throw new Exception("Personal ID already in use");
        Admin admin = adminRepository.getReferenceById(physicianForm.adminId());
        if(admin == null)throw new Exception("Admin not found");

        Physician physicianEntity =  new Physician();
        physicianEntity.setPassword(physicianForm.password());
        physicianEntity.setEmail(physicianForm.email());
        physicianEntity.setPersonalId(physicianForm.personalId());
        physicianEntity.setState(AccountState.ACTIVE);


        //Section for implementation of specializations if required during registration

        for(Integer specializationId: physicianForm.specializationsId()){
            if(specializationId<0 || specializationId>=physicianForm.specializationsId().size())throw new Exception("Invalid specialization ID: " + specializationId);
        }

        List<PhysicianSpecialization> specializations = new ArrayList<>();
        for(Integer specializationId : physicianForm.specializationsId()){
            PhysicianSpecialization physicianSpecialization = new PhysicianSpecialization();
            physicianSpecialization.setPhysician(physicianEntity);
            physicianSpecialization.setSpecialization(Specialization.values()[specializationId]);
            specializations.add(physicianSpecialization);
        }

        Physician registeredPhysician  = physicianRepository.save(physicianEntity);
        PhysicianRegistration registration = new PhysicianRegistration();
        registration.setPhysician(registeredPhysician);
        registration.setAdmin(admin);

        for(PhysicianSpecialization physicianSpecialization : specializations){
            physicianSpecializationRepository.equals(physicianSpecialization);
        }

        return registeredPhysician.getId();
    }

    @Override
    @Transactional
    public Long registerPatient(RegisterPatientDTO patientForm) throws Exception {
        Patient patientEntity =  new Patient();
        patientEntity.setPassword(patientForm.password());
        patientEntity.setEmail(patientForm.email());
        patientEntity.setPersonalId(patientForm.personalId());
        Patient registeredPatient = patientRepository.save(patientEntity);
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
