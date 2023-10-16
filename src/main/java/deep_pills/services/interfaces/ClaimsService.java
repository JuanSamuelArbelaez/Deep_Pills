package deep_pills.services.interfaces;

import deep_pills.dto.claims.admin.*;
import deep_pills.dto.claims.patient.ClaimDetailedItemPatientDTO;
import deep_pills.dto.claims.patient.ClaimItemPatientDTO;
import deep_pills.dto.claims.patient.ClaimRegisterDTO;
import deep_pills.model.enums.states.ClaimState;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClaimsService {
    //Admin
    List<ClaimItemAdminDTO> listAllClaimsByStatusForAdmin(Long adminId, ClaimState status) throws Exception;

    List<ClaimItemAdminDTO> listAllClaimsByStatus(ClaimState status) throws Exception;

    List<ClaimItemAdminDTO> listAllClaimsForAdmin(Long adminId) throws Exception;
    List<ClaimItemAdminDTO> listAllClaims() throws Exception;
    String assignClaimToAdmin(Long claimId, Long adminId) throws Exception;
    ClaimItemAdminDTO searchClaimForAdmin(Long claimId) throws Exception;
    ClaimDetailedItemAdminDTO seeClaimDetailsForAdmin(Long claimId) throws Exception;

    //Patient
    List<ClaimItemPatientDTO> listAllClaimsByStatusForPatient(String patientPersonalId, ClaimState status) throws Exception;
    List<ClaimItemPatientDTO> listAllClaimsForPatient(String patientPersonalId) throws Exception;
    ClaimItemPatientDTO searchClaimForPatient(Long claimId, String patientPersonalId) throws Exception;
    ClaimDetailedItemPatientDTO seeClaimDetailsForPatient(Long claimId, String patientPersonalId) throws Exception;
    Long newClaim(ClaimRegisterDTO claimRegisterDto) throws Exception;

    //Generic
    Long addMessageToClaim(ClaimAnswerDTO claimAnswerDto) throws Exception;
}
