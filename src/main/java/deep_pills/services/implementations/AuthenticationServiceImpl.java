package deep_pills.services.implementations;

import deep_pills.dto.authentications.TokenDTO;
import deep_pills.dto.logins.LoginDTO;
import deep_pills.model.entities.accounts.Account;
import deep_pills.model.entities.accounts.users.patients.Patient;
import deep_pills.model.entities.accounts.users.physicians.Physician;
import deep_pills.repositories.accounts.AccountRepository;
import deep_pills.services.interfaces.AuthenticationService;
import deep_pills.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AccountRepository accountRepository;
    private final JWTUtils jwtUtils;
    @Override
    public TokenDTO login(LoginDTO loginDTO) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Optional<Account> optionalAccount = accountRepository.findByEmail(loginDTO.email());
        if(optionalAccount.isEmpty()){
            throw new Exception("Incorrect E-mail address.");
        }
        Account account = optionalAccount.get();
        if(!passwordEncoder.matches(loginDTO.password(), account.getPassword()) ){
            throw new Exception("Incorrect password");
        }
        return new TokenDTO(createToken(account));
    }
    private String createToken(Account account){
        String role;
        String name;
        if(account instanceof Patient){
            role = "patient";
            name = ((Patient) account).getName();
        }else if( account instanceof Physician){
            role = "physician";
            name = ((Physician) account).getName();
        }else{
            role = "admin";
            name = "Admin";
        }
        Map<String, Object> map = new HashMap<>();
        map.put("role", role);
        map.put("name", name);
        map.put("id", account.getId());
        return jwtUtils.generarToken(account.getEmail(), map);
    }
}
