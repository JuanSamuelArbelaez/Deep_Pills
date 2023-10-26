package deep_pills.repositories.claims;

import deep_pills.model.entities.claims.ClaimInfo;
import deep_pills.model.enums.states.ClaimState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimInfoRepository extends JpaRepository<ClaimInfo, Long> {
    @Query("SELECT count(ci) from ClaimInfo ci "+
            "WHERE ci.appointment.appointmentId = :appointmentId "+
            "AND ci.claim.claimStatus = :claimStatus")
    public int countClaimsByAppointmentAndClaimStatus(@Param("appointmentId") Long appointmentId, @Param("claimStatus") ClaimState claimStatus);
}
