package deep_pills.dto.controllers;

import jakarta.validation.constraints.NotNull;

public record ResponseDTO<T>(
        @NotNull boolean errors,
        @NotNull T message) {
}
