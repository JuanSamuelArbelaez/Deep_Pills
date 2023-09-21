package deep_pills.model.entities.accounts;
import deep_pills.model.entities.accounts.users.physicians.Physician_Registration;
import deep_pills.model.entities.claims.Claim_Info;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Admin")
public class Admin extends Account {
    private List<Physician_Registration> physicianRegistrationList;
    private List<Claim_Info> claimInfoList;

}
