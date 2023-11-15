package deep_pills.services.interfaces;

import deep_pills.dto.authentications.TokenDTO;
import deep_pills.dto.logins.*;
import io.jsonwebtoken.Claims;
import org.springframework.transaction.annotation.Transactional;

public interface AuthenticationService {
    TokenDTO login(LoginDTO loginDTO) throws Exception;

    @Transactional
    Claims decode(String jwtToken);
}
