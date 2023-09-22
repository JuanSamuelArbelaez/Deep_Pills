package deep_pills.services.concreteServices;

import deep_pills.dto.registrations.*;

public interface RegistrationService {
    Long registerPhysician(RegisterPhysicianDTO registerPhysicianDto) throws Exception;
    Long registerPatient(RegisterPatientDTO registerPatientDto) throws Exception;
}
