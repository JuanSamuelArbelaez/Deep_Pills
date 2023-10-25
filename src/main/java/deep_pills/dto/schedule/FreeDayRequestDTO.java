package deep_pills.dto.schedule;

import jakarta.validation.constraints.NotNull;

public record FreeDayRequestDTO(
        @NotNull String physicianPersonalId,
        @NotNull Long scheduleId) {
}
