package deep_pills.dto.accounts;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record PasswordRecoveryDTO(
        @NotNull Long passwordRecoveryRequestId,
        @Email String email,
        @NotNull String code,
        @NotNull String password) {
}
