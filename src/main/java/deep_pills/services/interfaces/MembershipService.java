package deep_pills.services.interfaces;

import deep_pills.dto.memberships.*;
import deep_pills.model.enums.states.PolicyState;
import jakarta.validation.constraints.NotNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MembershipService {
    @Transactional
    String addPatientToMembership(@NotNull MembershipPatientModificationDTO membershipPatientModificationDTO) throws Exception;

    @Transactional
    String removePatientFromMembership(@NotNull MembershipPatientModificationDTO membershipPatientModificationDTO) throws Exception;

    @Transactional
    Long acquireMembership(MembershipAcquirementDTO membershipAcquirementDTO) throws Exception;

    @Transactional
    Long resignMembership(String patientPersonalId) throws Exception;

    @Transactional
    List<Long> chargeCurrentMonthToMemberships();

    @Transactional
    Long makePaymentToMembershipCharge(MembershipPaymentDTO membershipPaymentDTO) throws Exception;

    @Transactional
    List<Long> setArrearMemberships();

    @Transactional
    List<Long> setActiveMemberships();

    @Transactional
    Long setChargeState(@NotNull ChargeStateUpdateDTO chargeStateUpdateDTO) throws Exception;

    @Transactional
    Long setMembershipState(@NotNull MembershipStateUpdateDTO membershipStateUpdateDTO) throws Exception;

    @Transactional
    List<ChargeDTO> getChargesFromMembership(ChargeListDTO chargeListDTO) throws Exception;

    @Transactional
    List<PaymentDTO> getPaymentsFromCharge(PaymentListDTO paymentListDTO) throws Exception;

    @Transactional
    Long getPatientsMembership(String patientPersonId) throws Exception;
}
