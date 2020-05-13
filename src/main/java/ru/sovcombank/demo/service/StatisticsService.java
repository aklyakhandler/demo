package ru.sovcombank.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovcombank.demo.model.User;
import ru.sovcombank.demo.repository.AccountRepository;
import ru.sovcombank.demo.repository.CardRepository;
import ru.sovcombank.demo.repository.UserRepository;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StatisticsService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    public long cardCount() {
        return cardRepository.count();
    }

    public long userCount() {
        return userRepository.count();
    }

    public long accountCount() {
        return accountRepository.count();
    }

    public long countUsersHavingZeroAccounts() {
        Iterable<User> users = userRepository.findAll();
        AtomicInteger count = new AtomicInteger(0);
        users.forEach(user -> {
            if (accountRepository.findByUserId(user.getId()).iterator().hasNext()) {
                count.incrementAndGet();
            }
        });
        return count.get();
    }

}
