package deep_pills.services.implementations;

import deep_pills.dto.accounts.patient.*;
import deep_pills.dto.accounts.physician.*;
import deep_pills.model.entities.accounts.users.patients.Patient;
import deep_pills.model.entities.accounts.users.physicians.Physician;
import deep_pills.model.entities.memberships.Membership;
import deep_pills.model.enums.lists.City;
import deep_pills.model.enums.lists.Specialization;
import deep_pills.repositories.accounts.users.*;
import deep_pills.repositories.memberships.MembershipRepository;
import deep_pills.repositories.schedules.ShiftRepository;
import deep_pills.services.interfaces.UserListingService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserListingServiceImpl implements UserListingService {
    private final PhysicianRepository physicianRepository;
    private final PatientRepository patientRepository;
    private final ShiftRepository shiftRepository;
    private final MembershipRepository membershipRepository;

    @Override
    @Transactional
    public List<PhysicianListingItemAdminDTO> listPhysiciansForAdmin(@NotNull PhysicianSearchDTO physicianSearchDTO) throws Exception {
        List<Physician> physicianList = searchPhysician(physicianSearchDTO.searchParameter(), physicianSearchDTO.searchValue(), true);
        if(physicianList.isEmpty()) throw new Exception("No matches found");

        List<PhysicianListingItemAdminDTO> physicianItemList = new ArrayList<>();
        for(Physician physician: physicianList){
            PhysicianListingItemAdminDTO item = new PhysicianListingItemAdminDTO(
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
    public List<PhysicianListingItemPatientDTO> listPhysiciansForPatient(@NotNull PhysicianSearchDTO physicianSearchDTO) throws Exception {
        List<Physician> physicianList = searchPhysician(physicianSearchDTO.searchParameter(), physicianSearchDTO.searchValue(), true);
        if(physicianList.isEmpty()) throw new Exception("No matches found");

        List<PhysicianListingItemPatientDTO> physicianItemList = new ArrayList<>();
        for(Physician physician: physicianList){
            PhysicianListingItemPatientDTO item = new PhysicianListingItemPatientDTO(
                    physician.getId(),
                    physician.getName(),
                    physician.getLastName());
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
                if(admin) physicianList.add(physicianRepository.getReferenceById(Long.parseLong(searchValue)));}
            case 2 -> {
                if(admin){
                    Optional<Physician> optional = physicianRepository.findByPersonalId(searchValue);
                    if(optional.isEmpty()) throw new Exception("No physician found for ID: "+searchValue);
                    physicianList.add(optional.get());
                }
            }
            case 3 -> physicianList.addAll(physicianRepository.findByName(searchValue));
            case 4 -> physicianList.addAll(physicianRepository.findByLastName(searchValue));
            case 5 -> physicianList.addAll(physicianRepository.findByShift(shiftRepository.getReferenceById(Long.parseLong(searchValue))));
            case 6 -> {
                try{
                    physicianList.addAll(physicianRepository.findBySpecialization(getSpecialization(searchValue)));
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
            case 7 -> physicianList.addAll(physicianRepository.findByCity(getCity(searchValue)));
            default -> new ArrayList<>();
        }
        return physicianList;
    }

    private City getCity(String searchValue) throws Exception{
        for(City city : City.values()){
            if(city.name().equals(searchValue))return city;
        }
        throw new Exception("No city found for: "+searchValue);
    }

    @Override
    @Transactional
    public AccountDetailsPhysicianDTO getPhysicianDetails(Long physicianId) throws Exception {
        return null;
    }

    @Override
    @Transactional
    public List<PatientListingItemDTO> listPatients(PatientSearchDTO patientSearchDTO) throws Exception {
        List<Patient> patientList = searchPatients(patientSearchDTO.searchParameter(), patientSearchDTO.searchValue());
        if(patientList.isEmpty()) throw new Exception("No matches found for value:'"+patientSearchDTO.searchValue()+"'");

        List<PatientListingItemDTO> patientItemList = new ArrayList<>();
        for(Patient patient : patientList){
            patientItemList.add(new PatientListingItemDTO(
                    patient.getId(),
                    patient.getPersonalId(),
                    patient.getName(),
                    patient.getLastName(),
                    patient.getEmail()
            ));
        }

        return patientItemList;
    }

    private List<Patient> searchPatients(Integer searchParameter, String searchValue) throws Exception {
        List<Patient> patientList = new ArrayList<>();
        switch (searchParameter) {
            case 0 -> patientList.addAll(patientRepository.findAll());
            case 1 -> patientList.add(patientRepository.getReferenceById(Long.parseLong(searchValue)));
            case 2 -> {
                Optional<Patient> optional = patientRepository.findByPersonalId(searchValue);
                if(optional.isEmpty())throw new Exception("No patient found by personalID: "+searchValue);
                else patientList.add(optional.get());
            }
            case 3 -> patientList.addAll(patientRepository.findByName(searchValue));
            case 4 -> patientList.addAll(patientRepository.findByLastName(searchValue));
            case 5 -> patientList.add(patientRepository.findByEmail(searchValue));
            case 6 -> patientList.addAll(patientRepository.findByPhone(searchValue));
            case 7 -> patientList.addAll(patientRepository.findByDateOfBirth(getDate(searchValue)));
            case 8 -> {
                Optional<Membership> optional = membershipRepository.findById(Long.parseLong(searchValue));
                if(optional.isEmpty()) throw new Exception("No membership found by ID: " + searchValue);

                patientList.add(optional.get().getOwner());
                patientList.addAll(optional.get().getBeneficiaries());
                }
            default -> new ArrayList<>();
        }
        return patientList;
    }

    private Date getDate(String date){
         return new Date(); //Implement String to Date parsing
    }
    @Override
    @Transactional
    public AccountDetailsPatientDTO getPatientDetails(Long patientId) throws Exception {
        return null;
    }
}
