package deep_pills.model.enums;

import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serializable;

public enum Claim_State implements Serializable {
    ACTIVE,
    INACTIVE,
    SOLVED;

}
