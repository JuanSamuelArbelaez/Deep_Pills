package deep_pills.model.entities.passwordRecovery;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordRecoveryRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "passwordRecoveryRequestId")
    private long passwordRecoveryRequestId;
}
