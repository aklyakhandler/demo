package ru.sovcombank.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("account")
public class Account {
    @Id
    private Long id;
    private Type type;
    private Long userId;

    public enum Type {
        DEPOSIT, CREDIT
    }
}
