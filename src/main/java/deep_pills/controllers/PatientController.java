package deep_pills.controllers;

import deep_pills.dto.accounts.patient.InfoUpdatePatientDTO;
import deep_pills.dto.accounts.physician.PhysicianSearchDTO;
import deep_pills.dto.appointments.AppointmentDateSearchDTO;
import deep_pills.dto.appointments.AppointmentRescheduleDTO;
import deep_pills.dto.appointments.AppointmentScheduleDTO;
import deep_pills.dto.claims.admin.ClaimAnswerDTO;
import deep_pills.dto.claims.patient.*;
import deep_pills.dto.controllers.ResponseDTO;
import deep_pills.dto.logins.LoginDTO;
import deep_pills.dto.memberships.*;
import deep_pills.dto.registrations.RegisterPatientDTO;
import deep_pills.services.interfaces.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/deep_pills/patient")
public class PatientController {
    private final AccountUpdateService accountUpdateService;
    private final AppointmentService appointmentService;
    private final AuthenticationService authenticationService;
    private final ClaimsService claimsService;
    private final MembershipService membershipService;
    private final RegistrationService registrationService;
    private final UserListingService userListingService;
    private final UserManagementService userManagementService;


    //Registration, Account Management and Authentication
    @PostMapping("/account/login")
    public ResponseEntity<ResponseDTO<String>> patientLogin(@Valid @RequestBody LoginDTO loginDTO) throws Exception{
        authenticationService.login(loginDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Patient Logged in"));
    }
    @PostMapping("/account/register")
    public ResponseEntity<ResponseDTO<String>> patientRegistration(@Valid @RequestBody RegisterPatientDTO registerPatientDTO) throws Exception{
        registrationService.registerPatient(registerPatientDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Patient registration completed"));
    }

    @PutMapping("/account/info-update")
    public ResponseEntity<ResponseDTO<String>> patientInfoUpdate(@Valid @RequestBody InfoUpdatePatientDTO infoUpdatePatientDTO) throws Exception{
        accountUpdateService.updatePatient(infoUpdatePatientDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Patient info update completed"));
    }

    @DeleteMapping("/account/delete-account/{patientId}")
    public ResponseEntity<ResponseDTO<String>> deletePatientAccount(@PathVariable Long patientId) throws Exception{
        userManagementService.disablePatient(patientId);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Patient account deleted"));
    }

    @PutMapping("/account/password-recovery/{patientEmail}")
    public ResponseEntity<ResponseDTO<String>> patientPasswordRecovery(@PathVariable String patientEmail) throws Exception{
        accountUpdateService.newPasswordRecoveryRequest(patientEmail);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Patient password recovery request successful"));
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
    public ResponseEntity<ResponseDTO<String>> allAppointmentsByPatientId(@PathVariable String patientPersonalId) throws Exception {
        appointmentService.allAppointmentsByPatientId(patientPersonalId);
        return ResponseEntity.ok(new ResponseDTO<>(true, "Appointments loaded successfully"));
    }

    @GetMapping("/appointments/list-upcoming/{patientPersonalId}")
    public ResponseEntity<ResponseDTO<String>> upcomingAppointmentsByPatientId(@PathVariable String patientPersonalId) throws Exception {
        appointmentService.upcomingAppointmentsByPatientId(patientPersonalId);
        return ResponseEntity.ok(new ResponseDTO<>(true, "Appointments loaded successfully"));
    }

    @GetMapping("/appointments/list-past/{patientPersonalId}")
    public ResponseEntity<ResponseDTO<String>> pastAppointmentsByPatientId(@PathVariable String patientPersonalId) throws Exception {
        appointmentService.pastAppointmentsByPatientId(patientPersonalId);
        return ResponseEntity.ok(new ResponseDTO<>(true, "Appointments loaded successfully"));
    }

    @GetMapping("/appointments/list-date")
    public ResponseEntity<ResponseDTO<String>> dateSpecificAppointmentsByPatientId(@Valid @RequestBody AppointmentDateSearchDTO appointmentDateSearchDTO) throws Exception {
        appointmentService.dateSpecificAppointmentsByPatientId(appointmentDateSearchDTO);
        return ResponseEntity.ok(new ResponseDTO<>(true, "Appointments loaded successfully"));
    }

    @GetMapping("/appointments/list-physicians")
    public ResponseEntity<ResponseDTO<String>> listPhysicians(@Valid @RequestBody PhysicianSearchDTO physicianSearchDTO) throws Exception {
        userListingService.listPhysiciansForPatient(physicianSearchDTO);
        return ResponseEntity.ok(new ResponseDTO<>(true, "Physicians loaded successfully"));
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
    public ResponseEntity<ResponseDTO<String>> listAllSolvedClaims(@Valid @RequestBody ClaimListingPatientDTO claimListingPatientDTO) throws Exception {
        claimsService.listAllClaimsByStatusForPatient(claimListingPatientDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Claims listed successfully"));
    }

    @GetMapping("/claims/list-all/{patientPersonalId}")
    public ResponseEntity<ResponseDTO<String>> listAllClaims(@PathVariable String patientPersonalId) throws Exception {
        claimsService.listAllClaimsForPatient(patientPersonalId);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Claims listed successfully"));
    }

    @GetMapping("/claims/claim-search")
    public ResponseEntity<ResponseDTO<String>> searchClaim(@Valid @RequestBody ClaimSearchDTO claimSearchDTO) throws Exception {
        claimsService.searchClaimForPatient(claimSearchDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Claim search completed successfully"));
    }

    @GetMapping("/claims/claim-details")
    public ResponseEntity<ResponseDTO<String>> seeClaimDetails(@Valid @RequestBody ClaimSearchDTO claimSearchDTO) throws Exception {
        claimsService.seeClaimDetailsForPatient(claimSearchDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Claim details loaded successfully"));
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
    public ResponseEntity<ResponseDTO<String>> getChargesFromMembership(ChargeListDTO chargeListDTO) throws Exception{
        membershipService.getChargesFromMembership(chargeListDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Charges loaded successfully"));
    }

    @PostMapping("/membership/view-payments")
    public ResponseEntity<ResponseDTO<String>> getPaymentsFromCharge(PaymentListDTO paymentListDTO) throws Exception{
        membershipService.getPaymentsFromCharge(paymentListDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Payments loaded successfully"));
    }

}
