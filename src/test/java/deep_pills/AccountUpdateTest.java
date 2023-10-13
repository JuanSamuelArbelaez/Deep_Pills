package deep_pills;

import deep_pills.dto.accounts.patient.InfoUpdatePatientDTO;
import deep_pills.dto.accounts.physician.InfoUpdatePhysicianDTO;
import deep_pills.model.enums.lists.BloodType;
import deep_pills.model.enums.lists.City;
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
            String fechaNacimientoStr = "13/10/1990"; // Cambia esto a tu fecha de nacimiento

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

            Date fechaNacimiento = formato.parse(fechaNacimientoStr);
            System.out.println("Fecha de nacimiento en formato Date: " + fechaNacimiento);

            accountUpdateService.updatePatient(new InfoUpdatePatientDTO(
                    152L,
                    "Pepito",
                    "Perez",
                    fechaNacimiento,
                    "303-202-1010",
                    "pepito.perez@gmail.com",
                    City.NEW_YORK_CITY,
                    null,
                    BloodType.O_POSITIVE
            ));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
