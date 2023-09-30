package deep_pills.services.implementations;

import deep_pills.dto.accounts.patient.AccountDetailsPatientDTO;
import deep_pills.dto.accounts.patient.PatientListingItemDTO;
import deep_pills.dto.accounts.patient.PatientSearchDTO;
import deep_pills.dto.accounts.physician.AccountDetailsPhysicianDTO;
import deep_pills.dto.accounts.physician.PhysicianListingItemDTO;
import deep_pills.dto.accounts.physician.PhysicianSearchDTO;
import deep_pills.model.entities.accounts.users.physicians.Physician;
import deep_pills.repositories.accounts.users.PatientRepository;
import deep_pills.repositories.accounts.users.PhysicianRepository;
import deep_pills.repositories.schedules.ShiftRepository;
import deep_pills.services.interfaces.UserListingService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserListingServiceImpl implements UserListingService {
    private final PhysicianRepository physicianRepository;
    private final PatientRepository patientRepository;
    private final ShiftRepository shiftRepository;

    @Override
    @Transactional
    public List<PhysicianListingItemDTO> listPhysiciansForAdmin(@NotNull PhysicianSearchDTO physicianSearchDTO) throws Exception {
        List<Physician> physicianList = new ArrayList<>();
        switch (physicianSearchDTO.searchParameter()) {
            case 0 -> physicianList.addAll(physicianRepository.findAll());
            case 1 -> physicianList.add(physicianRepository.getReferenceById(Long.parseLong(physicianSearchDTO.searchValue())));
            case 2 -> physicianList.addAll(physicianRepository.findByName(physicianSearchDTO.searchValue()));
            case 3 -> physicianList.addAll(physicianRepository.findByLastName(physicianSearchDTO.searchValue()));
            case 4 -> physicianList.addAll(physicianRepository.findByShift(shiftRepository.getReferenceById(Long.parseLong(physicianSearchDTO.searchValue()))));
            default -> new ArrayList<>();
        }
        if(physicianList.isEmpty()) throw new Exception("No matches found");

        List<PhysicianListingItemDTO> physicianItemList = new ArrayList<>();
        for(Physician physician: physicianList){
            PhysicianListingItemDTO item = new PhysicianListingItemDTO(
                    physician.getId(),
                    physician.getPersonalId(),
                    physician.getName(),
                    physician.getLastName(),
                    physician.getShift().getShiftId());

            physicianItemList.add(item);
        }
        return physicianItemList;
    }

    @Override
    @Transactional
    public List<PhysicianListingItemDTO> listPhysiciansForPatient(@NotNull PhysicianSearchDTO physicianSearchDTO) throws Exception {
        List<Physician> physicianList = new ArrayList<>();
        switch (physicianSearchDTO.searchParameter()) {
            case 0 -> physicianList.addAll(physicianRepository.findAll());
            case 2 -> physicianList.addAll(physicianRepository.findByName(physicianSearchDTO.searchValue()));
            case 3 -> physicianList.addAll(physicianRepository.findByLastName(physicianSearchDTO.searchValue()));
            case 4 -> physicianList.addAll(physicianRepository.findByShift(shiftRepository.getReferenceById(Long.parseLong(physicianSearchDTO.searchValue()))));
            default -> new ArrayList<>();
        }
        if(physicianList.isEmpty()) throw new Exception("No matches found");

        List<PhysicianListingItemDTO> physicianItemList = new ArrayList<>();
        for(Physician physician: physicianList){
            PhysicianListingItemDTO item = new PhysicianListingItemDTO(
                    physician.getId(),
                    physician.getPersonalId(),
                    physician.getName(),
                    physician.getLastName(),
                    physician.getShift().getShiftId());

            physicianItemList.add(item);
        }
        return physicianItemList;
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
