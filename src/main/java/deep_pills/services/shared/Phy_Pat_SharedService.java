package deep_pills.services.shared;

public interface Phy_Pat_SharedService {
    void listAppointments(String physician_ID, String patient_ID, String date, String type) throws Exception;
}
