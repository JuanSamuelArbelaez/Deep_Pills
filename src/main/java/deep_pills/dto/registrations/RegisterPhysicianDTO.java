package deep_pills.dto.registrations;

import deep_pills.model.enums.lists.Specialization;

import java.util.List;

public record RegisterPhysicianDTO(String email, String password, String personalId, Long adminId, List<Integer> specializationsId) {
}
