package deep_pills.dto.memberships;

import jakarta.validation.constraints.NotNull;

public record PaymentListDTO (
        @NotNull String patientPersonalId,
        @NotNull Long chargeId){
}
