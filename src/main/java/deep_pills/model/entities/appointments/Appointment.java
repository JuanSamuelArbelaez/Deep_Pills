package deep_pills.model.entities.appointments;
import jakarta.persistence.*;

import lombok.*;



@Entity
@Table(name = "Appointment")
public class Appointment {
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
