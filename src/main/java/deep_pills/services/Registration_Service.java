package deep_pills.services;

import deep_pills.dto.registrations.*;

public interface Registration_Service {
    int registerPhysician(RegisterPhysician_DTO registerPhysician_dto) throws Exception;
    int registerPatient(RegisterPatient_DTO registerPatient_dto) throws Exception;
}
