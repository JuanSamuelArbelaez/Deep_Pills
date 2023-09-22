package deep_pills.model.entities.accounts.users.physicians;

import deep_pills.model.entities.accounts.Admin;
import deep_pills.model.entities.accounts.users.UserRegistration;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Physician_Registration")
public class Physician_Registration extends UserRegistration {
    @ManyToOne
    @Column(name = "patient_Id")
    private Physician physician;

    @ManyToOne
    @Column(name = "admin_Id")
    private Admin admin;
}
