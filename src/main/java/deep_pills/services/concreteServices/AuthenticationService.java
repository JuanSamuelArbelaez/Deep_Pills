package deep_pills.services.concreteServices;

import deep_pills.dto.logins.*;

public interface AuthenticationService {
    Long login(LoginDTO login_dto) throws Exception;
}
