package org.example.OperationsAndTransactions;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.example.BankAccounts.BankAccount;
import org.example.Exceptions.NoSuchTypeOfTransactionException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Класс, который описывает транзакцию.
 */
@Getter
@Setter(AccessLevel.PRIVATE)
public class Transaction {
     private UUID id;
    private BigDecimal money;
    private BankAccount account;
    private String typeOfTransaction;
    public Transaction(UUID id, BigDecimal money, BankAccount account, String typeOfTransaction){
        this.id = id;
        this.money = money;
        this.account = account;
        this.typeOfTransaction = typeOfTransaction;
    }

    /**
     * Метод, который возвращает тип транзакции.
     * @return строка типа транзакции.
     */
    public String getTypeOfTransactions() {
        Map<String,String> typeOfTransactions = new HashMap<String,String>();
        typeOfTransactions.put("+", "Account replenishment");
        typeOfTransactions.put("-", "Debiting money from an Account");

        boolean keyExists = typeOfTransactions.containsKey(typeOfTransaction);
        if (!keyExists) {
            try {
                throw new NoSuchTypeOfTransactionException(typeOfTransaction);
            } catch (NoSuchTypeOfTransactionException e) {
                throw new RuntimeException(e);
            }
        }

        String type = typeOfTransactions.get(typeOfTransaction);
        return type;
    }
}
