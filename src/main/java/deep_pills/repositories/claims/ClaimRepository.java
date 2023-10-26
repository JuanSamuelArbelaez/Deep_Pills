package deep_pills.repositories.claims;

import deep_pills.model.entities.claims.Claim;
import deep_pills.model.enums.states.ClaimState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    @Query("SELECT cl FROM Claim cl " +
            "WHERE cl.claimStatus = :status "+
            "ORDER BY cl.claimInfo.appointment.date")
    List<Claim> findByStatus(@Param("status")ClaimState status);

    @Query("SELECT cl FROM Claim cl " +
            "WHERE cl.claimInfo.admin.id = :adminId "+
            "AND cl.claimStatus = :status "+
            "ORDER BY cl.claimInfo.appointment.date")
    List<Claim> findByAdminAndStatus(@Param("adminId") Long adminId, @Param("status")ClaimState status);

    @Query("SELECT cl FROM Claim cl " +
            "WHERE cl.claimInfo.admin.id = :adminId "+
            "ORDER BY cl.claimInfo.appointment.date")
    List<Claim> findByAdmin(@Param("adminId") Long adminId);

    @Query("SELECT cl FROM Claim cl " +
            "WHERE cl.claimInfo.appointment.patient.personalId = :patientPersonalId "+
            "AND cl.claimStatus = :status "+
            "ORDER BY cl.claimInfo.appointment.date")
    List<Claim> findByPatientAndStatus(@Param("patientPersonalId") String patientPersonalId, @Param("status")ClaimState status);

    @Query("SELECT cl FROM Claim cl " +
            "WHERE cl.claimInfo.appointment.patient.personalId = :patientPersonalId "+
            "ORDER BY cl.claimInfo.appointment.date")
    List<Claim> findByPatientPersonalId(@Param("patientPersonalId") String patientPersonalId);

    @Query("SELECT cl FROM Claim cl " +
            "WHERE cl.claimInfo.appointment.patient.personalId = :patientPersonalId "+
            "AND cl.claimId = :claimId "+
            "ORDER BY cl.claimInfo.appointment.date")
    Optional<Claim> findByIdAndPatientPersonalId(@Param("claimId") Long claimId, @Param("patientPersonalId") String patientPersonalId);

    @Query("SELECT COUNT(*) " +
            "FROM Claim c " +
            "JOIN Patient p ON c.claimInfo.patient.personalId= p.personalId " +
            "WHERE c.claimStatus = :state " +
            "  AND p.personalId = :personalId")
    int countClaimsByStateAndPatientPersonalId(@Param("personalId") String personalId, @Param("state") ClaimState state);
}
