package deep_pills.services.implementations;

import deep_pills.dto.claims.admin.*;
import deep_pills.dto.claims.patient.ClaimDetailedItemPatientDTO;
import deep_pills.dto.claims.patient.ClaimItemPatientDTO;
import deep_pills.dto.claims.patient.ClaimListingPatientDTO;
import deep_pills.dto.claims.patient.ClaimRegisterDTO;
import deep_pills.services.interfaces.ClaimsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimsServiceImpl implements ClaimsService {

    @Override
    public List<ClaimItemAdminDTO> listPendingClaimsForAdmin(ClaimListingAdminDTO claimListingAdminDto) throws Exception {
        return null;
    }

    @Override
    public List<ClaimItemAdminDTO> listAllClaimsForAdmin(Long adminId) throws Exception {
        return null;
    }

    @Override
    public List<ClaimItemAdminDTO> listAllClaims() throws Exception {
        return null;
    }

    @Override
    public String answerClaim(ClaimAnswerDTO claimAnswerDto) throws Exception {
        return null;
    }

    @Override
    public ClaimItemAdminDTO searchClaimForAdmin(Long claimId) throws Exception {
        return null;
    }

    @Override
    public ClaimDetailedItemAdminDTO seeClaimDetailsForAdmin(Long claimId) throws Exception {
        return null;
    }

    @Override
    public List<ClaimItemPatientDTO> listPendingClaimsForPatient(ClaimListingPatientDTO claimListingPatientDto) throws Exception {
        return null;
    }

    @Override
    public List<ClaimItemPatientDTO> listAllClaimsForPatient(Long patientId) throws Exception {
        return null;
    }

    @Override
    public ClaimItemPatientDTO searchClaimForPatient(Long claimId, Long patientId) throws Exception {
        return null;
    }

    @Override
    public ClaimDetailedItemPatientDTO seeClaimDetailsForPatient(Long claimId, Long patientId) throws Exception {
        return null;
    }

    @Override
    public Long newClaim(ClaimRegisterDTO claimRegisterDto) throws Exception {
        return null;
    }
}
