package deep_pills.dto.schedule;

import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record HourOfferDTO (
        @NotNull Long scheduleId,
        @NotNull LocalTime startTime,
        @NotNull LocalTime endTime){
}
