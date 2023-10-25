package deep_pills.dto.controllers;

import jakarta.validation.constraints.NotNull;

public record ResponseDTO(
        @NotNull boolean errors,
        @NotNull String message) {
}
