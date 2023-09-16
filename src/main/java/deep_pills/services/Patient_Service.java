package deep_pills.services;

public interface Patient_Service {
    void updateProfile() throws Exception;
    void register() throws Exception;
    void disableAccount() throws Exception;
    void sendPasswordRecoveryLink() throws Exception;
    void changePassword() throws Exception;
    void scheduleAppointment() throws Exception;
}
