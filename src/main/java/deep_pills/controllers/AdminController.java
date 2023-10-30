package deep_pills.controllers;

import deep_pills.dto.accounts.patient.PatientListingItemDTO;
import deep_pills.dto.accounts.patient.PatientSearchDTO;
import deep_pills.dto.accounts.physician.PhysicianListingItemAdminDTO;
import deep_pills.dto.accounts.physician.PhysicianSearchDTO;
import deep_pills.dto.claims.admin.*;
import deep_pills.dto.controllers.ResponseDTO;
import deep_pills.dto.memberships.*;
import deep_pills.dto.registrations.RegisterPhysicianDTO;
import deep_pills.model.enums.states.ClaimState;
import deep_pills.services.interfaces.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/deep_pills/api/admin")
@SecurityRequirement(name = "bearerAuth")
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
    public ResponseEntity<ResponseDTO<List<PhysicianListingItemAdminDTO>>> listPhysicians(@Valid @RequestBody PhysicianSearchDTO physicianSearchDTO) throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, userListingService.listPhysiciansForAdmin(physicianSearchDTO)));
    }

    //Patient Management
    @GetMapping("/patient/search")
    public ResponseEntity<ResponseDTO<List<PatientListingItemDTO>>> listPatients(@Valid @RequestBody PatientSearchDTO patientSearchDTO) throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, userListingService.listPatients(patientSearchDTO)));
    }


    //Claim Management
    @GetMapping("/claims/list-mine-by-status")
    public ResponseEntity<ResponseDTO<List<ClaimItemAdminDTO>>> listAllClaimsByStatusForAdmin(@Valid @RequestBody AdminClaimsSearchDTO adminClaimsSearchDTO) throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, claimsService.listAllClaimsByStatusForAdmin(adminClaimsSearchDTO)));
    }
    @GetMapping("/claims/list-all-by-status/{status}")
    public ResponseEntity<ResponseDTO<List<ClaimItemAdminDTO>>> listAllClaimsByStatus(@PathVariable ClaimState status) throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, claimsService.listAllClaimsByStatus(status)));
    }
    @GetMapping("/claims/list-mine/{adminId}")
    public ResponseEntity<ResponseDTO<List<ClaimItemAdminDTO>>> listAllClaimsForAdmin(@PathVariable Long adminId) throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, claimsService.listAllClaimsForAdmin(adminId)));
    }
    @GetMapping("/claims/list-all")
    public ResponseEntity<ResponseDTO<List<ClaimItemAdminDTO>>> listAllClaims() throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, claimsService.listAllClaims()));
    }
    @GetMapping("/claims/assign-claim-to-admin")
    public ResponseEntity<ResponseDTO<String>> assignClaimToAdmin(@Valid @RequestBody ClaimAssignmentDTO claimAssignmentDTO) throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, claimsService.assignClaimToAdmin(claimAssignmentDTO)));
    }
    @GetMapping("/claims/search-by-id/{claimId}")
    public ResponseEntity<ResponseDTO<ClaimItemAdminDTO>> searchClaimForAdmin(@PathVariable Long claimId) throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, claimsService.searchClaimForAdmin(claimId)));
    }
    @GetMapping("/claims/see-details/{claimId}")
    public ResponseEntity<ResponseDTO<ClaimDetailedItemAdminDTO>> seeClaimDetailsForAdmin(@PathVariable Long claimId) throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, claimsService.seeClaimDetailsForAdmin(claimId)));
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
    public ResponseEntity<ResponseDTO<List<Long>>> chargeCurrentMonthToMemberships() throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, membershipService.chargeCurrentMonthToMemberships()));
    }
    @PostMapping("/memberships/set-arrears")
    public ResponseEntity<ResponseDTO<List<Long>>> setArrearMemberships() throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, membershipService.setArrearMemberships()));
    }
    @PostMapping("/memberships/set-active")
    public ResponseEntity<ResponseDTO<List<Long>>> setActiveMemberships() throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, membershipService.setActiveMemberships()));
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
        return ResponseEntity.ok(new ResponseDTO<>(false, "Policy added successfully: "+policyService.newPolicy(policyDetailsDTO)));
    }
    @PostMapping("/policies/set-policy-state")
    public ResponseEntity<ResponseDTO<String>> setPolicyState(@Valid @RequestBody PolicyStateDTO policyStateDTO) throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, "Policy state updated successfully: "+policyService.setPolicyState(policyStateDTO)));
    }
    @GetMapping("/policies/list-all")
    public ResponseEntity<ResponseDTO<List<PolicyDetailsDTO>>> listAllPolicies() throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, policyService.listAllPolicies()));
    }
    @GetMapping("/policies/search-by-id/{policyId}")
    public ResponseEntity<ResponseDTO<PolicyDetailsDTO>> searchPolicyById(@PathVariable Long policyId) throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, policyService.searchPolicyById(policyId)));
    }
}
