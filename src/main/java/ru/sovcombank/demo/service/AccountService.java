package ru.sovcombank.demo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sovcombank.demo.model.Account;
import ru.sovcombank.demo.repository.AccountRepository;

@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Account createNewDepositAccount(Long userId) {
        Account account = new Account();
        account.setType(Account.Type.DEPOSIT);
        account.setUserId(userId);
        return accountRepository.save(account);
    }

    public Account createNewCreditAccount(Long userId) {
        Account account = new Account();
        account.setType(Account.Type.CREDIT);
        account.setUserId(userId);
        return accountRepository.save(account);
    }

    public void deleteAccount(Long accountId) {
        accountRepository.deleteById(accountId);
    }
}
