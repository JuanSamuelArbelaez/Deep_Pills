package deep_pills.dto.controllers;

import jakarta.validation.constraints.NotNull;

public record ValidationDTO(
        @NotNull String filedName,
        @NotNull String message) {
}
