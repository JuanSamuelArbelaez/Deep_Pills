package deep_pills.controllers;

import deep_pills.dto.accounts.patient.PatientSearchDTO;
import deep_pills.dto.accounts.physician.PhysicianSearchDTO;
import deep_pills.dto.claims.admin.AdminClaimsSearchDTO;
import deep_pills.dto.claims.admin.ClaimAnswerDTO;
import deep_pills.dto.claims.admin.ClaimAssignmentDTO;
import deep_pills.dto.claims.admin.ClaimStateDTO;
import deep_pills.dto.controllers.ResponseDTO;
import deep_pills.dto.memberships.*;
import deep_pills.dto.registrations.RegisterPhysicianDTO;
import deep_pills.model.enums.states.ClaimState;
import deep_pills.services.interfaces.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/deep_pills/admin")
public class AdminController {
    private final ClaimsService claimsService;
    private final MembershipService membershipService;
    private final PolicyService policyService;
    private final RegistrationService registrationService;
    private final UserListingService userListingService;
    private final UserManagementService userManagementService;


    //Physician Management
    @PutMapping("/physician/register")
    public ResponseEntity<ResponseDTO<String>> physicianRegistration(@Valid @RequestBody RegisterPhysicianDTO registerPhysicianDTO) throws Exception{
        registrationService.registerPhysician(registerPhysicianDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Physician registration completed"));
    }
    @GetMapping("/physician/search")
    public ResponseEntity<ResponseDTO<String>> listPhysicians(@Valid @RequestBody PhysicianSearchDTO physicianSearchDTO) throws Exception{
        userListingService.listPhysiciansForAdmin(physicianSearchDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Physicians loaded successfully"));
    }
    @PostMapping("/physician/disable/{physicianId}")
    public ResponseEntity<ResponseDTO<String>> disablePhysician(@PathVariable Long physicianId) throws Exception{
        userManagementService.disablePhysician(physicianId);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Physician disabled successfully"));
    }
    @PostMapping("/physician/enable/{physicianId}")
    public ResponseEntity<ResponseDTO<String>> enablePhysician(@PathVariable Long physicianId) throws Exception{
        userManagementService.enablePhysician(physicianId);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Physician enabled successfully"));
    }


    //Patient Management
    @GetMapping("/patient/search")
    public ResponseEntity<ResponseDTO<String>> listPatients(@Valid @RequestBody PatientSearchDTO patientSearchDTO) throws Exception{
        userListingService.listPatients(patientSearchDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Patients loaded successfully"));
    }
    @PostMapping("/patient/disable/{patientId}")
    public ResponseEntity<ResponseDTO<String>> disablePatient(@PathVariable Long patientId) throws Exception{
        userManagementService.disablePatient(patientId);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Patient disabled successfully"));
    }
    @PostMapping("/patient/enable/{patientId}")
    public ResponseEntity<ResponseDTO<String>> enablePatient(@PathVariable Long patientId) throws Exception{
        userManagementService.enablePatient(patientId);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Patient enabled successfully"));
    }


    //Claim Management
    @GetMapping("/claims/list-mine-by-status")
    public ResponseEntity<ResponseDTO<String>> listAllClaimsByStatusForAdmin(@Valid @RequestBody AdminClaimsSearchDTO adminClaimsSearchDTO) throws Exception{
        claimsService.listAllClaimsByStatusForAdmin(adminClaimsSearchDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Claims loaded successfully"));
    }
    @GetMapping("/claims/list-all-by-status/{status}")
    public ResponseEntity<ResponseDTO<String>> listAllClaimsByStatus(@PathVariable ClaimState status) throws Exception{
        claimsService.listAllClaimsByStatus(status);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Claims loaded successfully"));
    }
    @GetMapping("/claims/list-mine/{adminId}")
    public ResponseEntity<ResponseDTO<String>> listAllClaimsForAdmin(@PathVariable Long adminId) throws Exception{
        claimsService.listAllClaimsForAdmin(adminId);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Claims loaded successfully"));
    }
    @GetMapping("/claims/list-all")
    public ResponseEntity<ResponseDTO<String>> listAllClaims() throws Exception{
        claimsService.listAllClaims();
        return ResponseEntity.ok(new ResponseDTO<>(false, "Claims loaded successfully"));
    }
    @GetMapping("/claims/list-all")
    public ResponseEntity<ResponseDTO<String>> assignClaimToAdmin(@Valid @RequestBody ClaimAssignmentDTO claimAssignmentDTO) throws Exception{
        claimsService.assignClaimToAdmin(claimAssignmentDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Claims loaded successfully"));
    }
    @GetMapping("/claims/search-by-id/{claimId}")
    public ResponseEntity<ResponseDTO<String>> searchClaimForAdmin(@PathVariable Long claimId) throws Exception{
        claimsService.searchClaimForAdmin(claimId);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Claims loaded successfully"));
    }
    @GetMapping("/claims/see-details/{claimId}")
    public ResponseEntity<ResponseDTO<String>> seeClaimDetailsForAdmin(@PathVariable Long claimId) throws Exception{
        claimsService.seeClaimDetailsForAdmin(claimId);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Claims loaded successfully"));
    }
    @PutMapping("/claims/new-message")
    public ResponseEntity<ResponseDTO<String>> newMessageToClaim(@Valid @RequestBody ClaimAnswerDTO claimAnswerDTO) throws Exception{
        claimsService.addMessageToClaim(claimAnswerDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Message added to Claim successfully"));
    }
    @PostMapping("/claims/set-claim-state")
    public ResponseEntity<ResponseDTO<String>> setClaimState(@NotNull ClaimStateDTO claimStateDTO) throws Exception {
        claimsService.setClaimState(claimStateDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Claim state updated successfully"));
    }


    //Membership Management
    @PutMapping("/memberships/charge-all")
    public ResponseEntity<ResponseDTO<String>> chargeCurrentMonthToMemberships() throws Exception{
        membershipService.chargeCurrentMonthToMemberships();
        return ResponseEntity.ok(new ResponseDTO<>(false, "Memberships charged successfully"));
    }
    @PostMapping("/memberships/set-arrears")
    public ResponseEntity<ResponseDTO<String>> setArrearMemberships() throws Exception{
        membershipService.setArrearMemberships();
        return ResponseEntity.ok(new ResponseDTO<>(false, "Arrearing memberships identified successfully"));
    }
    @PostMapping("/memberships/set-active")
    public ResponseEntity<ResponseDTO<String>> setActiveMemberships() throws Exception{
        membershipService.setActiveMemberships();
        return ResponseEntity.ok(new ResponseDTO<>(false, "Active memberships identified successfully"));
    }
    @PostMapping("/memberships/set-charge-state")
    public ResponseEntity<ResponseDTO<String>> setChargeState(@Valid @RequestBody ChargeStateUpdateDTO chargeStateUpdateDTO) throws Exception{
        membershipService.setChargeState(chargeStateUpdateDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Charged state updated successfully"));
    }
    @PostMapping("/memberships/set-membership-state")
    public ResponseEntity<ResponseDTO<String>> setMembershipState(@NotNull MembershipStateUpdateDTO membershipStateUpdateDTO) throws Exception{
        membershipService.setMembershipState(membershipStateUpdateDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Membership state updated successfully"));
    }


    //Policy Management
    @PutMapping("/policies/new-policy")
    public ResponseEntity<ResponseDTO<String>> newPolicy(@Valid @RequestBody PolicyDetailsDTO policyDetailsDTO) throws Exception{
        policyService.newPolicy(policyDetailsDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Policy added successfully"));
    }
    @PostMapping("/policies/set-policy-state")
    public ResponseEntity<ResponseDTO<String>> setPolicyState(@Valid @RequestBody PolicyStateDTO policyStateDTO) throws Exception{
        policyService.setPolicyState(policyStateDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Policy state updated successfully"));
    }
    @GetMapping("/policies/list-all")
    public ResponseEntity<ResponseDTO<String>> listAllPolicies() throws Exception{
        policyService.listAllPolicies();
        return ResponseEntity.ok(new ResponseDTO<>(false, "Policies loaded successfully"));
    }
    @GetMapping("/policies/search-by-id/{policyId}")
    public ResponseEntity<ResponseDTO<String>> searchPolicyById(@PathVariable Long policyId) throws Exception{
        policyService.searchPolicyById(policyId);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Policy loaded successfully"));
    }
}
