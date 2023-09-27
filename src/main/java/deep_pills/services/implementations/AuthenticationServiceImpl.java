package deep_pills.services.implementations;

import deep_pills.dto.logins.LoginDTO;
import deep_pills.model.entities.accounts.Account;
import deep_pills.repositories.accounts.AccountRepository;
import deep_pills.services.interfaces.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AccountRepository accountRepository;

    /**
     * Authenticates the account credentials contained in the DTO.
     * @param loginDto Data transfer object required for authentication. Must contain credentials.
     * @return Account ID if authenticated successfully.
     * @throws Exception Throws exceptions if the DTO or any of its credentials are null; if the search query by email returns null, or if the authentication failed.
     */
    @Override
    @Transactional
    public Long login(LoginDTO loginDto) throws Exception {
        //Pre-validation of dto and attributes
        if(loginDto == null) throw new IllegalArgumentException("loginDto cannot be null");
        if(loginDto.email() == null) throw new IllegalArgumentException("email cannot be null");
        if(loginDto.password() == null) throw new IllegalArgumentException("password cannot be null");

        Account account = accountRepository.searchByEmail(loginDto.email()); //Query for account search by email

        if(account == null) throw new Exception("Wrong Email, account not found"); //Validation of query results

        if(!account.getPassword().equals(loginDto.password())) throw new Exception("Wrong Password"); //Account authentication

        return account.getId();//Return of accountId
    }
}
