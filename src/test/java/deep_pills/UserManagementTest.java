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
            userManagementService.enableUser(502L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test public void disablePatientTest(){
        try{
            userManagementService.disableUser(502L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test public void enablePhysicianTest(){
        try{
            userManagementService.enableUser(52L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test public void disablePhysicianTest(){
        try{
            userManagementService.disableUser(52L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
