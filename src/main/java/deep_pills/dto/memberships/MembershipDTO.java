package deep_pills.dto.memberships;

import deep_pills.model.enums.states.MembershipState;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

public record MembershipDTO(
        @NotNull Long membershipID,

        @NotNull BeneficiaryDTO owner,
        @NotNull Date date,
        @NotNull PolicyDetailsDTO policy,
        @NotNull List<BeneficiaryDTO> beneficiaries,
        @NotNull MembershipState state) {
}
