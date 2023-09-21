package deep_pills.services;

import deep_pills.dto.logins.*;

public interface Authentication_Service {
    Long login(Login_DTO login_dto) throws Exception;
}
