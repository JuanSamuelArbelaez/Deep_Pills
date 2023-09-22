package deep_pills.model.entities.accounts;

import deep_pills.model.entities.accounts.users.physicians.PhysicianRegistration;
import deep_pills.model.entities.claims.ClaimInfo;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Admin")
public class Admin extends Account {
    @OneToMany(mappedBy = "admin")
    private List<PhysicianRegistration> physicianRegistrationList;

    @OneToMany(mappedBy = "admin")
    private List<ClaimInfo> claimInfoList;
}
