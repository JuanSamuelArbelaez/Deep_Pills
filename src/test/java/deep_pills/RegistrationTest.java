package deep_pills;

import deep_pills.dto.registrations.RegisterPatientDTO;
import deep_pills.dto.registrations.RegisterPhysicianDTO;
import deep_pills.dto.schedule.ShiftDTO;
import deep_pills.model.enums.lists.Specialization;
import deep_pills.services.interfaces.RegistrationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class RegistrationTest {
    @Autowired private RegistrationService registrationService;

    @Test public void registerPhysicianTest(){
        ArrayList<Specialization> specs = new ArrayList<>();
        specs.add(Specialization.Cardiology);
        specs.add(Specialization.Palliative_Medicine);

        RegisterPhysicianDTO dto = new RegisterPhysicianDTO(
                "Harry",
                "White",
                "harrywhite@deep.pills.com",
                "apoidy",
                "108.479.102",
                Long.parseLong("1"),
                specs,
                new ShiftDTO(
                        "Monday Tuesday Wednesday Thursday",
                        Time.valueOf("8:00:00"),
                        Time.valueOf("17:00:00")
                )
        );
        try {
            Long id = registrationService.registerPhysician(dto);
            System.out.println("Physycian registered. ID: "+id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test public void registerPatientTest(){
        RegisterPatientDTO dto = new RegisterPatientDTO(
                "pepitoperez@gmail.com",
                "pepepiña",
                "1012.529.018"
        );
        try {
            Long id = registrationService.registerPatient(dto);
            System.out.println("Patient registered. ID: "+id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
