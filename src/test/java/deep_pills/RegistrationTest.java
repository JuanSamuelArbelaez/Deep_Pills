package deep_pills;

import deep_pills.dto.registrations.RegisterPatientDTO;
import deep_pills.dto.registrations.RegisterPhysicianDTO;
import deep_pills.dto.schedule.ShiftDTO;
import deep_pills.model.enums.lists.Specialization;
import deep_pills.services.interfaces.RegistrationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;
import java.util.ArrayList;

@SpringBootTest
public class RegistrationTest {
    @Autowired private RegistrationService registrationService;

    @Test public void registerPhysicianTest(){
        ArrayList<Specialization> specs = new ArrayList<>();
        specs.add(Specialization.Emergency_Medicine);
        specs.add(Specialization.Obstetrics);
        specs.add(Specialization.Gynecology);

        RegisterPhysicianDTO dto = new RegisterPhysicianDTO(
                "Serenna",
                "Keisha",
                "serenna.keisha@deep.pills.com",
                "0192hsji",
                "30.971.342",
                Long.parseLong("1"),
                specs,
                new ShiftDTO(
                        "Monday Tuesday Wednesday Thursday Friday",
                        Time.valueOf("08:00:00"),
                        Time.valueOf("17:00:00")
                )
        );
        try {
            Long id = registrationService.registerPhysician(dto);
            System.out.println("Physician registered. ID: "+id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test public void registerPatientTest(){
        RegisterPatientDTO dto = new RegisterPatientDTO(
                "juanalcachofa@gmail.com",
                "lalaland",
                "171.018.271"
        );
        try {
            Long id = registrationService.registerPatient(dto);
            System.out.println("Patient registered. ID: "+id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
