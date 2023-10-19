package deep_pills.dto.authentications;

import jakarta.validation.constraints.NotBlank;
public record TokenDTO (
        @NotBlank
        String token){
}
