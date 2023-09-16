package deep_pills.services;

public interface Admin_Service {
    //Physician-related actions
    void createPhysician() throws Exception;
    void updatePhysician() throws Exception;
    void disablePhysician() throws Exception;
    void enablePhysician() throws Exception;
    void listPhysicians() throws Exception;
    void getPhysician() throws Exception;

    //Claim-related actions
    void listPendingClaims() throws Exception;
    void listAllClaims() throws Exception;
    void answerClaim() throws Exception;
    void getClaim() throws Exception;
    void seeClaimDetails() throws Exception;

    //Appointment-related actions
    void listAppointments() throws Exception;
    void listAppointmentsFromPhysician() throws Exception;
}
