package deep_pills.model.entities.accounts.users.patients;
import deep_pills.model.entities.accounts.users.User_Registration;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Patient_Registration")
public class Patient_Registration extends User_Registration {
    @OneToOne(mappedBy = "patient")
    @Column(name = "patient_ID")
    private Patient patient;
}
