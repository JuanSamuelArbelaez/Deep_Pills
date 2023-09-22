package deep_pills.services.concreteServices;

import deep_pills.dto.claims.*;

import java.util.List;

public interface ClaimsService {
    //Admin
    List<ClaimItemAdminDTO> listPendingClaimsForAdmin(ClaimListingAdminDTO claimListingAdminDto) throws Exception;
    List<ClaimItemAdminDTO> listAllClaimsForAdmin(Long adminId) throws Exception;
    List<ClaimItemAdminDTO> listAllClaims() throws Exception;
    String answerClaim(ClaimAnswerDTO claimAnswerDto) throws Exception;
    ClaimItemAdminDTO searchClaimForAdmin(Long claimId) throws Exception;
    ClaimDetailedItemAdminDTO seeClaimDetailsForAdmin(Long claimId) throws Exception;

    //Patient
    List<ClaimItemPatientDTO> listPendingClaimsForPatient(ClaimListingPatientDTO claimListingPatientDto) throws Exception;
    List<ClaimItemPatientDTO> listAllClaimsForPatient(Long patientId) throws Exception;
    ClaimItemPatientDTO searchClaimForPatient(Long claimId, Long patientId) throws Exception;
    ClaimDetailedItemPatientDTO seeClaimDetailsForPatient(Long claimId, Long patientId) throws Exception;
    Long newClaim(ClaimRegisterDTO claimRegisterDto) throws Exception;
}
