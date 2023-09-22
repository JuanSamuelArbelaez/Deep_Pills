package deep_pills.model.entities.accounts.users.physicians;

import deep_pills.model.entities.accounts.Admin;
import deep_pills.model.entities.accounts.users.UserRegistration;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "PhysicianRegistration")
public class PhysicianRegistration extends UserRegistration {
    @OneToOne
    @JoinColumn(name = "physicianId")
    private Physician physician;

    @ManyToOne
    @JoinColumn(name = "adminId")
    private Admin admin;
}
