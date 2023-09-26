package deep_pills.services.implementations;

import deep_pills.dto.logins.LoginDTO;
import deep_pills.repositories.accounts.AccountRepository;
import deep_pills.services.interfaces.AuthenticationService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private AccountRepository accountRepository;
    @Override
    public Long login(LoginDTO login_dto) throws Exception {
        return null;
    }
}
