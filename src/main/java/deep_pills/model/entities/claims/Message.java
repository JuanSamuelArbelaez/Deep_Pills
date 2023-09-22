package deep_pills.model.entities.claims;

import deep_pills.model.entities.accounts.Account;
import deep_pills.model.enums.Message_Type;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "message_Id")
    private Long messageId;

    @OneToOne
    @JoinColumn(name = "sender_Id")
    private Account sender;

    @OneToOne
    @JoinColumn(name = "recipient_Id")
    private Account recipient;

    @Column(name = "date")
    private Date date;

    @Column(name = "message")
    private String message;

    @Column(name = "message_Type")
    @Enumerated
    private Message_Type messageType;

    @ManyToOne
    @JoinColumn(name = "claim_Id")
    private Claim claim;

}
