package deep_pills.services.interfaces;

import deep_pills.dto.accounts.patient.*;
import deep_pills.dto.accounts.physician.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserListingService {
    List<PhysicianListingItemAdminDTO> listPhysiciansForAdmin(PhysicianSearchDTO physicianSearchDTO) throws Exception;

    @Transactional
    List<PhysicianListingItemPatientDTO> listPhysiciansForPatient(@NotNull PhysicianSearchDTO physicianSearchDTO) throws Exception;

    AccountDetailsPhysicianDTO getPhysicianDetails(Long physicianId) throws Exception;
    List<PatientListingItemDTO> listPatients(PatientSearchDTO patientSearchDTO) throws Exception;
    AccountDetailsPatientDTO getPatientDetails(Long patientId) throws Exception;
}
