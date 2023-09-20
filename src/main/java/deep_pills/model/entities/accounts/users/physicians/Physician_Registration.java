package deep_pills.model.entities.accounts.users.physicians;

import deep_pills.model.entities.accounts.users.User_Registration;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Physician_Registration")
public class Physician_Registration extends User_Registration {
    @ManyToOne
    @Column(name = "patient_ID")
    private Physician physician;
}
