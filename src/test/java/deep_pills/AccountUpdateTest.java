package deep_pills;

import deep_pills.dto.accounts.PasswordRecoveryDTO;
import deep_pills.dto.accounts.patient.InfoUpdatePatientDTO;
import deep_pills.dto.accounts.physician.InfoUpdatePhysicianDTO;
import deep_pills.model.enums.lists.BloodType;
import deep_pills.model.enums.lists.City;
import deep_pills.model.enums.lists.EPS;
import deep_pills.services.interfaces.AccountUpdateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class AccountUpdateTest {

    @Autowired private AccountUpdateService accountUpdateService;

    @Test public void updatePhysicianTest() {
        try {
            accountUpdateService.updatePhysician(new InfoUpdatePhysicianDTO(
                    102L,
                    "Will",
                    null,
                    "301-100-8888",
                    null,
                    City.KANSAS_CITY,
                    "www.example.com"
            ));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test public void updatePatientTest() {
        try {
            String fechaNacimientoStr = "27/09/2004"; // Cambia esto a tu fecha de nacimiento

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

            Date fechaNacimiento = formato.parse(fechaNacimientoStr);
            System.out.println("Fecha de nacimiento en formato Date: " + fechaNacimiento);

            accountUpdateService.updatePatient(new InfoUpdatePatientDTO(
                    502L,
                    "Juan Esteban",
                    "Astaiza Fuenmayor",
                    fechaNacimiento,
                    "316-250-0184",
                    null,
                    City.NEW_ORLEANS,
                    "www.example2.com/img2.jpg",
                    BloodType.O_POSITIVE,
                    EPS.MEDICAID
            ));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test public void newPasswordRecoveryRequest(){
        try {
            System.out.println(accountUpdateService.newPasswordRecoveryRequest("juane.astaizaf@uqvirtual.edu.co"));
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test public void acceptPasswordRecoveryCode(){
        Long prrId = 102L; //Use id provided
        String email = "juane.astaizaf@uqvirtual.edu.co";
        String code = "2WDGEJ"; //ej: 8D001A
        String newPassword = "bimbimbambam2";
        try{
            System.out.println(
                    accountUpdateService.acceptPasswordRecoveryCode(
                            new PasswordRecoveryDTO(prrId,
                                    email,
                                    code,
                                    newPassword
            )));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
