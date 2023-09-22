package deep_pills.model.entities.notifications;

import deep_pills.model.entities.accounts.users.UserRegistration;
import deep_pills.model.entities.appointments.Appointment;
import deep_pills.model.entities.passwordRecovery.PasswordRecoveryRequest;
import deep_pills.model.enums.states.EmailState;
import deep_pills.model.enums.types.EMailType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "email")
public class EMail implements Serializable {
    @Id
    @Column(name = "emailId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long emailId;

    @Column(name = "message")
    private String message;

    @Enumerated
    @Column(name = "type")
    private EMailType emailType;

    @Enumerated
    @Column(name = "state")
    private EmailState emailState;

    @ManyToOne
    @JoinColumn(name = "passwordRecoveryRequest")
    private PasswordRecoveryRequest passwordRecoveryRequest;

    @ManyToOne
    @JoinColumn(name = "registrationAlert")
    private UserRegistration userRegistration;

    @ManyToOne
    @JoinColumn(name = "appointmentScheduling")
    private Appointment appointment;
}
