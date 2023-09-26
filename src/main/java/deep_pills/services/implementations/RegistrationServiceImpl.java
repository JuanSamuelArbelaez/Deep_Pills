package deep_pills.services.implementations;

import deep_pills.dto.registrations.*;
import deep_pills.model.entities.accounts.Admin;
import deep_pills.model.entities.accounts.users.patients.Patient;
import deep_pills.model.entities.accounts.users.physicians.Physician;
import deep_pills.model.entities.accounts.users.physicians.PhysicianRegistration;
import deep_pills.model.enums.states.AccountState;
import deep_pills.repositories.accounts.AccountRepository;
import deep_pills.repositories.accounts.AdminRepository;
import deep_pills.repositories.accounts.users.*;
import deep_pills.repositories.accounts.users.registrations.*;
import deep_pills.services.interfaces.RegistrationService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final AdminRepository adminRepository;
    private final AccountRepository accountRepository;
    private final PatientRepository patientRepository;
    private final PatientRegistrationRepository patientRegistrationRepository;
    private final PhysicianRepository physicianRepository;
    private final PhysicianRegistrationRepository physicianRegistrationRepository;
    @Override
    public Long registerPhysician(RegisterPhysicianDTO physicianForm) throws Exception{
        Physician physicianEntity =  new Physician();
        physicianEntity.setPassword(physicianForm.password());
        physicianEntity.setEmail(physicianForm.email());
        physicianEntity.setPersonalId(physicianForm.personalId());
        physicianEntity.setState(AccountState.ACTIVE);
        //physicianEntity.setSpecialization(Specialization.values()[physicianForm.specializationId].name());
        Admin admin = adminRepository.getReferenceById(physicianForm.adminId());
        if(admin == null)throw new Exception("Admin not found");

        Physician registeredPhysician  = physicianRepository.save(physicianEntity);

        PhysicianRegistration registration = new PhysicianRegistration();
        registration.setPhysician(registeredPhysician);
        registration.setAdmin(admin);
        return registeredPhysician.getId();
    }

    @Override
    public Long registerPatient(RegisterPatientDTO patientForm) throws Exception {
        Patient patientEntity =  new Patient();
        patientEntity.setPassword(patientForm.password());
        patientEntity.setEmail(patientForm.email());
        patientEntity.setPersonalId(patientForm.personalId());
        Patient registeredPatient = patientRepository.save(patientEntity);
        return registeredPatient.getId();
    }

    private boolean emailExists(String email){
        return true;//accountRepository.searchByEmail(email);
    }
    private boolean personalPatientIdExists(String personalId){
        return patientRepository.searchByPersonalId(personalId) == null;
    }
    private boolean personalPhysicianIdExists(String personalId){
        return physicianRepository.searchByPersonalId(personalId) == null;
    }
}
