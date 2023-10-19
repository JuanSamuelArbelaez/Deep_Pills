package deep_pills.services.interfaces;

import deep_pills.dto.authentications.TokenDTO;
import deep_pills.dto.logins.*;

public interface AuthenticationService {
    TokenDTO login(LoginDTO loginDTO) throws Exception;
}
