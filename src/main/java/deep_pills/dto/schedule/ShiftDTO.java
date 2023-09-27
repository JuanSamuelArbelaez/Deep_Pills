package deep_pills.dto.schedule;

import jakarta.validation.constraints.NotNull;

import java.sql.Time;
import java.util.List;

public record ShiftDTO(
        @NotNull
        List<String> days,

        @NotNull
        Time startTime,

        @NotNull
        Time endTime){
}
