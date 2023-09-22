package deep_pills.model.entities.accounts.users.patients;
import deep_pills.model.entities.accounts.users.UserRegistration;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Patient_Registration")
public class PatientRegistration extends UserRegistration {
    @OneToOne(mappedBy = "patient")
    @Column(name = "patient_ID")
    private Patient patient;
}
