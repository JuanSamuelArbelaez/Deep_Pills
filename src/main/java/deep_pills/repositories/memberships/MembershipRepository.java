package deep_pills.repositories.memberships;

import deep_pills.model.entities.memberships.Membership;
import deep_pills.model.enums.states.ChargeState;
import deep_pills.model.enums.states.MembershipState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {
    @Query("SELECT m FROM Membership m " +
            "WHERE m.state = :membershipState " +
            "AND NOT EXISTS " +
            "(SELECT mc FROM MembershipCharge mc " +
            "WHERE mc.membership = m " +
            "AND MONTH(mc.dateTime) = MONTH(CURRENT_DATE) " +
            "AND YEAR(mc.dateTime) = YEAR(CURRENT_DATE))")
    List<Membership> findMembershipsWithStateAndNoChargesInCurrentMonth(
            @Param("membershipState") MembershipState membershipState);

    @Query("SELECT m FROM Membership m " +
            "WHERE m.state != :inactiveState " +
            "AND NOT EXISTS " +
            "(SELECT mc FROM MembershipCharge mc " +
            "WHERE mc.membership = m " +
            "AND MONTH(mc.dateTime) = MONTH(CURRENT_DATE) " +
            "AND YEAR(mc.dateTime) = YEAR(CURRENT_DATE))")
    List<Membership> findMembershipWithNonInactiveStateAndNoChargesInCurrentMonth(
            @Param("inactiveState") MembershipState inactiveState);

    @Query("SELECT DISTINCT m FROM Membership m " +
            "JOIN FETCH m.membershipCharges mc " +
            "WHERE m.state = :membershipState " +
            "AND mc.chargeState = :chargeState " +
            "AND mc.dateTime < DATE_TRUNC('MONTH', CURRENT_DATE)")
    List<Membership> findMembershipsToBeInArrear(
            @Param("membershipState") MembershipState membershipState,
            @Param("chargeState") ChargeState chargeState);

    @Query("SELECT DISTINCT m FROM Membership m " +
            "WHERE m.state = :membershipState " +
            "AND NOT EXISTS " +
            "(SELECT mc FROM MembershipCharge mc " +
            "WHERE mc.membership = m " +
            "AND mc.chargeState = :chargeState " +
            "AND mc.dateTime < DATE_TRUNC('MONTH', CURRENT_DATE))")
    List<Membership> findMembershipsToNoLongerBeInArrear(
            @Param("membershipState") MembershipState membershipState,
            @Param("chargeState") ChargeState chargeState);

}
