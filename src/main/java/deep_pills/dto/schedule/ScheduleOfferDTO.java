package deep_pills.dto.schedule;

import jakarta.validation.constraints.NotNull;

import java.sql.Time;
import java.util.Date;

public record ScheduleOfferDTO(
        @NotNull Long scheduleId,
        @NotNull Date date,
        @NotNull Time startTime,
        @NotNull Time endingTime) {

}
