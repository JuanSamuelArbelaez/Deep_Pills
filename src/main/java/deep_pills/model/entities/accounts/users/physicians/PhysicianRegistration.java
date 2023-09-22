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
    @Column(name = "patientId")
    private Physician physician;

    @ManyToOne
    @Column(name = "adminId")
    private Admin admin;
}
