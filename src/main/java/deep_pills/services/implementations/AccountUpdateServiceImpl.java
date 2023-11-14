package deep_pills.services.implementations;

import deep_pills.dto.accounts.PasswordRecoveryDTO;
import deep_pills.dto.accounts.patient.InfoUpdatePatientDTO;
import deep_pills.dto.accounts.physician.InfoUpdatePhysicianDTO;
import deep_pills.dto.emails.EMailDTO;
import deep_pills.model.entities.accounts.Account;
import deep_pills.model.entities.accounts.users.User;
import deep_pills.model.entities.accounts.users.patients.Patient;
import deep_pills.model.entities.accounts.users.physicians.Physician;
import deep_pills.model.entities.passwordRecovery.PasswordRecoveryRequest;
import deep_pills.model.enums.states.PasswordRecoveryRequestState;
import deep_pills.model.enums.types.EMailType;
import deep_pills.repositories.PasswordRecoveryRequestRepository;
import deep_pills.repositories.accounts.AccountRepository;
import deep_pills.repositories.accounts.users.PatientRepository;
import deep_pills.repositories.accounts.users.PhysicianRepository;
import deep_pills.repositories.accounts.users.UserRepository;
import deep_pills.services.interfaces.AccountUpdateService;
import deep_pills.services.interfaces.EMailService;
import deep_pills.services.interfaces.PicturesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountUpdateServiceImpl implements AccountUpdateService {
    private final PhysicianRepository physicianRepository;
    private final AccountRepository accountRepository;
    private final PatientRepository patientRepository;
    private final PasswordRecoveryRequestRepository passwordRecoveryRequestRepository;
    private final UserRepository userRepository;
    private final EMailService emailService;
    private final PicturesService picturesService;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Override
    @Transactional
    public String updatePhysician(InfoUpdatePhysicianDTO infoUpdatePhysicianDTO) throws Exception {
        Optional<Physician> optional = physicianRepository.findById(infoUpdatePhysicianDTO.id());
        Physician physician = null;
        if(optional.isPresent()){
            physician = optional.get();
        }else throw new Exception("Physician with id: "+infoUpdatePhysicianDTO.id()+" not found");

        // Check if the email is already in use by another user
        Optional<Account> optional2 = accountRepository.findByEmail(infoUpdatePhysicianDTO.email());
        if(optional2.isPresent()) {
            if(optional2.get().getId().equals(infoUpdatePhysicianDTO.id())) {
                throw new Exception("Cannot update physician's email to: "+infoUpdatePhysicianDTO.email()+" because it is already in use by someone");
            }
        }

        // Update physician information based on the DTO
        if(infoUpdatePhysicianDTO.city()!=null) physician.setCity(infoUpdatePhysicianDTO.city());
        if(infoUpdatePhysicianDTO.email()!=null) {
            Optional<Account> op = accountRepository.findByEmail(infoUpdatePhysicianDTO.email());
            if(op.isPresent()){
                if(!op.get().getId().equals(physician.getId())) throw new Exception("Email already in use by another account");
            }
            physician.setEmail(infoUpdatePhysicianDTO.email());
        }
        if(infoUpdatePhysicianDTO.name()!=null) physician.setName(infoUpdatePhysicianDTO.name());
        if(infoUpdatePhysicianDTO.lastName()!=null) physician.setLastName(infoUpdatePhysicianDTO.lastName());
        if(infoUpdatePhysicianDTO.phone()!=null) physician.setPhone(infoUpdatePhysicianDTO.phone());
        if(infoUpdatePhysicianDTO.pic()!=null) physician.setPictureUrl(loadPic(infoUpdatePhysicianDTO.pic()));

        // Save the updated physician information
        physicianRepository.save(physician);

        return "Physician info updates successfully";
    }

    private String loadPic(MultipartFile file) throws Exception {
        return picturesService.subirImagen(file).get("url").toString();
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
        Optional<Account> optional2 = accountRepository.findByEmail(infoUpdatePatientDTO.email());
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
        if (infoUpdatePatientDTO.email() != null){
            Optional<Account> op = accountRepository.findByEmail(infoUpdatePatientDTO.email());
            if(op.isPresent()){
                if(!op.get().getId().equals(patient.getId())) throw new Exception("Email already in use by another account");
            }
            patient.setEmail(infoUpdatePatientDTO.email());
        }
        if (infoUpdatePatientDTO.city() != null) patient.setCity(infoUpdatePatientDTO.city());
        if (infoUpdatePatientDTO.pic()!= null) patient.setPictureUrl(loadPic(infoUpdatePatientDTO.pic()));
        if (infoUpdatePatientDTO.bloodType() != null) patient.setBloodType(infoUpdatePatientDTO.bloodType());
        if (infoUpdatePatientDTO.eps() != null) patient.setEps(infoUpdatePatientDTO.eps());
        // Save the updated patient information
        patientRepository.save(patient);

        return "Patient info updated successfully";
    }

    @Override
    @Transactional
    public Long newPasswordRecoveryRequest(String email) throws Exception {
        Optional<User> optional = userRepository.findByEmail(email);
        if (optional.isEmpty()) throw new Exception("Patient with email: " + email + " not found");

        PasswordRecoveryRequest prr = new PasswordRecoveryRequest();
        prr.setPasswordRecoveryRequestState(PasswordRecoveryRequestState.ACTIVE);
        prr.setUser(optional.get());
        String code = randomCode();
        prr.setCode(encrypt(code));

        prr.setDateTime(new Date());
        prr.setExpirationDateTime(new Date(prr.getDateTime().getTime() + 900000));

        Long id = passwordRecoveryRequestRepository.save(prr).getPasswordRecoveryRequestId();
        emailService.sendEmail(new EMailDTO(
                prr.getUser().getEmail(),
                "This JUAN from the DeepPills Team! We received your password reset request."
                        +"\nUse this code: "+code+" to reset your password at "
                        +"https://www.deeppills.com/pswdRecovery?prrId=" + id + "&code=" + code + "&email=" + prr.getUser().getEmail()
                        +"\nThe code will expire in 15 minutes.",
                "Password reset",
                EMailType.PSWD_RECOVERY,
                id
        ));
        return id;
    }

    @Override
    @Transactional
    public PasswordRecoveryRequestState acceptPasswordRecoveryCode(PasswordRecoveryDTO passwordRecoveryDTO) throws Exception {
        Optional<PasswordRecoveryRequest> prrOptional = passwordRecoveryRequestRepository.findById(passwordRecoveryDTO.passwordRecoveryRequestId());
        if(prrOptional.isEmpty()) throw new Exception("Password recovery request not found");
        PasswordRecoveryRequest prr = prrOptional.get();

        if(prr.getPasswordRecoveryRequestState().equals(PasswordRecoveryRequestState.SOLVED)) throw new Exception("This password recovery request has already been solved");
        if(prr.getPasswordRecoveryRequestState().equals(PasswordRecoveryRequestState.TIMED_OUT)) throw new Exception("This password recovery request has already been timed out");

        Date now = new Date();
        if (prr.getExpirationDateTime().before(now)){
            prr.setPasswordRecoveryRequestState(PasswordRecoveryRequestState.TIMED_OUT);
            passwordRecoveryRequestRepository.save(prr);
            return prr.getPasswordRecoveryRequestState();
        }

        if(!prr.getUser().getEmail().equals(passwordRecoveryDTO.email())) throw new Exception("Email provided does not match the one on the password recovery request");

        if(!encoder.matches(passwordRecoveryDTO.code(), prr.getCode()) )throw new Exception("Incorrect code");

        User user = prr.getUser();
        user.setPassword(encrypt(passwordRecoveryDTO.password()));
        prr.setPasswordRecoveryRequestState(PasswordRecoveryRequestState.SOLVED);

        userRepository.save(user);
        passwordRecoveryRequestRepository.save(prr);
        return prr.getPasswordRecoveryRequestState();
    }
    private String randomCode(){
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder code = new StringBuilder(6);
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(chars.length());
            code.append(chars.charAt(index));
        }

        return code.toString();
    }

    private String encrypt(String code) {
        return encoder.encode(code);
    }

}
