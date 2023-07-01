package org.example.BankAccounts;

import lombok.Getter;
import lombok.Setter;
import org.example.Entities.Client;
import org.example.Exceptions.InvalidNameException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

/**
 * Класс, описывающий объект "Кредитный счет".
 */
public class CreditAccount extends BankAccount {
    @Getter
    @Setter
    private BigDecimal commission;
    public CreditAccount(UUID id, BigDecimal commission, BigDecimal balance, Client owner) throws InvalidNameException {
        super(id, balance, owner); {
            this.commission = checkNotNullBigDecimal(commission);
        }
    }

    /**
     * Метод, который списывает с кредитного счета процент, если счет ушел в минус.
     */

    @Override
    public void changingTheBalance() {
        BigDecimal withdrawMoney = getBalance().divide(commission.multiply(new BigDecimal(100)), 2, RoundingMode.HALF_UP);
        setBalance(getBalance().add(withdrawMoney));
        getOwner().addHistoryOfTransactions("new Balance:" + getBalance());
    }
}
