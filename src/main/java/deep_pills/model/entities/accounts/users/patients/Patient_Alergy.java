package deep_pills.model.entities.accounts.users.patients;
import deep_pills.model.enums.Allergy;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@Setter
@Table(name = "Patient_Alergy")
public class Patient_Alergy {
    @Id
    @ManyToOne
    @JoinColumn(name = "patient_ID")
    private Patient patient;

    @Id
    @ManyToOne
    @JoinColumn(name = "alergy_ID")
    private Allergy alergy;

    // Getters and setters
}
