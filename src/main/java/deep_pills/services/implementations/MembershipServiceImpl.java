package deep_pills.services.implementations;

import deep_pills.dto.emails.EMailDTO;
import deep_pills.dto.memberships.*;
import deep_pills.model.entities.accounts.Admin;
import deep_pills.model.entities.accounts.users.patients.Patient;
import deep_pills.model.entities.memberships.*;
import deep_pills.model.enums.states.ChargeState;
import deep_pills.model.enums.states.MembershipState;
import deep_pills.model.enums.states.PaymentState;
import deep_pills.model.enums.types.EMailType;
import deep_pills.repositories.accounts.AdminRepository;
import deep_pills.repositories.accounts.users.PatientRepository;
import deep_pills.repositories.memberships.*;
import deep_pills.services.interfaces.EMailService;
import deep_pills.services.interfaces.MembershipService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MembershipServiceImpl implements MembershipService {
    private final PatientRepository patientRepository;
    private final MembershipRepository membershipRepository;
    private final PolicyRepository policyRepository;
    private final MembershipChargeRepository membershipChargeRepository;
    private final MembershipPaymentRepository membershipPaymentRepository;
    private final AdminRepository adminRepository;
    private final EMailService eMailService;

    @Override
    @Transactional
    public String addPatientToMembership(@NotNull MembershipPatientModificationDTO membershipPatientModificationDTO) throws Exception{
        Patient owner = getPatientFromOptional(patientRepository.findByPersonalId(membershipPatientModificationDTO.ownerPersonalId()));
        if(owner == null) throw new Exception("No owner with PID: " + membershipPatientModificationDTO.ownerPersonalId()+" found");

        Membership membership = getMembershipFromOptional(membershipRepository.findById(membershipPatientModificationDTO.membershipId()));
        if(membership == null) throw new Exception("No membership with ID: " + membershipPatientModificationDTO.membershipId() + " found");
        if(!membership.getOwner().equals(owner)) throw new Exception("The membership with ID: " + membershipPatientModificationDTO.membershipId() + " isn't owned by patient with PID: " + membershipPatientModificationDTO.ownerPersonalId());
        if(membership.getState().equals(MembershipState.INACTIVE)) throw new Exception("The membership with ID: " + membershipPatientModificationDTO.membershipId() + " is inactive");

        if(membership.getBeneficiaries().size() + 1 >= membership.getPolicy().getMaxPatients()) throw new Exception("The membership with ID: "+membershipPatientModificationDTO.membershipId()+" already has reached its maximum amount of patients");

        Patient patient = getPatientFromOptional(patientRepository.findByPersonalId(membershipPatientModificationDTO.patientPersonalId()));
        if(patient == null) throw new Exception("No patient with PID: " + membershipPatientModificationDTO.patientPersonalId()+" found");

        if(patient.equals(owner)) throw new Exception("Patient cannot add itself to it's membership's beneficiaries");
        if(patient.getBeneficiaryMembership() != null){
            if(patient.getBeneficiaryMembership().equals(membership)) throw new Exception("The patient with PID: " + membershipPatientModificationDTO.patientPersonalId()+" is already a beneficiary of the membership with ID: " + membershipPatientModificationDTO.membershipId());
            else throw new Exception("The patient with PID: "+membershipPatientModificationDTO.patientPersonalId()+" already is a beneficiary of another membership");
        }
        if(patient.getOwnedMembership() != null && !patient.getOwnedMembership().equals(membership)) throw new Exception("The patient with PID: "+membershipPatientModificationDTO.patientPersonalId()+" already owns another membership");

        patient.setBeneficiaryMembership(membership);
        eMailService.sendEmail(new EMailDTO(owner.getEmail(),
                "This JUAN from the DeepPills Team! You just added "+patient.getName()+" to your membership.",
                "New patient added to your membership",
                EMailType.MEMB_PATIENT_ADDED,
                membership.getMembershipId()));
        eMailService.sendEmail(new EMailDTO(patient.getEmail(),
                "This JUAN from the DeepPills Team! "+owner.getName()+" just added you to their membership.",
                "You have been added to a membership",
                EMailType.MEMB_PATIENT_ADDED,
                membership.getMembershipId()));
        return patientRepository.save(patient).getPersonalId();
    }

    @Override
    @Transactional
    public String removePatientFromMembership(@NotNull MembershipPatientModificationDTO membershipPatientModificationDTO) throws Exception{
        Patient owner = getPatientFromOptional(patientRepository.findByPersonalId(membershipPatientModificationDTO.ownerPersonalId()));
        if(owner == null) throw new Exception("No owner with PID: " + membershipPatientModificationDTO.ownerPersonalId()+" found");

        Membership membership = getMembershipFromOptional(membershipRepository.findById(membershipPatientModificationDTO.membershipId()));
        if(membership == null) throw new Exception("No membership with ID: " + membershipPatientModificationDTO.membershipId() + " found");
        if(!membership.getOwner().equals(owner)) throw new Exception("The membership with ID: " + membershipPatientModificationDTO.membershipId() + " isn't owned by patient with PID: " + membershipPatientModificationDTO.ownerPersonalId());
        if(membership.getState().equals(MembershipState.INACTIVE)) throw new Exception("The membership with ID: " + membershipPatientModificationDTO.membershipId() + " is inactive");

        Patient patient = getPatientFromOptional(patientRepository.findByPersonalId(membershipPatientModificationDTO.patientPersonalId()));
        if(patient == null) throw new Exception("No patient with PID: " + membershipPatientModificationDTO.patientPersonalId()+" found");

        if(patient.equals(owner)) throw new Exception("Patient cannot remove itself from it's membership's beneficiaries");
        if(!membership.getBeneficiaries().contains(patient)) throw new Exception("Patient with PID: " + membershipPatientModificationDTO.patientPersonalId() + " isn't a beneficiary of the membership with ID: "+membershipPatientModificationDTO.membershipId());
        membership.getBeneficiaries().remove(patient);
        eMailService.sendEmail(new EMailDTO(owner.getEmail(),
                "This JUAN from the DeepPills Team! You just removed "+patient.getName()+" from your membership.",
                "Patient removed from your membership",
                EMailType.MEMB_PATIENT_REMOVED,
                membership.getMembershipId()));
        eMailService.sendEmail(new EMailDTO(patient.getEmail(),
                "This JUAN from the DeepPills Team! "+owner.getName()+" just removed you from their membership.",
                "You have been removed from a membership",
                EMailType.MEMB_PATIENT_REMOVED,
                membership.getMembershipId()));
        return patientRepository.save(patient).getPersonalId();
    }

    @Override
    @Transactional
    public Long acquireMembership(MembershipAcquirementDTO membershipAcquirementDTO) throws Exception{
        Patient patient = getPatientFromOptional(patientRepository.findByPersonalId(membershipAcquirementDTO.patientPersonalId()));
        if(patient == null) throw new Exception("No patient with id: " + membershipAcquirementDTO.patientPersonalId()+" found");

        Policy policy = getPolicyFromOptional(policyRepository.findById(membershipAcquirementDTO.policyId()));

        if(patient.getBeneficiaryMembership()!=null) throw new Exception("Cannot acquire membership policy: " + membershipAcquirementDTO.policyId() + " because patient with PID: "+membershipAcquirementDTO.patientPersonalId()+" is already a beneficiary of a membership");

        Membership membership = patient.getOwnedMembership();
        if(patient.getOwnedMembership() == null) {
            membership = new Membership();
            membership.setDate(new Date());
            membership.setOwner(patient);
            membership.setState(MembershipState.ACTIVE);
            membership.setPolicy(policy);
        } else if(policy.getMaxPatients()>membership.getPolicy().getMaxPatients()) throw new Exception("Cannot update membership's current policy to policy: "+membershipAcquirementDTO.policyId()+" because the membership's current policy's amount of patients ("+(membership.getBeneficiaries().size()+1)+") exceeds the target policy's maximum amount of patients ("+policy.getMaxPatients()+")");
        Long id = membershipRepository.save(membership).getMembershipId();
        eMailService.sendEmail(new EMailDTO(patient.getEmail(),
                "This JUAN from the DeepPills Team! You just acquired a new membership: "+membership.getMembershipId()+" ("+policy.getName()+")",
                "New membership",
                EMailType.MEMB_ACQUIRED,id));
        return id;
    }

    @Override
    @Transactional
    public Long resignMembership(String patientPersonalId) throws Exception {
        Patient patient = getPatientFromOptional(patientRepository.findByPersonalId(patientPersonalId));
        if(patient == null) throw new Exception("No patient with PID: " + patientPersonalId+" found");

        Membership membership = patient.getBeneficiaryMembership();
        if(membership == null) {
            membership = patient.getOwnedMembership();
            if(membership == null) throw new Exception("Patient with PID: "+patientPersonalId+" has no membership to resign to");
            if(membership.getBeneficiaries().size()>0) throw new Exception("Patient with PID: "+patientPersonalId+" cannot resign to it's owned membership, for it has beneficiaries");
            membership.setOwner(null);
        }else membership.getBeneficiaries().remove(patient);
        membership.setState(MembershipState.INACTIVE);
        eMailService.sendEmail(new EMailDTO(patient.getEmail(),
                "This JUAN from the DeepPills Team! You just resigned from your membership: "+membership.getMembershipId()+" ("+membership.getPolicy().getName()+")",
                "Resigned from Membership",
                EMailType.MEMB_RESIGNED,
                membership.getMembershipId()));
        return membership.getMembershipId();
    }

    @Override
    @Transactional
    public List<Long> chargeCurrentMonthToMemberships() throws Exception{
        List<Long> charges = new ArrayList<>();

        List<Membership> memberships = membershipRepository.findMembershipWithNonInactiveStateAndNoChargesInCurrentMonth(MembershipState.INACTIVE);

        for (Membership membership : memberships) {
            MembershipCharge membershipCharge = new MembershipCharge();
            membershipCharge.setMembership(membership);
            membershipCharge.setChargeState(ChargeState.ISSUED);
            membershipCharge.setDateTime(new Date());
            membershipCharge.setChargeAmount(membership.getPolicy().getCost());
            charges.add(membershipChargeRepository.save(membershipCharge).getMembershipChargeId());
            eMailService.sendEmail(new EMailDTO(membership.getOwner().getEmail(),
                    "This JUAN from the DeepPills Team! This month's charge for your membership: "+membership.getMembershipId()+" ("+membership.getPolicy().getName()+") has just been issued: "+membershipCharge.getMembershipChargeId(),
                    "Membership Charge",
                    EMailType.MEMB_CHARGE,
                    membership.getMembershipId()
            ));
        }
        return charges;
    }

    @Override
    @Transactional
    public Long makePaymentToMembershipCharge(MembershipPaymentDTO membershipPaymentDTO) throws Exception {
        Patient patient = getPatientFromOptional(patientRepository.findByPersonalId(membershipPaymentDTO.patientPersonalId()));
        if(patient == null) throw new Exception("No patient with PID: " + membershipPaymentDTO.patientPersonalId()+" found");

        if(patient.getOwnedMembership() == null || patient.getBeneficiaryMembership() == null) throw new Exception("Patient with PID: " + membershipPaymentDTO.patientPersonalId()+" does not have any membership to pay charges for.");

        MembershipCharge charge = getChargeFromOptional(membershipChargeRepository.findById(membershipPaymentDTO.membershipChargeId()));
        if(charge == null) throw new Exception("No charge with ID: " + membershipPaymentDTO.membershipChargeId()+" found");

        if(patient.getBeneficiaryMembership() != null){
            if(patient.getBeneficiaryMembership().equals(charge.getMembership()))throw new Exception("Patient with PID: " + membershipPaymentDTO.patientPersonalId()+" cannot pay for a charge to it's beneficiary membership. Only thw owner can pay for it.");
            else throw new Exception("Patient with PID: " + membershipPaymentDTO.patientPersonalId()+" cannot pay for a charge to a membership it is not related to");
        }

        if(patient.getOwnedMembership() != null) {
            if(!patient.getOwnedMembership().equals(charge.getMembership())) throw new Exception("Patient with PID: "+membershipPaymentDTO.patientPersonalId()+" cannot pay for a charge to a membership it is not related to");
        }

        double payedAmount = membershipPaymentRepository.getTotalPaymentAmountByStateAndChargeId(PaymentState.COMPLETED, charge.getMembershipChargeId());

        if(membershipPaymentDTO.amount() > charge.getChargeAmount() - payedAmount) throw new Exception("Patient with PID: "+membershipPaymentDTO.patientPersonalId()+" cannot pay the target amount ("+membershipPaymentDTO.amount()+"), for it would make the total amount payed to the charge exceed the amount charged");

        MembershipPayment payment = new MembershipPayment();
        payment.setMembershipCharge(charge);
        payment.setAmount(membershipPaymentDTO.amount());
        payment.setConcept("Payment of "+membershipPaymentDTO.amount() +" onto charge: "+ membershipPaymentDTO.membershipChargeId());
        Long confirmation = newTransaction(payment);
        payment.setPaymentState(PaymentState.COMPLETED);
        payment.setDateTime(new Date());
        Long id = membershipPaymentRepository.save(payment).getMembershipPaymentId();
        if(payedAmount + payment.getAmount() == charge.getChargeAmount()) {
            charge.setChargeState(ChargeState.FULLFILLED);
            membershipChargeRepository.save(charge);
        }
        eMailService.sendEmail(new EMailDTO(charge.getMembership().getOwner().getEmail(),
                "This JUAN from the DeepPills Team! A payment for: "+payment.getAmount()+
                        " has just been made to the charge: "+charge.getMembershipChargeId()+
                        "\nThe charge has a remaining amount of "+(charge.getChargeAmount()-payedAmount-payment.getAmount()),
                "Membership Payment",
                EMailType.MEMB_PAYMENT,
                charge.getMembership().getMembershipId()));
        return id;
    }

    @Override
    @Transactional
    public List<Long> setArrearMemberships()throws Exception{
        List<Long> memberships = new ArrayList<>();

        List<Membership> membershipsFromActiveToArrear = membershipRepository.findMembershipsToBeInArrear(MembershipState.ACTIVE, ChargeState.ISSUED);
        for(Membership membership : membershipsFromActiveToArrear){
            membership.setState(MembershipState.ARREAR);
            memberships.add(membershipRepository.save(membership).getMembershipId());
            eMailService.sendEmail(new EMailDTO(membership.getOwner().getEmail(),
                    "This JUAN from the DeepPills Team! Your membership: "+membership.getMembershipId()+" has been set to ARREAR, for there are pending charges to it to be fulfilled",
                    "Membership In Arrear",
                    EMailType.MEMB_STATE_UPDATED,
                    membership.getMembershipId()
                    ));
        }

        return memberships;
    }

    @Override
    @Transactional
    public List<Long> setActiveMemberships()throws Exception {
        List<Long> memberships = new ArrayList<>();

        List<Membership> membershipsFromArrearToActive = membershipRepository.findMembershipsToNoLongerBeInArrear(MembershipState.ARREAR, ChargeState.ISSUED);
        for(Membership membership : membershipsFromArrearToActive){
            membership.setState(MembershipState.ACTIVE);
            memberships.add(membershipRepository.save(membership).getMembershipId());
            eMailService.sendEmail(new EMailDTO(membership.getOwner().getEmail(),
                    "This JUAN from the DeepPills Team! All of your membership: "+membership.getMembershipId()+" charges are up to date, and your membership is back to ACTIVE",
                    "Membership Back to ACTIVE",
                    EMailType.MEMB_STATE_UPDATED,
                    membership.getMembershipId()
            ));
        }

        return memberships;
    }

    @Override
    @Transactional
    public Long setChargeState(@NotNull ChargeStateUpdateDTO chargeStateUpdateDTO) throws Exception {
        Admin admin = getAdminFromOptional(adminRepository.findById(chargeStateUpdateDTO.adminId()));
        if(admin == null) throw new Exception("Admin not found");

        MembershipCharge charge = getChargeFromOptional(membershipChargeRepository.findById(chargeStateUpdateDTO.membershipChargeId()));
        if(charge == null) throw new Exception("No charge with ID: "+chargeStateUpdateDTO.membershipChargeId()+" found");

        charge.setChargeState(chargeStateUpdateDTO.state());

        eMailService.sendEmail(new EMailDTO(charge.getMembership().getOwner().getEmail(),
                "This JUAN from the DeepPills Team! The charge: "+charge.getMembershipChargeId()+" to your membership has been set to "+charge.getChargeState()+"by an admin",
                "Charge "+charge.getChargeState()+"!",
                EMailType.MEMB_STATE_UPDATED,
                charge.getMembership().getMembershipId()
        ));
        return membershipChargeRepository.save(charge).getMembershipChargeId();
    }

    @Override
    @Transactional
    public Long setMembershipState(@NotNull MembershipStateUpdateDTO membershipStateUpdateDTO) throws Exception {
        Admin admin = getAdminFromOptional(adminRepository.findById(membershipStateUpdateDTO.adminId()));
        if(admin == null) throw new Exception("Admin not found");

        Membership membership = getMembershipFromOptional(membershipRepository.findById(membershipStateUpdateDTO.membershipId()));
        if(membership == null) throw new Exception("No charge with ID: "+membershipStateUpdateDTO.membershipId()+" found");

        membership.setState(membershipStateUpdateDTO.state());
        eMailService.sendEmail(new EMailDTO(membership.getOwner().getEmail(),
                "This JUAN from the DeepPills Team! Your membership: "+membership.getMembershipId()+" has been set to "+membership.getState()+"by an admin",
                "Charge "+membership.getState()+"!",
                EMailType.MEMB_STATE_UPDATED,
                membership.getMembershipId()
        ));
        return membershipRepository.save(membership).getMembershipId();
    }

    @Override
    @Transactional
    public List<ChargeDTO> getChargesFromMembership(ChargeListDTO chargeListDTO) throws Exception {
        Patient patient = getPatientFromOptional(patientRepository.findByPersonalId(chargeListDTO.patientPersonalId()));
        if(patient == null) throw new Exception("No patient with ID: "+chargeListDTO.patientPersonalId()+" found");

        Membership membership = getMembershipFromOptional(membershipRepository.findById(chargeListDTO.membershipId()));
        if(membership == null) throw new Exception("No membership with ID: "+chargeListDTO.membershipId()+" found");

        if(patient.getBeneficiaryMembership()!=null) {
            if (patient.getBeneficiaryMembership().equals(membership))
                throw new Exception("Beneficiaries cannot see membership charges");
            else throw new Exception("Cannot see unrelated membership charges");
        }else if(patient.getOwnedMembership()!=null){
            if (!patient.getOwnedMembership().equals(membership)) throw new Exception("Cannot see unrelated membership charges");
        }else throw new Exception("Patient does not have any membership");


        List<ChargeDTO> charges = new ArrayList<>();
        for(MembershipCharge charge : membership.getMembershipCharges()){
            charges.add(new ChargeDTO(
                    charge.getMembershipChargeId(),
                    charge.getChargeAmount(),
                    charge.getChargeState(),
                    charge.getDateTime()
            ));
        }
        return charges;
    }
    @Override
    @Transactional
    public List<PaymentDTO> getPaymentsFromCharge(PaymentListDTO paymentListDTO) throws Exception {
        Patient patient = getPatientFromOptional(patientRepository.findByPersonalId(paymentListDTO.patientPersonalId()));
        if(patient == null) throw new Exception("No patient with ID: "+paymentListDTO.patientPersonalId()+" found");

        MembershipCharge charge = getChargeFromOptional(membershipChargeRepository.findById(paymentListDTO.chargeId()));
        if(charge == null) throw new Exception("No membership with ID: "+paymentListDTO.chargeId()+" found");

        if(patient.getBeneficiaryMembership()!=null) {
            if (patient.getBeneficiaryMembership().equals(charge.getMembership()))
                throw new Exception("Beneficiaries cannot see membership charges");
            else throw new Exception("Cannot see unrelated membership charges");
        }else if(patient.getOwnedMembership()!=null){
            if (!patient.getOwnedMembership().equals(charge.getMembership())) throw new Exception("Cannot see unrelated membership charges");
        }else throw new Exception("Patient does not have any membership");


        List<PaymentDTO> payments = new ArrayList<>();
        for(MembershipPayment payment : charge.getMembershipPaymentList()){
            payments.add(new PaymentDTO(
                    payment.getMembershipPaymentId(),
                    payment.getAmount(),
                    payment.getDateTime(),
                    payment.getConcept(),
                    payment.getPaymentState()
            ));
        }
        return payments;
    }

    @Override
    @Transactional
    public Long getPatientsMembership(String patientPersonId) throws Exception{
        Patient patient = getPatientFromOptional(patientRepository.findByPersonalId(patientPersonId));
        if(patient == null) throw new Exception("Patient by PID: " + patientPersonId+" not found");

        if(patient.getOwnedMembership() != null) return patient.getOwnedMembership().getMembershipId();
        if(patient.getBeneficiaryMembership() != null) return patient.getBeneficiaryMembership().getMembershipId();

        return null;
    }

    private Long newTransaction(MembershipPayment payment) throws Exception {
        //Implement payment APIS
        // return API transaction confirmation number
        return 1L;
    }

    private Patient getPatientFromOptional(Optional<Patient> optional){
        return optional.orElse(null);
    }

    private Membership getMembershipFromOptional(Optional<Membership> optional){
        return optional.orElse(null);
    }

    private Policy getPolicyFromOptional(Optional<Policy> optional){
        return optional.orElse(null);
    }
    private MembershipCharge getChargeFromOptional(Optional<MembershipCharge> optional){
        return optional.orElse(null);
    }
    private Admin getAdminFromOptional(Optional<Admin> optional){
        return optional.orElse(null);
    }


}
