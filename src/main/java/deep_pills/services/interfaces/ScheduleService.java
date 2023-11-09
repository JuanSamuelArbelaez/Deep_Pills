package deep_pills.services.interfaces;

import deep_pills.dto.schedule.GenericCalendarDTO;
import deep_pills.dto.schedule.SpecificCalendarDTO;
import jakarta.validation.constraints.NotNull;
import org.springframework.transaction.annotation.Transactional;

public interface ScheduleService {

    @Transactional
    void createSchedulesForAllShiftsOnMonth(@NotNull GenericCalendarDTO calendarDTO) throws Exception;

    @Transactional
    void createSchedulesForSpecificShiftOnMonth(@NotNull SpecificCalendarDTO calendarDTO) throws Exception;
}
