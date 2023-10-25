package deep_pills;

import deep_pills.dto.claims.admin.ClaimAnswerDTO;
import deep_pills.dto.claims.admin.ClaimDetailedItemAdminDTO;
import deep_pills.dto.claims.admin.ClaimItemAdminDTO;
import deep_pills.dto.claims.patient.*;
import deep_pills.model.enums.states.ClaimState;
import deep_pills.model.enums.types.ClaimType;
import deep_pills.model.enums.types.MessageType;
import deep_pills.services.interfaces.ClaimsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClaimsTest {
    @Autowired private ClaimsService claimsService;

    @Test public void newClaim(){
        try {
            System.out.println(claimsService.newClaim(new ClaimRegisterDTO(
                    "171.018.271",
                    152L,
                    ClaimType.COMPLAINT,
                    "Initial Message: The doctor's head nurse was a little bit rude."
            )));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test public void listAllClaimsByStatusForAdminTest(){
        try {
            for(ClaimItemAdminDTO claim : claimsService.listAllClaimsByStatusForAdmin(1L, ClaimState.ACTIVE)){
                System.out.println(claim.claimId()+" | "+
                                claim.date()+" | "+
                                claim.claimInfoId()+ " | "+
                                claim.status()+" | "
                );
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test public void listAllClaimsByStatusTest(){
        try {
            for(ClaimItemAdminDTO claim : claimsService.listAllClaimsByStatus(ClaimState.ACTIVE)){
                System.out.println(claim.claimId()+" | "+
                        claim.date()+" | "+
                        claim.claimInfoId()+ " | "+
                        claim.status()+" | "
                );
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test public void listAllClaimsForAdminTest(){
        try {
            for(ClaimItemAdminDTO claim : claimsService.listAllClaimsForAdmin(1L)){
                System.out.println(claim.claimId()+" | "+
                        claim.date()+" | "+
                        claim.claimInfoId()+ " | "+
                        claim.status()+" | "
                );
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test void listAllClaimsTest(){
        try {
            for(ClaimItemAdminDTO claim : claimsService.listAllClaims()){
                System.out.println(claim.claimId()+" | "+
                        claim.date()+" | "+
                        claim.claimInfoId()+ " | "+
                        claim.status()+" | "
                );
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test public void assignClaimToAdminTest(){
        try{
            System.out.println(claimsService.assignClaimToAdmin(1L, 1L));
        }catch(Exception e){
        e.printStackTrace();
        }
    }

    @Test public void addMessageToClaimTest(){
        try {
            System.out.println(claimsService.addMessageToClaim(new ClaimAnswerDTO(
                    1L,
                    "Necesitamos expandir la info",
                    MessageType.ADMIN_PATIENT
            )));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test public void searchClaimForAdminTest(){
        try {
            System.out.println(claimsService.searchClaimForAdmin(1L));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test public void seeClaimDetailsForAdminTest(){
        try {
            ClaimDetailedItemAdminDTO cl = claimsService.seeClaimDetailsForAdmin(1L);
            System.out.println(cl.claimId()+" | "+
                    cl.adminId()+" | "+
                    cl.patientPersonalId()+" | "+
                    cl.appointmentId()+" | "+
                    cl.date()+" | "+
                    cl.details()+" | "+
                    cl.claimType()+" | "+
                    cl.claimStatus()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test public void listAllClaimsByStatusForPatientTest(){
        try {
            for(ClaimItemPatientDTO cl : claimsService.listAllClaimsByStatusForPatient(new ClaimListingPatientDTO("1000.000.000", ClaimState.ACTIVE))){
                System.out.println(cl.claimId()+" | "+
                                cl.claimDate()+" | "+
                                cl.claimType()+" | "+
                                cl.claimStatus()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listAllClaimsForPatientTest(){
        try {
            for(ClaimItemPatientDTO cl : claimsService.listAllClaimsForPatient("1000.000.000")){
                System.out.println(cl.claimId()+" | "+
                        cl.claimDate()+" | "+
                        cl.claimType()+" | "+
                        cl.claimStatus()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void searchClaimForPatientTest(){
        try {
            System.out.println(claimsService.searchClaimForPatient(new ClaimSearchDTO(1L, "1000.000.000")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void seeClaimDetailsForPatientTest(){
        try {
            ClaimDetailedItemPatientDTO cl = claimsService.seeClaimDetailsForPatient(new ClaimSearchDTO(1L, "1000.000.000"));
            System.out.println(cl.claimId()+" | "+
                    cl.personalId()+" | "+
                    cl.appointmentId()+" | "+
                    cl.claimDate()+" | "+
                    cl.details()+" | "+
                    cl.claimType()+" | "+
                    cl.claimStatus()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
