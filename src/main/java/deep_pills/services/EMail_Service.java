package deep_pills.services;

public interface EMail_Service {
    String publicID = "100.A1", token  = "000";
    void sendEmail(String address, String message) throws Exception;
}
