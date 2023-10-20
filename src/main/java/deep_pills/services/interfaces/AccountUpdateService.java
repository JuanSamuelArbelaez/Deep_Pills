package deep_pills.services.interfaces;

import deep_pills.dto.accounts.PasswordRecoveryDTO;
import deep_pills.dto.accounts.patient.InfoUpdatePatientDTO;
import deep_pills.dto.accounts.physician.InfoUpdatePhysicianDTO;
import deep_pills.model.enums.states.PasswordRecoveryRequestState;
import org.springframework.transaction.annotation.Transactional;

public interface AccountUpdateService {
    String updatePhysician(InfoUpdatePhysicianDTO infoUpdatePhysicianDTO) throws Exception;
    String updatePatient(InfoUpdatePatientDTO infoUpdatePatientDTO) throws Exception;

    @Transactional
    Long newPasswordRecoveryRequest(String email) throws Exception;

    @Transactional
    PasswordRecoveryRequestState acceptPasswordRecoveryCode(PasswordRecoveryDTO passwordRecoveryDTO) throws Exception;
}
