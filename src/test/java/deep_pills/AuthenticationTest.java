package deep_pills;

import deep_pills.dto.logins.LoginDTO;
import deep_pills.services.interfaces.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthenticationTest {
    @Autowired private AuthenticationService authenticationService;

    @Test public void loginPhysicianTest(){
        //Output expected: 52
        try{
            System.out.println(authenticationService.login(new LoginDTO(
                    "gloriagreen@deep.pills.com",
                    "gl1872"
            )));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test public void loginPatientTest(){
        //Output expected: 152
        try{
            System.out.println(authenticationService.login(new LoginDTO(
                    "pepito.perez@gmail.com",
                    "pepepiña"
            )));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test public void loginAdminTest(){
        //Output expected: 1
        try{
            System.out.println(authenticationService.login(new LoginDTO(
                    "admin@deep.pills.com",
                    "pswd"
            )));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
