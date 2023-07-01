package org.example.BankAccounts;

import lombok.Getter;
import lombok.Setter;
import org.example.Entities.Client;
import org.example.Exceptions.InvalidNameException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

/**
 * Класс, описывающий объект "Дебетовый счет".
 */

public class DebitAccount extends BankAccount {
    @Getter
    @Setter
    private BigDecimal percent;
    public DebitAccount(UUID id, BigDecimal percent, BigDecimal balance, Client owner) throws InvalidNameException {
        super(id, balance, owner); {
            this.percent = checkNotNullBigDecimal(percent);
        }
    }

    /**
     * Метод, который в конце месяца начисляет кэшбэк
     */
    public void changingTheBalance() {
        BigDecimal cashBack = getBalance().divide(percent.multiply(new BigDecimal(100)), 2, RoundingMode.HALF_UP);
        setBalance(getBalance().add(cashBack));
        getOwner().addHistoryOfTransactions("new Balance:" + getBalance());
    }
}
