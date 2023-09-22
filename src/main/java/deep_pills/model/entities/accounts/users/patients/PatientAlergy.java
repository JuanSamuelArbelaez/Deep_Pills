package deep_pills.model.entities.accounts.users.patients;
import deep_pills.model.enums.Allergy;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@Setter
@Table(name = "Patient_Alergy")
public class PatientAlergy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "patient_allergy_Id")
    private long patient_allergy_Id;

    @ManyToOne
    @JoinColumn(name = "patient_Id")
    private Patient patient;


    @Enumerated(EnumType.STRING)
    @Column(name = "alergy_ID")
    private Allergy alergy;

    // Getters and setters
}
