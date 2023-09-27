package deep_pills.services.implementations;

import deep_pills.dto.accounts.patient.AccountDetailsPatientDTO;
import deep_pills.dto.accounts.patient.PatientListingItemDTO;
import deep_pills.dto.accounts.patient.PatientSearchDTO;
import deep_pills.dto.accounts.physician.AccountDetailsPhysicianDTO;
import deep_pills.dto.accounts.physician.PhysicianListingItemDTO;
import deep_pills.dto.accounts.physician.PhysicianSearchDTO;
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
