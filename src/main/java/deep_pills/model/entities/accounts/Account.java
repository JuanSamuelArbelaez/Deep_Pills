package deep_pills.model.entities.accounts;

import deep_pills.model.entities.claims.Message;
import deep_pills.model.entities.notifications.EMail;
import jakarta.persistence.*;
import lombok.*;
import deep_pills.model.enums.states.AccountState;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Account")
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Account implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "accountId", length = 10, nullable = false)
    private Long id;

    @Column(name = "email", unique = true, length = 50, nullable = false)
    private String email;

    @Column(name = "pswd", nullable = false)
    private String password;

    @Enumerated
    @JoinColumn(name = "state", referencedColumnName = "id_UserState")
    private AccountState state;

    @OneToMany(mappedBy = "recipient")
    private List<Message> messagesReceived;

    @OneToMany(mappedBy = "sender")
    private List<Message> messagesSent;

    @OneToMany(mappedBy = "account")
    private List<EMail> eMailsReceived;
}
