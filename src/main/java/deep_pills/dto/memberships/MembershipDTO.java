package deep_pills.dto.memberships;

import deep_pills.model.enums.states.MembershipState;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

public record MembershipDTO(
        @NotNull Long membershipID,

        @NotNull Long ownerPatientID,

        @NotNull List<Long> beneficiariesID,

        @NotNull Date date,

        @NotNull Long policyID,

        @NotNull MembershipState state) {
}
