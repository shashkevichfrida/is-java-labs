package org.example.OperationsAndTransactions;



import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.example.BankAccounts.BankAccount;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Класс, который описывает перевод денежных средств.
 */
@Getter
@Setter(AccessLevel.PRIVATE)

public class MoneyTransfer extends Transaction {
    private BankAccount accountTo;
    private String typeOfTransactionFrom;
    public MoneyTransfer(UUID id, BigDecimal money, BankAccount account, BankAccount accountTo, String typeOfTransaction, String typeOfTransactionFrom) {
        super(id, money, account, typeOfTransaction); {
            this.accountTo = accountTo;
            this.typeOfTransactionFrom = typeOfTransactionFrom;
        }
    }
}
