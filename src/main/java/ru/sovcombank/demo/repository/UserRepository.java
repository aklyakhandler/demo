package ru.sovcombank.demo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.sovcombank.demo.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
