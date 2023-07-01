package org.example.BankAccounts;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.example.Entities.CheckNotNull;
import org.example.Entities.Client;
import org.example.Exceptions.AMonetaryTransactionIsNotPossibleException;
import org.example.Exceptions.InvalidNameException;
import org.example.Exceptions.NotEnoughMoneyException;
import org.example.OperationsAndTransactions.Transaction;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Абстрактный класс, который описывает объект "Банковский счет".
 */

@Getter
@Setter(AccessLevel.PROTECTED)
public abstract class BankAccount extends CheckNotNull {
    private UUID id;
    private BigDecimal balance;
    private Client owner;
    public BankAccount(UUID id, BigDecimal balance, Client owner) throws InvalidNameException {
        this.id = id;
        this.balance = checkNotNullBigDecimal(balance);
        this.owner = owner;
    }

    /**
     * Абстрактный метод изменения баланса счета.
     */
    public abstract void changingTheBalance();

    /**
     * Метод, который пополняет баланс счета.
     * @param newMoney - количество денег для пополнения.
     */
    public void replenishmentOfTheBalance(BigDecimal newMoney){
        balance = balance.add(newMoney);
        owner.addHistoryOfTransactions("new Balance:" + balance);
    }

    /**
     * Метод, который списывает деньги со счета.
     * @param moneyToWithdraw - количество денег для спсания.
     * @throws AMonetaryTransactionIsNotPossibleException - исключение, которые вызывается при попытки списать деньги с кредитного счета.
     * @throws NotEnoughMoneyException - исключение, которое вызывается если на счету недостаточно средств.
     */
    public void withdrawingMoneyFromTheBalance(BigDecimal moneyToWithdraw, boolean typeOfAcc) throws AMonetaryTransactionIsNotPossibleException, NotEnoughMoneyException {
        if (balance.compareTo(moneyToWithdraw) == -1 && !typeOfAcc) {
            throw new NotEnoughMoneyException(balance);
        }
        else if (balance.compareTo(moneyToWithdraw) == -1 && typeOfAcc) changingTheBalance();
        balance = balance.subtract(moneyToWithdraw);
        owner.addHistoryOfTransactions("new Balance:" + balance);
    }

    /**
     * Метод, который отменяет транзакцию.
     * @param transaction - объект "Транзакция".
     * @param typeOfTransaction - строка тип транзакции.
     */
    public void cancellationTransferOfTheTransaction(Transaction transaction, String typeOfTransaction) {
        if (typeOfTransaction == "Account replenishment") {
            balance = balance.subtract(transaction.getMoney());
        }
        else {
            balance = balance.add(transaction.getMoney());
        }
        owner.addHistoryOfTransactions("new Balance:" + balance);
    }

}
