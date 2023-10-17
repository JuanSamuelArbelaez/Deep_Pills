package deep_pills.repositories.schedules;

import deep_pills.model.entities.schedule.FreeDay;
import deep_pills.model.enums.states.FreeDayStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FreeDayRepository extends JpaRepository<FreeDay, Long> {
    @Query("SELECT count(fd) FROM FreeDay fd "+
            "WHERE fd.physician.personalId = :physicianPersonalId "+
            "AND fd.freeDayStatus = :freeDayStatus")
    int countByPhysicianPersonalIdAndStatus(@Param("physicianPersonalId") String physicianPersonalId, @Param("freeDayStatus") FreeDayStatus freeDayStatus);

    @Query("SELECT fd FROM FreeDay fd "+
            "WHERE fd.physician.personalId = :physicianPersonalId "+
            "AND fd.schedule.scheduleId = :scheduleId "+
            "AND fd.freeDayStatus = :freeDayStatus")
    Optional<FreeDay> findByPhysicianPersonalIdAndScheduleAndStatus(@Param("physicianPersonalId") String physicianPersonalId, @Param("scheduleId") Long scheduleId, @Param("freeDayStatus") FreeDayStatus freeDayStatus);
}
