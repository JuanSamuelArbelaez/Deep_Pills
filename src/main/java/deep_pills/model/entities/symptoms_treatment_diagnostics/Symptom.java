package deep_pills.model.entities.symptoms_treatment_diagnostics;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Symptom")
public class Symptom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "symtpomId")
    private Long symptomId;

    @Column(name = "symptomName")
    private String symptomName;

    @Column(name = "symptomDescription")
    private String symptomDescription;
}
