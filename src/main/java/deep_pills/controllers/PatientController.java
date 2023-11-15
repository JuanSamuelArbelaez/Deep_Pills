package deep_pills.controllers;

import deep_pills.dto.accounts.patient.InfoLoadPatientDTO;
import deep_pills.dto.accounts.patient.InfoUpdatePatientDTO;
import deep_pills.dto.accounts.physician.PhysicianListingItemPatientDTO;
import deep_pills.dto.accounts.physician.PhysicianSearchDTO;
import deep_pills.dto.appointments.*;
import deep_pills.dto.claims.admin.ClaimAnswerDTO;
import deep_pills.dto.claims.patient.*;
import deep_pills.dto.controllers.ResponseDTO;
import deep_pills.dto.memberships.*;
import deep_pills.services.interfaces.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/deep_pills/api/patient")
@SecurityRequirement(name = "bearerAuth")
public class PatientController {
    private final AccountUpdateService accountUpdateService;
    private final AppointmentService appointmentService;
    private final ClaimsService claimsService;
    private final MembershipService membershipService;
    private final UserListingService userListingService;


    //Info update
    @PostMapping("/account/info-update")
    public ResponseEntity<ResponseDTO<String>> patientInfoUpdate(@Valid @RequestBody InfoUpdatePatientDTO infoUpdatePatientDTO) throws Exception{
        accountUpdateService.updatePatient(infoUpdatePatientDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Patient info update completed"));
    }

    @GetMapping("/account/load-info/{patientId}")
    public ResponseEntity<ResponseDTO<InfoLoadPatientDTO>> patientInfoUpdate(@PathVariable Long patientId) throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, accountUpdateService.loadPatientInfo(patientId)));
    }


    //Appointments
    @PostMapping("/appointments/cancel-appointment/{appointmentId}")
    public ResponseEntity<ResponseDTO<String>> cancellAppointment(@PathVariable  Long appointmentId) throws Exception{
        appointmentService.cancellAppointment(appointmentId);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Appointment cancelled"));
    }

    @PostMapping("/appointments/reschedule-appointment")
    public ResponseEntity<ResponseDTO<String>> rescheduleAppointment(@Valid @RequestBody AppointmentRescheduleDTO appointmentRescheduleDTO) throws Exception{
        appointmentService.rescheduleAppointment(appointmentRescheduleDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Appointment rescheduled"));
    }

    @PutMapping("/appointments/schedule-appointment")
    public ResponseEntity<ResponseDTO<String>> scheduleAppointment(AppointmentScheduleDTO appointmentScheduleDTO) throws Exception{
        appointmentService.scheduleAppointment(appointmentScheduleDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Appointment scheduled"));
    }

    @GetMapping ("/appointments/list-all/{patientPersonalId}")
    public ResponseEntity<ResponseDTO<List<AppointmentGenericDTO>>> allAppointmentsByPatientId(@PathVariable String patientPersonalId) throws Exception {
        return ResponseEntity.ok(new ResponseDTO<>(true, appointmentService.allAppointmentsByPatientId(patientPersonalId)));
    }

    @GetMapping("/appointments/list-upcoming/{patientPersonalId}")
    public ResponseEntity<ResponseDTO<List<AppointmentGenericDTO>>> upcomingAppointmentsByPatientId(@PathVariable String patientPersonalId) throws Exception {
        return ResponseEntity.ok(new ResponseDTO<>(true, appointmentService.upcomingAppointmentsByPatientId(patientPersonalId)));
    }

    @GetMapping("/appointments/list-past/{patientPersonalId}")
    public ResponseEntity<ResponseDTO<List<AppointmentGenericDTO>>> pastAppointmentsByPatientId(@PathVariable String patientPersonalId) throws Exception {
        return ResponseEntity.ok(new ResponseDTO<>(true, appointmentService.pastAppointmentsByPatientId(patientPersonalId)));
    }

    @GetMapping("/appointments/list-date")
    public ResponseEntity<ResponseDTO<List<AppointmentGenericDTO>>> dateSpecificAppointmentsByPatientId(@Valid @RequestBody AppointmentDatePatientSearchDTO appointmentDatePatientSearchDTO) throws Exception {
        return ResponseEntity.ok(new ResponseDTO<>(true, appointmentService.dateSpecificAppointmentsByPatientId(appointmentDatePatientSearchDTO)));
    }

    @GetMapping("/appointments/list-physicians")
    public ResponseEntity<ResponseDTO<List<PhysicianListingItemPatientDTO>>> listPhysicians(@Valid @RequestBody PhysicianSearchDTO physicianSearchDTO) throws Exception {
        return ResponseEntity.ok(new ResponseDTO<>(true, userListingService.listPhysiciansForPatient(physicianSearchDTO)));
    }


    //Claims
    @PutMapping("/claims/new-claim")
    public ResponseEntity<ResponseDTO<String>> newClaim(@Valid @RequestBody ClaimRegisterDTO claimRegisterDTO) throws Exception{
        claimsService.newClaim(claimRegisterDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "New claim successfully created"));
    }

    @PutMapping("/claims/add-message")
    public ResponseEntity<ResponseDTO<String>> addMessage(@Valid @RequestBody ClaimAnswerDTO claimAnswerDTO) throws Exception{
        claimsService.addMessageToClaim(claimAnswerDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "New message added to the claim"));
    }

    @GetMapping("/claims/list-by-status")
    public ResponseEntity<ResponseDTO<List<ClaimItemPatientDTO>>> listAllSolvedClaims(@Valid @RequestBody ClaimListingPatientDTO claimListingPatientDTO) throws Exception {
        return ResponseEntity.ok(new ResponseDTO<>(false, claimsService.listAllClaimsByStatusForPatient(claimListingPatientDTO)));
    }

    @GetMapping("/claims/list-all/{patientPersonalId}")
    public ResponseEntity<ResponseDTO<List<ClaimItemPatientDTO>>> listAllClaims(@PathVariable String patientPersonalId) throws Exception {
        return ResponseEntity.ok(new ResponseDTO<>(false, claimsService.listAllClaimsForPatient(patientPersonalId)));
    }

    @GetMapping("/claims/claim-search")
    public ResponseEntity<ResponseDTO<ClaimItemPatientDTO>> searchClaim(@Valid @RequestBody ClaimSearchDTO claimSearchDTO) throws Exception {
        return ResponseEntity.ok(new ResponseDTO<>(false, claimsService.searchClaimForPatient(claimSearchDTO)));
    }

    @GetMapping("/claims/claim-details")
    public ResponseEntity<ResponseDTO<ClaimDetailedItemPatientDTO>> seeClaimDetails(@Valid @RequestBody ClaimSearchDTO claimSearchDTO) throws Exception {
        return ResponseEntity.ok(new ResponseDTO<>(false, claimsService.seeClaimDetailsForPatient(claimSearchDTO)));
    }

    //Membership
    @PostMapping("/membership/add-patient")
    public ResponseEntity<ResponseDTO<String>> addPatientToMembership(@Valid @RequestBody MembershipPatientModificationDTO membershipPatientModificationDTO) throws Exception{
        membershipService.addPatientToMembership(membershipPatientModificationDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Patient added successfully to the membership"));
    }

    @PostMapping("/membership/remove-patient")
    public ResponseEntity<ResponseDTO<String>> removePatientFromMembership(@Valid @RequestBody MembershipPatientModificationDTO membershipPatientModificationDTO) throws Exception{
        membershipService.removePatientFromMembership(membershipPatientModificationDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Patient removed successfully from the membership"));
    }

    @PutMapping("/membership/acquire-membership")
    public ResponseEntity<ResponseDTO<String>> acquireMembership(@Valid @RequestBody MembershipAcquirementDTO membershipAcquirementDTO) throws Exception{
        membershipService.acquireMembership(membershipAcquirementDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Membership acquired successfully"));
    }

    @PostMapping("/membership/resign-membership/{patientPersonalId}")
    public ResponseEntity<ResponseDTO<String>> resignMembership(@PathVariable String patientPersonalId) throws Exception{
        membershipService.resignMembership(patientPersonalId);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Membership resigned"));
    }
    @PostMapping("/membership/pay-charge")
    public ResponseEntity<ResponseDTO<String>> makePaymentToMembershipCharge(@Valid @RequestBody MembershipPaymentDTO membershipPaymentDTO) throws Exception{
        membershipService.makePaymentToMembershipCharge(membershipPaymentDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Payment applied to charge successfully"));
    }

    @PostMapping("/membership/view-charges")
    public ResponseEntity<ResponseDTO<List<ChargeDTO>>> getChargesFromMembership(ChargeListDTO chargeListDTO) throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, membershipService.getChargesFromMembership(chargeListDTO)));
    }

    @PostMapping("/membership/view-payments")
    public ResponseEntity<ResponseDTO<List<PaymentDTO>>> getPaymentsFromCharge(PaymentListDTO paymentListDTO) throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, membershipService.getPaymentsFromCharge(paymentListDTO)));
    }

}
