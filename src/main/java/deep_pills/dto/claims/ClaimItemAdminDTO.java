package deep_pills.dto.claims;

import deep_pills.model.enums.states.ClaimState;
import deep_pills.model.enums.types.ClaimType;

import java.util.Date;

public record ClaimItemAdminDTO(Long id, Date date, ClaimType type, ClaimState status) {
}
