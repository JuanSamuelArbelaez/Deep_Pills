package deep_pills.model.entities.claims;

import deep_pills.model.entities.accounts.Account;
import deep_pills.model.enums.types.MessageType;
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
    @Column(name = "messageId")
    private Long messageId;

    @ManyToOne
    @JoinColumn(name = "senderId")
    private Account sender;

    @ManyToOne
    @JoinColumn(name = "recipientId")
    private Account recipient;

    @Column(name = "date")
    private Date date;

    @Column(name = "message")
    private String message;

    @Column(name = "messageType")
    @Enumerated
    private MessageType messageType;

    @ManyToOne
    @JoinColumn(name = "claimId")
    private Claim claim;

}
