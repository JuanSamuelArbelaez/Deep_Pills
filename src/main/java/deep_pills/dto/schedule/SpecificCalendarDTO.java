package deep_pills.dto.schedule;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SpecificCalendarDTO(
        @NotNull Long shiftId,
        @NotNull @Min(0) @Max(11) int month,
        @NotNull @Min(2023) int year) {
}
