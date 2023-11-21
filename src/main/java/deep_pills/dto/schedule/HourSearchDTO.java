package deep_pills.dto.schedule;

import jakarta.validation.constraints.NotNull;

public record HourSearchDTO(
        @NotNull Long physicianId,
        @NotNull Long scheduleId) {
}
