package deep_pills.services;

import deep_pills.dto.accounts.AccountDetailsPhysician_DTO;
import deep_pills.dto.registrations.RegisterPhysician_DTO;

public interface Admin_Service {
    Long createPhysician(RegisterPhysician_DTO registerPhysician_dto) throws Exception;
    String updatePhysician() throws Exception;
    String disablePhysician(Long physician_Id) throws Exception;
    String enablePhysician(Long physician_Id) throws Exception;
    void listPhysicians() throws Exception;
    AccountDetailsPhysician_DTO getPhysicianDetails(Long physician_Id) throws Exception;
}





