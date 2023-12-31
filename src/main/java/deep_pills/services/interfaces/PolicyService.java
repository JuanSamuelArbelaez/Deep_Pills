package deep_pills.services.interfaces;

import deep_pills.dto.memberships.PolicyDetailsDTO;
import deep_pills.dto.memberships.PolicyStateDTO;
import deep_pills.model.enums.states.PolicyState;
import jakarta.validation.constraints.NotNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PolicyService {
    @Transactional
    Long newPolicy(@NotNull PolicyDetailsDTO policyDetailsDTO) throws Exception;

    @Transactional
    PolicyState setPolicyState(@NotNull PolicyStateDTO policyStateDTO) throws Exception;

    @Transactional
    List<PolicyDetailsDTO> listAllPolicies() throws Exception;

    @Transactional
    PolicyDetailsDTO searchPolicyById(@NotNull Long policyId) throws Exception;
}
