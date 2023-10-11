package deep_pills.services.interfaces;

import deep_pills.dto.accounts.patient.InfoUpdatePatientDTO;
import deep_pills.dto.accounts.physician.InfoUpdatePhysicianDTO;

public interface AccountUpdateService {
    String updatePhysician(InfoUpdatePhysicianDTO infoUpdatePhysicianDTO) throws Exception;
    String updatePatient(InfoUpdatePatientDTO infoUpdatePatientDTO) throws Exception;
}
