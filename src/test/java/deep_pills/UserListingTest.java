package deep_pills;

import deep_pills.dto.accounts.patient.AccountDetailsPatientDTO;
import deep_pills.dto.accounts.patient.PatientListingItemDTO;
import deep_pills.dto.accounts.patient.PatientSearchDTO;
import deep_pills.dto.accounts.physician.AccountDetailsPhysicianDTO;
import deep_pills.dto.accounts.physician.PhysicianListingItemAdminDTO;
import deep_pills.dto.accounts.physician.PhysicianListingItemPatientDTO;
import deep_pills.dto.accounts.physician.PhysicianSearchDTO;
import deep_pills.services.interfaces.UserListingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserListingTest {
    @Autowired UserListingService userListingService;

    @Test
    public void listAllPhysicians(){
        try {
            for (PhysicianListingItemAdminDTO p : userListingService.listPhysiciansForAdmin(new PhysicianSearchDTO(0, ""))) {
                System.out.println(p.physicianId()+" | "+
                        p.personalId()+" | "+
                        p.name()+" | "+
                        p.lastName()+" | "+
                        p.shiftId()
                );
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void listAllPatients(){
        try {
            for (PatientListingItemDTO p : userListingService.listPatients(new PatientSearchDTO(0, ""))) {
                System.out.println(p.patientID()+" | "+
                        p.personalID()+" | "+
                        p.email()+" | "+
                        p.name()+" | "+
                        p.LastName()
                );
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void listPhysiciansForPatient(){
        try {
            for (PhysicianListingItemPatientDTO p : userListingService.listPhysiciansForPatient(new PhysicianSearchDTO(0, ""))) {
                System.out.println(p.physicianId() + " | " +
                        p.name() + " | " +
                        p.lastName());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void getPhysicianDetails() throws Exception {
        try {
            AccountDetailsPhysicianDTO p = userListingService.getPhysicianDetails("108.479.102");
            System.out.println(p.id() + " | " +
                    p.name() + " | " +
                    p.lastName() + " | " +
                    p.email() + " | " +
                    p.personalId() + " | " +
                    p.city() + " | " +
                    p.phone() + " | " +
                    p.shift() + " | " +
                    p.state()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void getPatientDetails() throws Exception {

        try {
            AccountDetailsPatientDTO p = userListingService.getPatientDetails("1000.000.000");
            System.out.println(p.id() + " | " +
                    p.name() + " | " +
                    p.lastName() + " | " +
                    p.email() + " | " +
                    p.personalId() + " | " +
                    p.city() + " | " +
                    p.phone() + " | " +
                    p.bloodType()+ " | " +
                    p.state()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
