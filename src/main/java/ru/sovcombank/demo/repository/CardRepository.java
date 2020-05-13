package ru.sovcombank.demo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.sovcombank.demo.model.Card;

public interface CardRepository extends CrudRepository<Card, Long> {
}
