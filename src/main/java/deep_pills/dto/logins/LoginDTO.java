package deep_pills.dto.logins;

import jakarta.validation.constraints.NotNull;

public record LoginDTO(
        @NotNull
        String email,

        @NotNull
        String password){
}