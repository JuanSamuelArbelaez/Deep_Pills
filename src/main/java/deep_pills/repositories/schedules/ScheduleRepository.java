package deep_pills.repositories.schedules;

import deep_pills.model.entities.schedule.Schedule;
import deep_pills.model.entities.schedule.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("SELECT s.scheduleId FROM Schedule s WHERE s.date = :date AND s.shift = :shift")
    Long findShiftIdByDateAndShift(Date date, Shift shift);

    @Query("SELECT s FROM Schedule s WHERE s.shift = :shift AND s.date = :date")
    Optional<Schedule> findByShiftAndDate(@Param("shift") Shift shift, @Param("date") Date date);
}
