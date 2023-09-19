package deep_pills.model.entities.accounts.users.patients;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Patient_Registration")
public class Patient_Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "register_ID")
    private Long registerId;

    @OneToOne(mappedBy = "patient")
    @Column(name = "patient_ID")
    private Patient patient;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    // Getters and setters
}
