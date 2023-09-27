package deep_pills.model.entities.accounts.users;
import deep_pills.model.entities.accounts.Account;

import deep_pills.model.entities.passwordRecovery.PasswordRecoveryRequest;
import deep_pills.model.enums.lists.City;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User extends Account {
    @Column(name = "personalId", unique = true, length = 15)
    private String personalId;

    @Column(name = "name", length = 20)
    private String name;

    @Column(name = "lastName", length = 20)
    private String lastName;

    @Column(name = "phone", length = 15)
    private String phone;

    @Enumerated
    @Column(name = "city")
    private City city;

    @Column(name = "pictureUrl", columnDefinition = "TINYTEXT")
    private String pictureUrl;

    @OneToMany(mappedBy = "user")
    private List<PasswordRecoveryRequest> passwordRecoveryRequestList;
}
