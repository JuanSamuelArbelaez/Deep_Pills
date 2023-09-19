package deep_pills.model.enums;

import jakarta.persistence.Entity;

import java.io.Serializable;

@Entity
public enum Claim_State implements Serializable {
    ACTIVE,
    INACTIVE,
    SOLVED;
}
