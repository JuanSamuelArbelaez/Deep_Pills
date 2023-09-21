package deep_pills.services;

import deep_pills.dto.claims.*;

import java.util.List;

public interface Claims_Service {
    //Admin
    List<ClaimItemAdmin_DTO> listPendingClaims_Admin(ClaimListingAdmin_DTO claimListingAdmin_dto) throws Exception;
    List<ClaimItemAdmin_DTO> listAllClaims_Admin(Long admin_Id) throws Exception;
    List<ClaimItemAdmin_DTO> listAllClaims() throws Exception;
    String answerClaim(ClaimAnswer_DTO claimAnswer_dto) throws Exception;
    ClaimItemAdmin_DTO searchClaim_Admin(Long claim_Id) throws Exception;
    ClaimDetailedItemAdmin_DTO seeClaimDetails_Admin(Long claim_Id) throws Exception;

    //Patient
    List<ClaimItemPatient_DTO> listPendingClaims_Patient(ClaimListingPatient_DTO claimListingPatient_dto) throws Exception;
    List<ClaimItemPatient_DTO> listAllClaims_Patient(Long patient_id) throws Exception;
    ClaimItemPatient_DTO searchClaim_Patient(Long claim_Id, Long patient_Id) throws Exception;
    ClaimDetailedItemPatient_DTO seeClaimDetails_Patient(Long claim_Id, Long patient_Id) throws Exception;

}
