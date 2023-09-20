package deep_pills.model.enums;

import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
public enum Claim_State implements Serializable {
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    SOLVED("Solved");

    private String state;
    Claim_State(String state) {this.state = state;}
}
