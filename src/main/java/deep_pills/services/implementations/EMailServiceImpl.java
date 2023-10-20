package deep_pills.services.implementations;

import deep_pills.dto.emails.EMailDTO;
import deep_pills.model.entities.accounts.Account;
import deep_pills.model.entities.accounts.users.patients.PatientRegistration;
import deep_pills.model.entities.accounts.users.physicians.PhysicianRegistration;
import deep_pills.model.entities.appointments.Appointment;
import deep_pills.model.entities.claims.Claim;
import deep_pills.model.entities.memberships.Membership;
import deep_pills.model.entities.notifications.EMail;
import deep_pills.model.entities.passwordRecovery.PasswordRecoveryRequest;
import deep_pills.model.enums.states.EmailState;
import deep_pills.repositories.EMails.EMailsRepository;
import deep_pills.repositories.PasswordRecoveryRequestRepository;
import deep_pills.repositories.accounts.AccountRepository;
import deep_pills.repositories.accounts.users.registrations.PatientRegistrationRepository;
import deep_pills.repositories.accounts.users.registrations.PhysicianRegistrationRepository;
import deep_pills.repositories.appointments.AppointmentRepository;
import deep_pills.repositories.claims.ClaimRepository;
import deep_pills.repositories.memberships.MembershipRepository;
import deep_pills.services.interfaces.EMailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EMailServiceImpl implements EMailService {
    private final AccountRepository accountRepository;
    private final MembershipRepository membershipRepository;
    private final AppointmentRepository appointmentRepository;
    private final PasswordRecoveryRequestRepository passwordRecoveryRequestRepository;
    private final PatientRegistrationRepository patientRegistrationRepository;
    private final PhysicianRegistrationRepository physicianRegistrationRepository;
    private final ClaimRepository claimRepository;
    private final EMailsRepository eMailsRepository;
    private final JavaMailSender javaMailSender;
    @Override
    public void sendEmail(EMailDTO emailDTO) throws Exception {
        EMail email = new EMail();
        email.setEmailState(EmailState.SENDED);
        email.setEmailType(emailDTO.emailType());
        email.setBody(emailDTO.body());
        email.setSubject(email.getSubject());
        email.setRecipientEmail(emailDTO.destination());

        switch (emailDTO.emailType()) {
            case APPOINT_SCHEDULED, APPOINT_CANCELLED, APPOINT_RESCHEDULED, APPOINT_SERVED -> {
                Optional<Appointment> appointmentOptional = appointmentRepository.findById(emailDTO.objectId());
                if (appointmentOptional.isEmpty()) throw new Exception("Appointment "+emailDTO.objectId()+" not found");
                email.setAppointment(appointmentOptional.get());
            }
            case MEMB_ACQUIRED, MEMB_RESIGNED, MEMB_PATIENT_ADDED, MEMB_PATIENT_REMOVED, MEMB_STATE_UPDATED, MEMB_CHARGE, MEMB_PAYMENT -> {
                Optional<Membership> membershipOptional = membershipRepository.findById(emailDTO.objectId());
                if (membershipOptional.isEmpty()) throw new Exception("Membership "+emailDTO.objectId()+" not found");
                email.setMembership(membershipOptional.get());
            }
            case ACCOUNT_STATE_UPDATED, FREE_DAY -> {
                Optional<Account> accountOptional = accountRepository.findById(emailDTO.objectId());
                if (accountOptional.isEmpty()) throw new Exception("Account "+emailDTO.objectId()+" not found");
                email.setAccount(accountOptional.get());
            }
            case  REGISTRATION -> {
                Optional<PatientRegistration> registrationOptionalPatient = patientRegistrationRepository.findById(emailDTO.objectId());
                if (registrationOptionalPatient.isEmpty()){
                    Optional<PhysicianRegistration> registrationOptionalPhysician = physicianRegistrationRepository.findById(emailDTO.objectId());
                    if (registrationOptionalPhysician.isEmpty()) throw new Exception("Registration "+emailDTO.objectId()+" not found");
                    email.setUserRegistration(registrationOptionalPhysician.get());
                    email.setAccount(registrationOptionalPhysician.get().getPhysician());
                }
                email.setUserRegistration(registrationOptionalPatient.get());
                email.setAccount(registrationOptionalPatient.get().getPatient());
            }
            case PSWD_RECOVERY -> {
                Optional<PasswordRecoveryRequest> prr = passwordRecoveryRequestRepository.findById(emailDTO.objectId());
                if (prr.isEmpty()) throw new Exception("Password Recovery Request "+emailDTO.objectId()+" not found");
                email.setPasswordRecoveryRequest(prr.get());
                email.setAccount(prr.get().getUser());
            }
            case CLAIM -> {
                Optional<Claim> claimOptional = claimRepository.findById(emailDTO.objectId());
                if(claimOptional.isEmpty()) throw new Exception("Claim "+emailDTO.objectId()+" not found");
                email.setClaim(claimOptional.get());
            }
            default -> throw new Exception("Invalid type of email");
        }

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setSubject(emailDTO.subject());
        helper.setText(emailDTO.body(), true);
        helper.setTo(emailDTO.destination());
        helper.setFrom("no_reply@deep.pills.com");
        javaMailSender.send(message);

        eMailsRepository.save(email);
    }
}
