package deep_pills;

import deep_pills.dto.memberships.PolicyDetailsDTO;
import deep_pills.model.enums.states.PolicyState;
import deep_pills.services.interfaces.PolicyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PolicyTest {
    @Autowired PolicyService policyService;

    @Test
    public void newPolicyTest(){
        try {
            System.out.println("New policy: "+policyService.newPolicy(new PolicyDetailsDTO(
                    0L,
                    "Apolo",
                    "Appointment oriented policy",
                    22000,
                    8,
                    3,
                    PolicyState.ACTIVE
            )));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test public void setPolicyStateTest(){
        try{
            System.out.println(policyService.setPolicyState(1L, PolicyState.ACTIVE));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test public void listAllPoliciesTest(){
        try{
            for(PolicyDetailsDTO policy : policyService.listAllPolicies()){
                System.out.println(policy.policyId()+" | "+
                                policy.name()+" | "+
                                policy.description()+" | "+
                                policy.cost()+" | "+
                                policy.maxAppointments()+" | "+
                                policy.maxPatients()+" | "+
                                policy.policyState());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test public void searchPolicyById(){
        try {
            PolicyDetailsDTO policy =  policyService.searchPolicyById(1L);
            System.out.println(policy.policyId()+" | "+
                    policy.name()+" | "+
                    policy.description()+" | "+
                    policy.cost()+" | "+
                    policy.maxAppointments()+" | "+
                    policy.maxPatients()+" | "+
                    policy.policyState());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
