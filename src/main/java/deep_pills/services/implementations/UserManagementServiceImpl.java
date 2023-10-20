package deep_pills.services.implementations;

import deep_pills.dto.emails.EMailDTO;
import deep_pills.model.entities.accounts.Account;
import deep_pills.model.enums.states.AccountState;
import deep_pills.model.enums.types.EMailType;
import deep_pills.repositories.accounts.AccountRepository;
import deep_pills.services.interfaces.EMailService;
import deep_pills.services.interfaces.UserManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserManagementServiceImpl implements UserManagementService {
    private final AccountRepository accountRepository;
    private final EMailService eMailService;
    @Override
    @Transactional
    public String disablePhysician(Long physicianId) throws Exception {
        return disableAccount(physicianId).getState().name();
    }

    @Override
    @Transactional
    public String enablePhysician(Long physicianId) throws Exception {
        return enableAccount(physicianId).getState().name();
    }

    @Override
    @Transactional
    public String disablePatient(Long patientId) throws Exception {
        return disableAccount(patientId).getState().name();
    }

    @Override
    @Transactional
    public String enablePatient(Long patientId) throws Exception {
        return enableAccount(patientId).getState().name();
    }

    private Account getAccountById(Long id) throws Exception {
        Optional<Account> optional = accountRepository.findById(id);

        if(optional.isEmpty()) throw new Exception("No account was found with id: " + id);
        return optional.get();
    }
    private Account enableAccount(Long id) throws Exception {
        Account account = getAccountById(id);

        if(account.getState().equals(AccountState.ACTIVE)) throw new Exception("Account already enabled");

        account.setState(AccountState.ACTIVE);
        accountRepository.save(account);
        eMailService.sendEmail(new EMailDTO(account.getEmail(),
                "This JUAN from the DeepPills Team! We're happy to say that your account has been enabled.",
                "Account enabled",
                EMailType.ACCOUNT_STATE_UPDATED,
                account.getId()));
        return account;
    }

    private Account disableAccount(Long id) throws Exception {
        Account account = getAccountById(id);

        if(account.getState().equals(AccountState.INACTIVE)) throw new Exception("Account already disabled");

        account.setState(AccountState.INACTIVE);
        accountRepository.save(account);
        eMailService.sendEmail(new EMailDTO(account.getEmail(),
                "This JUAN from the DeepPills Team! We're sorry to say that your account has been disabled. Pleas contact us for more info.",
                "Account disabled",
                EMailType.ACCOUNT_STATE_UPDATED,
                account.getId()
                ));
        return account;
    }
}
