package deep_pills.model.entities.accounts.users.patients;
import deep_pills.model.entities.accounts.users.UserRegistration;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PatientRegistration")
public class PatientRegistration extends UserRegistration {
    @OneToOne
    @JoinColumn(name = "patientId")
    private Patient patient;
}
