package deep_pills.services.implementations;

import deep_pills.dto.claims.admin.*;
import deep_pills.dto.claims.patient.*;
import deep_pills.dto.emails.EMailDTO;
import deep_pills.model.entities.accounts.Admin;
import deep_pills.model.entities.accounts.users.patients.Patient;
import deep_pills.model.entities.appointments.Appointment;
import deep_pills.model.entities.claims.*;
import deep_pills.model.enums.states.AppointmentState;
import deep_pills.model.enums.states.ClaimState;
import deep_pills.model.enums.types.EMailType;
import deep_pills.model.enums.types.MessageType;
import deep_pills.repositories.accounts.AdminRepository;
import deep_pills.repositories.accounts.users.PatientRepository;
import deep_pills.repositories.accounts.users.PhysicianRepository;
import deep_pills.repositories.appointments.AppointmentRepository;
import deep_pills.repositories.claims.*;
import deep_pills.services.interfaces.ClaimsService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClaimsServiceImpl implements ClaimsService {
    private final ClaimRepository claimRepository;
    private final MessageRepository messageRepository;
    private final AppointmentRepository appointmentRepository;
    private final ClaimInfoRepository claimInfoRepository;
    private final PatientRepository patientRepository;
    private final AdminRepository adminRepository;
    private final EMailServiceImpl eMailService;
    private final PhysicianRepository physicianRepository;

    @Override
    @Transactional
    public List<ClaimItemAdminDTO> listAllClaimsByStatusForAdmin(AdminClaimsSearchDTO adminClaimsSearchDTO) throws Exception {
        return adminClaimMap(claimRepository.findByAdminAndStatus(adminClaimsSearchDTO.adminId(), adminClaimsSearchDTO.claimState()));
    }
    @Override
    @Transactional
    public List<ClaimItemAdminDTO> listAllClaimsByStatus(ClaimState status) throws Exception {
        return adminClaimMap(claimRepository.findByStatus(status));
    }

    @Override
    @Transactional
    public List<ClaimItemAdminDTO> listAllClaimsForAdmin(Long adminId) throws Exception {
        return adminClaimMap(claimRepository.findByAdmin(adminId));
    }

    @Override
    @Transactional
    public List<ClaimItemAdminDTO> listAllClaims() throws Exception {
        return adminClaimMap(claimRepository.findAll());
    }

    private List<ClaimItemAdminDTO> adminClaimMap(List<Claim> claims){
        return claims.stream().map(
                claim -> new ClaimItemAdminDTO(
                        claim.getClaimId(),
                        claim.getClaimDate(),
                        claim.getClaimType(),
                        claim.getClaimInfo().getClaimInfoId(),
                        claim.getClaimStatus())
        ).collect(Collectors.toList());
    }
    private List<ClaimItemPatientDTO> patientClaimMap(List<Claim> claims){
        return claims.stream().map(
                claim -> new ClaimItemPatientDTO(
                        claim.getClaimId(),
                        claim.getClaimDate(),
                        claim.getClaimType(),
                        claim.getClaimStatus())
        ).collect(Collectors.toList());
    }

    private Claim getClaimFromOptional(Optional<Claim> optional){
        return optional.orElse(null);
    }

    @Override
    @Transactional
    public String assignClaimToAdmin(ClaimAssignmentDTO claimAssignmentDTO) throws Exception{
        Claim claim = getClaimFromOptional(claimRepository.findById(claimAssignmentDTO.claimId()));
        Optional<Admin> adminOptional = adminRepository.findById(claimAssignmentDTO.adminId());
        if(adminOptional.isEmpty()) throw new Exception("No admin found for id: " + claimAssignmentDTO.adminId());

        claim.getClaimInfo().setAdmin(adminOptional.get());
        claimRepository.save(claim);

        return "Claim " + claim.getClaimId() +" assigned to admin: " + claim.getClaimInfo().getAdmin().getId();
    }

    @Override
    @Transactional
    public Long addMessageToClaim(ClaimAnswerDTO claimAnswerDTO) throws Exception {
        Claim claim = getClaimFromOptional(claimRepository.findById(claimAnswerDTO.claimId()));

        if(claim == null) throw new Exception("Claim: "+claimAnswerDTO.claimId()+"not found");

        if(!claim.getClaimStatus().equals(ClaimState.ACTIVE)) throw new Exception("Claim not active: "+claim.getClaimStatus());

        Patient patient = claim.getClaimInfo().getPatient();
        Admin admin = claim.getClaimInfo().getAdmin();

        Message message = new Message();
        message.setClaim(claim);
        message.setMessage(claimAnswerDTO.text());
        message.setMessageType(claimAnswerDTO.messageType());

        String email = "";

        if(message.getMessageType().equals(MessageType.ADMIN_PATIENT)){
            message.setSender(admin);
            message.setRecipient(patient);
            email = patient.getEmail();
        }else{
            message.setSender(patient);
            message.setRecipient(admin);
            email = admin.getEmail();
        }

        message.setDate(new Date());
        Message savedMessage = messageRepository.save(message);
        eMailService.sendEmail(new EMailDTO(email,
                "This JUAN from the DeepPills Team! A new message has been sent to the claim "+
                        claim.getClaimId()
                ,
                "New message from Claim",
                EMailType.CLAIM,
                claim.getClaimId()
        ));
        return savedMessage.getMessageId();
    }

    @Override
    @Transactional
    public ClaimItemAdminDTO searchClaimForAdmin(Long claimId) throws Exception {
        Claim claim = getClaimFromOptional(claimRepository.findById(claimId));
        if(claim == null)throw new Exception("No claim found for Id: "+claimId);
        return adminClaimMap(Collections.singletonList(claim)).get(0);
    }

    @Override
    @Transactional
    public ClaimDetailedItemAdminDTO seeClaimDetailsForAdmin(Long claimId) throws Exception {
        Claim claim = getClaimFromOptional(claimRepository.findById(claimId));
        if(claim == null)throw new Exception("No claim found for Id: "+claimId);
        return new ClaimDetailedItemAdminDTO(
                claim.getClaimId(),
                claim.getClaimInfo().getAppointment().getPatient().getPersonalId(),
                claim.getClaimInfo().getAdmin().getId(),
                claim.getClaimInfo().getAppointment().getAppointmentId(),
                claim.getClaimDate(),
                claim.getClaimType(),
                claim.getDetails(),
                claim.getClaimStatus()
        );
    }

    @Override
    @Transactional
    public List<ClaimItemPatientDTO> listAllClaimsByStatusForPatient(@NotNull ClaimListingPatientDTO claimListingPatientDTO) throws Exception {
        return patientClaimMap(claimRepository.findByPatientAndStatus(claimListingPatientDTO.patientPersonalId(), claimListingPatientDTO.claimState()));
    }

    @Override
    @Transactional
    public List<ClaimItemPatientDTO> listAllClaimsForPatient(String patientPersonalId) throws Exception {
        return patientClaimMap(claimRepository.findByPatientPersonalId(patientPersonalId));
    }

    @Override
    @Transactional
    public ClaimItemPatientDTO searchClaimForPatient(ClaimSearchDTO claimSearchDTO) throws Exception {
        Claim claim = getClaimFromOptional(claimRepository.findByIdAndPatientPersonalId(claimSearchDTO.claimId(), claimSearchDTO.patientPersonalId()));
        if(claim == null ) throw new Exception("No claim found for Id: "+claimSearchDTO.claimId()+" for Patient: "+claimSearchDTO.patientPersonalId());
        return patientClaimMap(Collections.singletonList(claim)).get(0);
    }

    @Override
    @Transactional
    public ClaimDetailedItemPatientDTO seeClaimDetailsForPatient(ClaimSearchDTO claimSearchDTO) throws Exception {
        Claim claim = getClaimFromOptional(claimRepository.findByIdAndPatientPersonalId(claimSearchDTO.claimId(), claimSearchDTO.patientPersonalId()));
        if(claim == null ) throw new Exception("No claim found for Id: "+claimSearchDTO.claimId()+" for Patient: "+claimSearchDTO.patientPersonalId());

        List<ClaimMessageDTO> messages = new ArrayList<>();

        for(Message m : claim.getMessages()){
            messages.add(new ClaimMessageDTO(
                    claim.getClaimId(),
                    m.getMessage(),
                    m.getDate(),
                    m.getSender().getId(),
                    m.getRecipient().getId(),
                    m.getMessageType()
            ));
        }

        return new ClaimDetailedItemPatientDTO(
                claim.getClaimId(),
                claim.getClaimInfo().getPatient().getPersonalId(),
                claim.getClaimInfo().getAppointment().getAppointmentId(),
                claim.getClaimDate(),
                claim.getClaimType(),
                claim.getDetails(),
                claim.getClaimStatus(),
                messages
        );
    }

    @Override
    @Transactional
    public Long newClaim(ClaimRegisterDTO claimRegisterDTO) throws Exception {
        Optional<Appointment> appointmentOptional = appointmentRepository.findAppointmentsByIdAndPatientPersonalId(claimRegisterDTO.appointmentId(), claimRegisterDTO.patientPersonalId());
        if(appointmentOptional.isEmpty()) throw new Exception("No appointment found for Id: " + claimRegisterDTO.appointmentId());
        if(claimInfoRepository.countClaimsByAppointmentAndClaimStatus(claimRegisterDTO.appointmentId(), ClaimState.ACTIVE)>0) throw new Exception("Cannot create new claim for appointment: " + claimRegisterDTO.appointmentId()+" because there is already an ACTIVE");

        Appointment appointment = appointmentOptional.get();

        if(!appointment.getAppointmentId().equals(AppointmentState.COMPLETED)) throw new Exception("Cannot create a claim for an unserviced appointment");

        Optional<Patient> patientOptional = patientRepository.findByPersonalId(claimRegisterDTO.patientPersonalId());
        if(patientOptional.isEmpty())throw new Exception("No patient found for personalId: "+claimRegisterDTO.patientPersonalId());

        Patient patient = patientOptional.get();
        for(ClaimInfo cl: appointment.getClaimInfoList()){
            if(cl.getClaim().getClaimStatus().equals(ClaimState.ACTIVE)) throw new Exception("Cannot create new claim for appointment: " + claimRegisterDTO.appointmentId()+ " because there is already an ACTIVE claim for it");
        }
        if(claimRepository.countClaimsByStateAndPatientPersonalId(patient.getPersonalId(), ClaimState.ACTIVE)>3) throw new Exception("Cannot create new claims because there are already 3 active claims");

        Claim claim = new Claim();
        claim.setClaimStatus(ClaimState.ACTIVE);
        claim.setClaimType(claimRegisterDTO.claimType());
        claim.setDetails(claimRegisterDTO.details());
        claim.setClaimDate(new Date());
        Claim savedClaim = claimRepository.save(claim);

        ClaimInfo info = new ClaimInfo();
        info.setClaim(claim);
        info.setAppointment(appointment);
        info.setPatient(patient);
        claimInfoRepository.save(info);
        eMailService.sendEmail(new EMailDTO(patient.getEmail(),
                "This JUAN from the DeepPills Team! A new claim ("+claim.getClaimId()+") has been created for the appointment: "+
                        appointment.getAppointmentId(),

                "New Claim",
                EMailType.CLAIM,
                claim.getClaimId()
                ));
        return savedClaim.getClaimId();
    }
    @Override
    @Transactional
    public Long setClaimState(@NotNull ClaimStateDTO claimStateDTO) throws Exception {
        Claim claim = getClaimFromOptional(claimRepository.findById(claimStateDTO.claimId()));
        if(claim == null) throw new Exception("Claim not found");

        claim.setClaimStatus(claimStateDTO.claimState());
        eMailService.sendEmail(new EMailDTO(claim.getClaimInfo().getPatient().getEmail(),
                "This JUAN from the DeepPills Team! Your claim: "+claim.getClaimId()+" has been set to "+claim.getClaimStatus()+"by an admin",
                "Charge "+claim.getClaimStatus()+"!",
                EMailType.CLAIM,
                claim.getClaimId()
        ));
        return claimRepository.save(claim).getClaimId();
    }

    @Override
    @Transactional
    public Float getPercentageOfSolvedClaims()throws Exception{
        if(claimRepository.count()==0){
            throw new Exception("Cannot calculate percentage, for there are no claims");
        }
        return 100*(claimRepository.countOfClaimsWithState(ClaimState.SOLVED)/claimRepository.count());
    }

    @Override
    @Transactional
    public List<ClaimItemAdminDTO> getClaimsByPhysicianId(@NotNull Long physicianId) throws Exception{
        if(!physicianRepository.existsById(physicianId)){
            throw new Exception("Physician with id " + physicianId + " not found");
        }
        List<ClaimItemAdminDTO> claims = new ArrayList<>();
        for(Claim c: claimRepository.findByPhysicianId(physicianId)){
            claims.add(new ClaimItemAdminDTO(
                    c.getClaimId(),
                    c.getClaimDate(),
                    c.getClaimType(),
                    c.getClaimInfo().getClaimInfoId(),
                    c.getClaimStatus()
            ));
        }
        return claims;
    }
}
