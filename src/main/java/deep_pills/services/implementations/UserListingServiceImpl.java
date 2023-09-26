package deep_pills.services.implementations;

import deep_pills.dto.accounts.*;
import deep_pills.services.interfaces.UserListingService;

import java.util.List;

public class UserListingServiceImpl implements UserListingService {
    @Override
    public List<PhysicianListingItemDTO> listPhysicians(PhysicianSearchDTO physicianSearchDTO) throws Exception {
        return null;
    }

    @Override
    public AccountDetailsPhysicianDTO getPhysicianDetails(Long physicianId) throws Exception {
        return null;
    }

    @Override
    public List<PatientListingItemDTO> listPatients(PatientSearchDTO patientSearchDTO) throws Exception {
        return null;
    }

    @Override
    public AccountDetailsPatientDTO getPatientDetails(Long patientId) throws Exception {
        return null;
    }
}
