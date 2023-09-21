package deep_pills.model.entities.accounts.users;
import deep_pills.model.entities.accounts.Account;

import deep_pills.model.enums.City;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "User")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User extends Account {
    @Column(name = "personal_id", unique = true)
    private String personalId;

    @Column(name = "name", length = 20)
    private String name;

    @Column(name = "lastName", length = 20)
    private String lastName;

    @Column(name = "phone", length = 15)
    private String phone;

    @Enumerated
    @JoinColumn(name = "city_id", referencedColumnName = "id_City")
    private City city;

    @Column(name = "picture_Url", columnDefinition = "TINYTEXT")
    private String pictureUrl;

    // Getters and setters
}
