package deep_pills.repositories.memberships;

import deep_pills.model.entities.memberships.MembershipPayment;
import deep_pills.model.enums.states.PaymentState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MembershipPaymentRepository extends JpaRepository<MembershipPayment, Long> {
    @Query("SELECT SUM(mp.amount) FROM MembershipPayment mp " +
            "WHERE mp.paymentState = :paymentState " +
            "AND mp.membershipCharge.membershipChargeId = :chargeId")
    Double getTotalPaymentAmountByStateAndChargeId(
            @Param("paymentState") PaymentState paymentState,
            @Param("chargeId") Long chargeId);

    @Query("SELECT COUNT(mp) FROM MembershipPayment mp")
    int countAll();
}
