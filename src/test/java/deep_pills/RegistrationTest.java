package deep_pills;

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
@Transactional
public class RegistrationTest {
    @Autowired private RegistrationService registrationService;

    @Test public void registerPhysicianTest(){
            RegisterPhysicianDTO dto = new RegisterPhysicianDTO(
                    "Gloria",
                    "Green",
                    "gloriagreen@deep.pills.com",
                    "gl1872",
                    "723.319.180",
                    Long.parseLong("1"),
                    new ArrayList<Specialization>(Specialization.Cardiology.ordinal()),
                    new ShiftDTO(
                            "Monday Tuesday Wednesday Thursday",
                            new Time(212123),
                            new Time(212121)
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

    }
}
