package deep_pills.services.implementations;

import deep_pills.dto.memberships.PolicyDetailsDTO;
import deep_pills.dto.memberships.PolicyStateDTO;
import deep_pills.model.entities.memberships.Policy;
import deep_pills.model.enums.states.PolicyState;
import deep_pills.repositories.memberships.PolicyRepository;
import deep_pills.services.interfaces.PolicyService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PolicyServiceImpl implements PolicyService {
    private final PolicyRepository policyRepository;
    @Override
    @Transactional
    public Long newPolicy(@NotNull PolicyDetailsDTO policyDetailsDTO) throws Exception{
        Policy policy = getPolicyFromOptional(policyRepository.findByCostAndMaxAppointmentsAndMaxPatients(policyDetailsDTO.cost(), policyDetailsDTO.maxAppointments(), policyDetailsDTO.maxPatients()));
        if(policy != null) throw new Exception("There already exists a policy with this properties (Policy: "+policy.getPolicyId()+")");
        policy = new Policy();
        policy.setCost(policyDetailsDTO.cost());
        policy.setName(policyDetailsDTO.name());
        policy.setMaxAppointments(policyDetailsDTO.maxAppointments());
        policy.setMaxPatients(policyDetailsDTO.maxPatients());
        policy.setDescription(policyDetailsDTO.description());
        policy.setState(PolicyState.ACTIVE);

        return policyRepository.save(policy).getPolicyId();
    }


    @Override
    @Transactional
    public PolicyState setPolicyState(@NotNull PolicyStateDTO policyStateDTO) throws Exception {
        Policy policy = getPolicyFromOptional(policyRepository.findById(policyStateDTO.policyId()));
        if(policy == null) throw new Exception("Policy "+policyStateDTO.policyId()+" not found");
        if(policy.getState().equals(policyStateDTO.policyState())) throw new Exception("Policy "+policyStateDTO.policyId()+" is "+policyStateDTO.policyState()+" already");
        policy.setState(policyStateDTO.policyState());
        policyRepository.save(policy);
        return policy.getState();
    }

    @Override
    @Transactional
    public List<PolicyDetailsDTO> listAllPolicies() throws Exception{
        List<PolicyDetailsDTO> policyDetails = new ArrayList<>();
        for(Policy policy : policyRepository.findAll()){
            policyDetails.add(new PolicyDetailsDTO(
                    policy.getPolicyId(),
                    policy.getName(),
                    policy.getDescription(),
                    policy.getCost(),
                    policy.getMaxAppointments(),
                    policy.getMaxPatients(),
                    policy.getState()
            ));
        }
        return policyDetails;
    }

    @Override
    @Transactional
    public PolicyDetailsDTO searchPolicyById(@NotNull Long policyId) throws Exception{
        Policy policy = getPolicyFromOptional(policyRepository.findById(policyId));
        if(policy == null) throw new Exception("Policy "+policyId +" not found");
        return new PolicyDetailsDTO(
                policy.getPolicyId(),
                policy.getName(),
                policy.getDescription(),
                policy.getCost(),
                policy.getMaxAppointments(),
                policy.getMaxPatients(),
                policy.getState()
        );
    }
    private Policy getPolicyFromOptional(Optional<Policy> optional){
        return optional.orElse(null);
    }
}
