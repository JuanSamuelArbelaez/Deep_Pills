package deep_pills.model.enums;

import jakarta.persistence.*;
import lombok.*;

@Entity
public enum Claim_Type {
    PETITION,
    COMPLAINT,
    REQUEST,
    SUGGESTION;

}
