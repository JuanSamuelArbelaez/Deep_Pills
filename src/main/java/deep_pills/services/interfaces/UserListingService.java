package deep_pills.services.interfaces;

import deep_pills.dto.accounts.patient.AccountDetailsPatientDTO;
import deep_pills.dto.accounts.patient.PatientListingItemDTO;
import deep_pills.dto.accounts.patient.PatientSearchDTO;
import deep_pills.dto.accounts.physician.AccountDetailsPhysicianDTO;
import deep_pills.dto.accounts.physician.PhysicianListingItemDTO;
import deep_pills.dto.accounts.physician.PhysicianSearchDTO;
import jakarta.validation.constraints.NotNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserListingService {
    List<PhysicianListingItemDTO> listPhysiciansForAdmin(PhysicianSearchDTO physicianSearchDTO) throws Exception;

    @Transactional
    List<PhysicianListingItemDTO> listPhysiciansForPatient(@NotNull PhysicianSearchDTO physicianSearchDTO) throws Exception;

    AccountDetailsPhysicianDTO getPhysicianDetails(Long physicianId) throws Exception;
    List<PatientListingItemDTO> listPatients(PatientSearchDTO patientSearchDTO) throws Exception;
    AccountDetailsPatientDTO getPatientDetails(Long patientId) throws Exception;
}
