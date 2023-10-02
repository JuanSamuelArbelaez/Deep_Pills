package deep_pills.services.implementations;

import deep_pills.dto.accounts.patient.*;
import deep_pills.dto.accounts.physician.*;
import deep_pills.model.entities.accounts.users.physicians.Physician;
import deep_pills.model.enums.lists.Specialization;
import deep_pills.repositories.accounts.users.*;
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
        List<Physician> physicianList = searchPhysician(physicianSearchDTO.searchParameter(), physicianSearchDTO.searchValue(), true);
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
        List<Physician> physicianList = searchPhysician(physicianSearchDTO.searchParameter(), physicianSearchDTO.searchValue(), true);
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

    private Specialization getSpecialization(String searchValue) throws Exception {
        for(Specialization spec: Specialization.values()){
            if (spec.name().equals(searchValue)) return spec;
        }
        throw new Exception("No specialization found for "+searchValue);
    }

    private List<Physician> searchPhysician(Integer searchParameter, String searchValue, Boolean admin) throws Exception{
        List<Physician> physicianList = new ArrayList<>();
        switch (searchParameter) {
            case 0 -> physicianList.addAll(physicianRepository.findAll());
            case 1 -> {
                if(admin) physicianList.add(physicianRepository.getReferenceById(Long.parseLong(searchValue)));
            }
            case 2 -> physicianList.addAll(physicianRepository.findByName(searchValue));
            case 3 -> physicianList.addAll(physicianRepository.findByLastName(searchValue));
            case 4 -> physicianList.addAll(physicianRepository.findByShift(shiftRepository.getReferenceById(Long.parseLong(searchValue))));
            case 5 -> {
                try{
                    physicianList.addAll(physicianRepository.findBySpecialization(getSpecialization(searchValue)));
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
            default -> new ArrayList<>();
        }
        return physicianList;
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
