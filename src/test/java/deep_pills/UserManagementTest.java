package deep_pills;

import deep_pills.services.interfaces.UserManagementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserManagementTest {
    @Autowired UserManagementService userManagementService;

    @Test public void enablePatientTest(){
        try{
            userManagementService.enablePatient(502L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test public void disablePatientTest(){
        try{
            userManagementService.disablePatient(502L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test public void enablePhysicianTest(){
        try{
            userManagementService.enablePhysician(52L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test public void disablePhysicianTest(){
        try{
            userManagementService.disablePhysician(52L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
