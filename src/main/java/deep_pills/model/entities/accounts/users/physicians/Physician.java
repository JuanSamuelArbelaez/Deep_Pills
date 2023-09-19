package deep_pills.model.entities.accounts.users.physicians;

import deep_pills.model.entities.accounts.users.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Physician")
public class Physician extends User {

}
