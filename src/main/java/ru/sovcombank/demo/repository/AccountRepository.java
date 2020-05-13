package ru.sovcombank.demo.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import ru.sovcombank.demo.model.Account;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {

    @Query("SELECT * FROM account WHERE user_id = :userId")
    List<Account> findByUserId(Long userId);
}
