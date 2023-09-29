package deep_pills.repositories.schedules;

import deep_pills.model.entities.schedule.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {
    @Query("SELECT s.shiftId FROM Shift s WHERE s.startTime = :startTime AND s.endTime = :endTime")
    Long findShiftIdByStartTimeAndEndTime(Time startTime,Time endTime);
}
