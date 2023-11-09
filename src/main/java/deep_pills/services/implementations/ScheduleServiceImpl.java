package deep_pills.services.implementations;

import deep_pills.dto.schedule.GenericCalendarDTO;
import deep_pills.dto.schedule.SpecificCalendarDTO;
import deep_pills.model.entities.schedule.Schedule;
import deep_pills.model.entities.schedule.Shift;
import deep_pills.model.enums.states.ScheduleState;
import deep_pills.model.enums.states.ShiftState;
import deep_pills.repositories.schedules.ScheduleRepository;
import deep_pills.repositories.schedules.ShiftRepository;
import deep_pills.services.interfaces.ScheduleService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ShiftRepository shiftRepository;

    @Override
    @Transactional
    public void createSchedulesForAllShiftsOnMonth(@NotNull GenericCalendarDTO calendarDTO) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendarDTO.month()); // Use the constant for the month you want
        calendar.set(Calendar.YEAR, calendarDTO.year()); // Use the year you want
        List<Shift> activeShifts = shiftRepository.findAllByShiftState(ShiftState.ACTIVE);
        for (Shift shift : activeShifts) {
            if(shift.getState().equals(ShiftState.INACTIVE)) throw new Exception("Could not set the schedule for shift "+ shift.getShiftId()+" for month "+ (calendar.get(Calendar.MONTH)+1)+" because it the shift is INACTIVE");

            Calendar currentDay = Calendar.getInstance();
            currentDay.setTime(calendar.getTime());
            currentDay.set(Calendar.DAY_OF_MONTH, 1); // Move to the first day of that month

            Calendar aux = Calendar.getInstance();
            aux.set(Calendar.MONTH,calendarDTO.month());
            aux.set(Calendar.DATE, 1);
            aux.add(Calendar.MONTH, 1);
            aux.add(Calendar.DATE, -1);

            Calendar lastDayOfMonth = Calendar.getInstance();
            lastDayOfMonth.setTime(calendar.getTime());
            lastDayOfMonth.add(Calendar.MONTH, 1);
            lastDayOfMonth.set(Calendar.DAY_OF_MONTH, 1);

            // Create Schedule entities for each day from today to the last day of the following month
            while (currentDay.before(lastDayOfMonth)) {
                if (Arrays.asList(shift.getDays().split(" ")).contains(new SimpleDateFormat("EEEE", Locale.ENGLISH).format(currentDay.getTime()))) {
                    Optional<Schedule> existingSchedule = scheduleRepository.findByShiftAndDate(shift, currentDay.getTime());
                    if (existingSchedule.isEmpty()) {
                        Schedule schedule = new Schedule();
                        schedule.setShift(shift);
                        schedule.setDate(currentDay.getTime());
                        schedule.setScheduleState(ScheduleState.ACTIVE);
                        System.out.println(scheduleRepository.save(schedule).getScheduleId());

                    }
                }
                // Move to the next day
                currentDay.add(Calendar.DATE, 1);
            }
        }
    }

    @Override
    @Transactional
    public void createSchedulesForSpecificShiftOnMonth(@NotNull SpecificCalendarDTO calendarDTO) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendarDTO.month()); // Use the constant for the month you want
        calendar.set(Calendar.YEAR, calendarDTO.year()); // Use the year you want
        Optional<Shift> optional = shiftRepository.findById(calendarDTO.shiftId());
        if(optional.isEmpty()) throw new Exception("No shift found for id: "+calendarDTO.shiftId());
        Shift shift = optional.get();

        if(shift.getState().equals(ShiftState.INACTIVE)) throw new Exception("Could not set the schedule for shift "+ shift.getShiftId()+" for month "+ (calendar.get(Calendar.MONTH)+1)+" because it the shift is INACTIVE");

        Calendar currentDay = Calendar.getInstance();
        currentDay.setTime(calendar.getTime());
        currentDay.set(Calendar.DAY_OF_MONTH, 1); // Move to the first day of that month

        Calendar aux = Calendar.getInstance();
        aux.set(Calendar.MONTH,calendarDTO.month());
        aux.set(Calendar.DATE, 1);
        aux.add(Calendar.MONTH, 1);
        aux.add(Calendar.DATE, -1);

        Calendar lastDayOfMonth = Calendar.getInstance();
        lastDayOfMonth.setTime(calendar.getTime());
        lastDayOfMonth.add(Calendar.MONTH, 1);
        lastDayOfMonth.set(Calendar.DAY_OF_MONTH, 1);

        // Create Schedule entities for each day from today to the last day of the following month
        while (currentDay.before(lastDayOfMonth)) {
            if (Arrays.asList(shift.getDays().split(" ")).contains(new SimpleDateFormat("EEEE", Locale.ENGLISH).format(currentDay.getTime()))) {
                Optional<Schedule> existingSchedule = scheduleRepository.findByShiftAndDate(shift, currentDay.getTime());
                if (existingSchedule.isEmpty()) {
                    Schedule schedule = new Schedule();
                    schedule.setShift(shift);
                    schedule.setDate(currentDay.getTime());
                    schedule.setScheduleState(ScheduleState.ACTIVE);
                    System.out.println(scheduleRepository.save(schedule).getScheduleId());

                }
            }
            // Move to the next day
            currentDay.add(Calendar.DATE, 1);
        }
    }
}
