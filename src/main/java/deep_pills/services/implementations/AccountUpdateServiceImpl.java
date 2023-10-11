package deep_pills.services.implementations;

import deep_pills.dto.accounts.patient.InfoUpdatePatientDTO;
import deep_pills.dto.accounts.physician.InfoUpdatePhysicianDTO;
import deep_pills.model.entities.accounts.Account;
import deep_pills.model.entities.accounts.users.patients.Patient;
import deep_pills.model.entities.accounts.users.physicians.Physician;
import deep_pills.repositories.accounts.AccountRepository;
import deep_pills.repositories.accounts.users.PatientRepository;
import deep_pills.repositories.accounts.users.PhysicianRepository;
import deep_pills.services.interfaces.AccountUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountUpdateServiceImpl implements AccountUpdateService {
    private final PhysicianRepository physicianRepository;
    private final AccountRepository accountRepository;
    private final PatientRepository patientRepository;

    @Override
    @Transactional
    public String updatePhysician(InfoUpdatePhysicianDTO infoUpdatePhysicianDTO) throws Exception {
        Optional<Physician> optional = physicianRepository.findById(infoUpdatePhysicianDTO.id());
        Physician physician = null;
        if(optional.isPresent()){
            physician = optional.get();
        }else throw new Exception("Physician with id: "+infoUpdatePhysicianDTO.id()+" not found");

        // Check if the email is already in use by another user
        Optional<Account> optional2 = accountRepository.fingByEmail(infoUpdatePhysicianDTO.email());
        if(optional2.isPresent()) {
            if(optional2.get().getId().equals(infoUpdatePhysicianDTO.id())) {
                throw new Exception("Cannot update physician's email to: "+infoUpdatePhysicianDTO.email()+" because it is already in use by someone");
            }
        }

        // Update physician information based on the DTO
        if(infoUpdatePhysicianDTO.city()!=null) physician.setCity(infoUpdatePhysicianDTO.city());
        if(infoUpdatePhysicianDTO.email()!=null) physician.setEmail(infoUpdatePhysicianDTO.email());
        if(infoUpdatePhysicianDTO.name()!=null) physician.setName(infoUpdatePhysicianDTO.name());
        if(infoUpdatePhysicianDTO.lastName()!=null) physician.setLastName(infoUpdatePhysicianDTO.lastName());
        if(infoUpdatePhysicianDTO.phone()!=null) physician.setPhone(infoUpdatePhysicianDTO.phone());
        if(infoUpdatePhysicianDTO.pictureURL()!=null) physician.setPictureUrl(infoUpdatePhysicianDTO.pictureURL());

        // Save the updated physician information
        physicianRepository.save(physician);

        return "Physician info updates successfully";
    }

    @Override
    @Transactional
    public String updatePatient(InfoUpdatePatientDTO infoUpdatePatientDTO) throws Exception {
        Optional<Patient> optional = patientRepository.findById(infoUpdatePatientDTO.id());
        Patient patient = null;
        if (optional.isPresent()) {
            patient = optional.get();
        } else {
            throw new Exception("Patient with id: " + infoUpdatePatientDTO.id() + " not found");
        }

        // Check if the email is already in use by another user
        Optional<Account> optional2 = accountRepository.fingByEmail(infoUpdatePatientDTO.email());
        if (optional2.isPresent()){
            if(optional2.get().getId().equals(infoUpdatePatientDTO.id())) {
                throw new Exception("Cannot update patient's email to: " + infoUpdatePatientDTO.email() + " because it is already in use by someone");
            }
        }

        // Update patient information based on the DTO
        if (infoUpdatePatientDTO.name() != null) patient.setName(infoUpdatePatientDTO.name());
        if (infoUpdatePatientDTO.lastName() != null)patient.setLastName(infoUpdatePatientDTO.lastName());
        if (infoUpdatePatientDTO.dateOfBirth() != null) patient.setDateOfBirth(infoUpdatePatientDTO.dateOfBirth());
        if (infoUpdatePatientDTO.phone() != null) patient.setPhone(infoUpdatePatientDTO.phone());
        if (infoUpdatePatientDTO.email() != null) patient.setEmail(infoUpdatePatientDTO.email());
        if (infoUpdatePatientDTO.city() != null) patient.setCity(infoUpdatePatientDTO.city());
        if (infoUpdatePatientDTO.pictureURL() != null) patient.setPictureUrl(infoUpdatePatientDTO.pictureURL());
        if (infoUpdatePatientDTO.bloodType() != null) patient.setBloodType(infoUpdatePatientDTO.bloodType());

        // Save the updated patient information
        patientRepository.save(patient);

        return "Patient info updated successfully";
    }
}
