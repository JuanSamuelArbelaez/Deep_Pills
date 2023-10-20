package deep_pills.model.entities.notifications;

import deep_pills.model.entities.accounts.Account;
import deep_pills.model.entities.accounts.users.UserRegistration;
import deep_pills.model.entities.appointments.Appointment;
import deep_pills.model.entities.claims.Claim;
import deep_pills.model.entities.memberships.Membership;
import deep_pills.model.entities.passwordRecovery.PasswordRecoveryRequest;
import deep_pills.model.enums.states.EmailState;
import deep_pills.model.enums.types.EMailType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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

    @Column(name = "body", length = 1000)
    private String body;

    @Column(name = "subject")
    private String subject;

    @Enumerated
    @Column(name = "type")
    private EMailType emailType;

    @Email
    @Column(name = "recipientEmail")
    private String recipientEmail;

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
    @JoinColumn(name = "appointmentAlert")
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "accountAlert")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "membershipAlert")
    private Membership membership;

    @ManyToOne
    @JoinColumn(name = "claimAlert")
    private Claim claim;
}
