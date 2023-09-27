package deep_pills.repositories.appointments;

import deep_pills.model.entities.schedule.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Time;

public interface ShiftRepository extends JpaRepository<Shift, Long> {
    @Query("SELECT s.id FROM Shift s WHERE s.startTime = :startTime AND s.endTime = :endTime")
    Long findShiftIdByStartTimeAndEndTime(Time startTime,Time endTime);
}
