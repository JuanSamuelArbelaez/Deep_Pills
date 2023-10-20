package deep_pills;

import deep_pills.dto.memberships.*;
import deep_pills.model.enums.states.ChargeState;
import deep_pills.model.enums.states.MembershipState;
import deep_pills.services.interfaces.MembershipService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MembershipTest {
    @Autowired private MembershipService membershipService;

    @Test
    public void addPatientToMembership() {
        try{
            System.out.println(membershipService.addPatientToMembership(
                    new MembershipPatientModificationDTO(
                            2L,
                            "1000.000.000",
                            "1000.413.970"
                    )));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void removePatientFromMembership() {
        try{
            System.out.println(membershipService.removePatientFromMembership(
                    new MembershipPatientModificationDTO(
                            2L,
                            "1000.000.000",
                            "1000.413.970"
                    )));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void acquireMembership() {
        try{
            System.out.println(membershipService.acquireMembership(
                    new MembershipAcquirementDTO(
                            "1000.000.000",
                            3L
                    )));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void resignMembership() {
        try{
            System.out.println(membershipService.resignMembership(
                    "1000.000.000"
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void chargeCurrentMonthToMemberships() {
        try{
            List<Long> list = membershipService.chargeCurrentMonthToMemberships();
            for(Long l : list) {
                System.out.println(l);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void makePaymentToMembershipCharge() {
        try{
            System.out.println(membershipService.makePaymentToMembershipCharge(
                    new MembershipPaymentDTO(
                          "1000.000.000",
                            1L,
                            30000
                    )));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void setArrearMemberships() {
        try{
            List<Long> list = membershipService.setArrearMemberships();
            for(Long l : list) {
                System.out.println(l);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void setActiveMemberships() {
        try{
            List<Long> list = membershipService.setActiveMemberships();
            for(Long l : list) {
                System.out.println(l);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void setChargeState() {
        try{
            System.out.println(membershipService.setChargeState(
                    new ChargeStateUpdateDTO(
                            1L,
                            1L,
                            ChargeState.WAIVED
                    )));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void setMembershipState() {
        try{
            System.out.println(membershipService.setMembershipState(
                    new MembershipStateUpdateDTO(
                            1L,
                            2L,
                            MembershipState.ACTIVE
                    )));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getChargesFromMembership() {
        try{
            for(ChargeDTO charge : membershipService.getChargesFromMembership(new ChargeListDTO("1000.000.000", 2L))){
                System.out.println(charge.membershipChargeId()+" | "+
                        charge.chargeAmount()+" | "+
                        charge.dateTime()+" | "+
                        charge.chargeState());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getPaymentsFromCharge() {
        try{
            for(PaymentDTO payment : membershipService.getPaymentsFromCharge(new PaymentListDTO("1000.000.000", 1L))){
                System.out.println(payment.membershipPaymentId()+" | "+
                        payment.amount()+" | "+
                        payment.dateTime()+" | "+
                        payment.concept()+" | "+
                        payment.paymentState());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getPatientsMembership() {
        try{
            System.out.println(membershipService.getPatientsMembership("1000.000.000"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
