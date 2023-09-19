package deep_pills.model.entities.accounts.users.physicians;

import deep_pills.model.entities.accounts.users.patients.Patient;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Physician_Registration")
public class Physician_Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "register_ID")
    private Long registerId;

    @ManyToOne
    @JoinColumn(name = "patient_ID")
    private Physician physician;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
}
