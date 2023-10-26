package deep_pills.services.interfaces;

import deep_pills.dto.claims.admin.*;
import deep_pills.dto.claims.patient.*;
import deep_pills.model.enums.states.ClaimState;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClaimsService {
    //Admin
    List<ClaimItemAdminDTO> listAllClaimsByStatusForAdmin(AdminClaimsSearchDTO adminClaimsSearchDTO) throws Exception;
    List<ClaimItemAdminDTO> listAllClaimsByStatus(ClaimState status) throws Exception;
    List<ClaimItemAdminDTO> listAllClaimsForAdmin(Long adminId) throws Exception;
    List<ClaimItemAdminDTO> listAllClaims() throws Exception;
    String assignClaimToAdmin(ClaimAssignmentDTO claimAssignmentDTO) throws Exception;
    ClaimItemAdminDTO searchClaimForAdmin(Long claimId) throws Exception;
    ClaimDetailedItemAdminDTO seeClaimDetailsForAdmin(Long claimId) throws Exception;
    Long setClaimState(ClaimStateDTO claimStateDTO) throws Exception;

    //Patient
    List<ClaimItemPatientDTO> listAllClaimsByStatusForPatient(ClaimListingPatientDTO claimListingPatientDTO) throws Exception;
    List<ClaimItemPatientDTO> listAllClaimsForPatient(String patientPersonalId) throws Exception;
    ClaimItemPatientDTO searchClaimForPatient(ClaimSearchDTO claimSearchDTO) throws Exception;
    ClaimDetailedItemPatientDTO seeClaimDetailsForPatient(ClaimSearchDTO claimSearchDTO) throws Exception;
    Long newClaim(ClaimRegisterDTO claimRegisterDto) throws Exception;

    //Generic
    Long addMessageToClaim(ClaimAnswerDTO claimAnswerDto) throws Exception;
}
