package deep_pills.services.interfaces;

import deep_pills.dto.accounts.*;

import java.util.List;

public interface UserListingService {
    List<PhysicianListingItemDTO> listPhysicians(PhysicianSearchDTO physicianSearchDTO) throws Exception;
    AccountDetailsPhysicianDTO getPhysicianDetails(Long physicianId) throws Exception;
    List<PatientListingItemDTO> listPatients(PatientSearchDTO patientSearchDTO) throws Exception;
    AccountDetailsPatientDTO getPatientDetails(Long patientId) throws Exception;
}
