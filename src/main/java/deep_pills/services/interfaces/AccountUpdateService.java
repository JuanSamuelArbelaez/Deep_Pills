package deep_pills.services.interfaces;

import deep_pills.dto.accounts.PasswordRecoveryDTO;
import deep_pills.dto.accounts.patient.InfoLoadPatientDTO;
import deep_pills.dto.accounts.patient.InfoUpdatePatientDTO;
import deep_pills.dto.accounts.physician.InfoUpdatePhysicianDTO;
import deep_pills.model.enums.states.PasswordRecoveryRequestState;
import jakarta.validation.constraints.NotNull;
import org.springframework.transaction.annotation.Transactional;

public interface AccountUpdateService {
    String updatePhysician(InfoUpdatePhysicianDTO infoUpdatePhysicianDTO) throws Exception;
    String updatePatient(InfoUpdatePatientDTO infoUpdatePatientDTO) throws Exception;

    @Transactional
    InfoLoadPatientDTO loadPatientInfo(@NotNull Long patientId) throws Exception;

    @Transactional
    String newPasswordRecoveryRequest(String email) throws Exception;

    @Transactional
    PasswordRecoveryRequestState acceptPasswordRecoveryCode(PasswordRecoveryDTO passwordRecoveryDTO) throws Exception;
}
