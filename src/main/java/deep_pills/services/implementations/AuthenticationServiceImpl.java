package deep_pills.services.implementations;

import deep_pills.dto.logins.LoginDTO;
import deep_pills.model.entities.accounts.Account;
import deep_pills.repositories.accounts.AccountRepository;
import deep_pills.services.interfaces.AuthenticationService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    public Long login(@NotNull LoginDTO loginDto) throws Exception {
        Optional<Account> optional = accountRepository.fingByEmail(loginDto.email()); //Query for account search by email
        Account account = null;

        if(optional.isEmpty()) throw new Exception("Wrong Email, account not found"); //Validation of query results
        else account = optional.get();

        if(!account.getPassword().equals(loginDto.password())) throw new Exception("Wrong Password"); //Account authentication

        return account.getId();//Return of accountId
    }
}
