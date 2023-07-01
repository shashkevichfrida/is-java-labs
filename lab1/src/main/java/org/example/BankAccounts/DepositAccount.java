package org.example.BankAccounts;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.example.Entities.Client;
import org.example.Exceptions.AMonetaryTransactionIsNotPossibleException;
import org.example.Exceptions.InvalidNameException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

/**
 * Класс, описывающий объект "Депозитный счет".
 */

public class DepositAccount extends BankAccount {
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private BigDecimal percent;
    public DepositAccount(UUID id, BigDecimal percent, BigDecimal balance, Client owner) throws InvalidNameException {
        super(id, balance, owner);
        {
            this.percent = checkNotNullBigDecimal(percent);
        }
    }

    /**
     * Метод, который начисляет кэшбэк в конце месяца
     */
    public void changingTheBalance() {
        BigDecimal dayPercent = percent.divide(new BigDecimal(365), 2, RoundingMode.HALF_UP);
        setBalance(getBalance().multiply(dayPercent));
        getOwner().addHistoryOfTransactions("new Balance:" + getBalance());
    }

    /**
     * Метод, при вызыве которого вызывается исключение.
     * @param moneyToWithdraw - количество денег для спсания.
     * @throws AMonetaryTransactionIsNotPossibleException - исключение, которое вызывается при попытки списания средств с депозитного счета.
     */
    public void withdrawingMoneyFromTheBalance(BigDecimal moneyToWithdraw, boolean typeOfAcc) throws AMonetaryTransactionIsNotPossibleException {
        throw new AMonetaryTransactionIsNotPossibleException("Денежный перевод с депозитного счета невозможен.");
    }

}
