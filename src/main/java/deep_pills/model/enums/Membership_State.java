package deep_pills.model.enums;

import jakarta.persistence.Entity;

@Entity
public enum Membership_State {
    ACTIVE,
    INACTIVE,
    ARREAR;
}
