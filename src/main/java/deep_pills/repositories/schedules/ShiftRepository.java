package deep_pills.repositories.schedules;

import deep_pills.model.entities.schedule.Shift;
import deep_pills.model.enums.states.ShiftState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {
    @Query("SELECT s.shiftId FROM Shift s WHERE s.startTime = :startTime AND s.endTime = :endTime")
    Long findShiftIdByStartTimeAndEndTime(Time startTime,Time endTime);

    @Query("SELECT s.shiftId FROM Shift s WHERE s.startTime = :startTime AND s.endTime = :endTime AND s.days = :days")
    Long findShiftIdByStartTimeAndEndTimeAndDays(Time startTime, Time endTime, String days);

    @Query("SELECT s FROM Shift s WHERE s.state = :state")
    List<Shift> findAllByShiftState(@Param("state") ShiftState state);
}
