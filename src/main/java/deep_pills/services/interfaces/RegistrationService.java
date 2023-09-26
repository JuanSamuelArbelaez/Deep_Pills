package deep_pills.services.interfaces;

import deep_pills.dto.registrations.*;

public interface RegistrationService {
    Long registerPhysician(RegisterPhysicianDTO registerPhysicianDto) throws Exception;
    Long registerPatient(RegisterPatientDTO registerPatientDto) throws Exception;
}
