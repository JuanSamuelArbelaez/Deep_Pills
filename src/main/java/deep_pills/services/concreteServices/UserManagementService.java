package deep_pills.services.concreteServices;

public interface UserManagementService {
    String disablePhysician(Long physicianId) throws Exception;
    String enablePhysician(Long physicianId) throws Exception;
    String disablePatient(Long patientId) throws Exception;
    String enablePatient(Long patientId) throws Exception;

}





