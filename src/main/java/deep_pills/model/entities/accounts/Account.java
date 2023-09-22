package deep_pills.model.entities.accounts;

import deep_pills.model.entities.claims.Message;
import jakarta.persistence.*;
import lombok.*;
import deep_pills.model.enums.Account_State;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Account")
@Inheritance(strategy = InheritanceType.JOINED)
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Account", length = 10, nullable = false)
    private int id;

    @Column(name = "email", unique = true, length = 25, nullable = false)
    private String email;

    @Column(name = "pswd", length = 15, nullable = false)
    private String password;

    @Enumerated
    @JoinColumn(name = "state", referencedColumnName = "id_UserState")
    private Account_State state;

    @OneToOne(mappedBy = "recieptant")
    private List<Message> messageList;

}
