package deep_pills.services.roleServices;

public interface PatientService {
    void updateProfile() throws Exception;
    void register() throws Exception;
    void disableAccount() throws Exception;
    void sendPasswordRecoveryLink() throws Exception;
    void changePassword() throws Exception;
    void scheduleAppointment() throws Exception;
}
