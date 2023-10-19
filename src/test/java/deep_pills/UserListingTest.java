package deep_pills;

import deep_pills.dto.accounts.patient.PatientSearchDTO;
import deep_pills.dto.accounts.physician.PhysicianSearchDTO;
import deep_pills.services.interfaces.UserListingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserListingTest {
    @Autowired UserListingService userListingService;

    @Test
    public void listAllPhysicians() throws Exception {
        userListingService.listPhysiciansForAdmin(new PhysicianSearchDTO(
                0,
                ""
        ));
    }

    @Test
    public void listAllPatients() throws Exception {
        userListingService.listPatients(new PatientSearchDTO(
                0,
                ""
        ));
    }

}
