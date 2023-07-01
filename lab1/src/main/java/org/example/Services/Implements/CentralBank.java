package org.example.Services.Implements;


import org.example.BankAccounts.BankAccount;
import org.example.BankAccounts.CreditAccount;
import org.example.DataTime.DataTime;
import org.example.Entities.Bank;
import org.example.Exceptions.*;
import org.example.OperationsAndTransactions.MoneyTransfer;
import org.example.OperationsAndTransactions.Transaction;
import org.example.Services.CentralBankInterface;

import java.math.BigDecimal;
import java.util.*;

public class CentralBank implements CentralBankInterface {

    private List<BankAccount> accounts = new ArrayList<>();
    private List<Bank> banks = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();
    public List<Bank> banksRead = Collections.unmodifiableList(banks);
    public List<BankAccount> accountsRead = Collections.unmodifiableList(accounts);

    /**
     * Метод, который создает объект "банк" и добавляет его в лист.
     * @param name - имя банка.
     * @param commission - комиссия банка.
     * @param percent - проценты банка.
     * @return Bank
     */
    public Bank addBank(String name, BigDecimal commission, BigDecimal percent) throws InvalidNameException {
        var bank = new Bank(UUID.randomUUID(), name, commission, percent);
        banks.add(bank);
        return bank;
    }

    /**
     * Метод, который добавляет счет к банку
     * @param bank - объект "Банк"
     * @param account - объект "Счет"
     */
    public void addAccountToBank(Bank bank, BankAccount account) {
        bank.addAccountToBank(account);
        accounts.add(account);
    }

    /**
     * Метод,
     */
    public void cashOperationsOfTheAccountLastDayOfTheMonthNotify() {
        List<BankAccount> accountsForChange = accounts.stream().filter(i -> i.getClass() != CreditAccount.class).toList();
        for (BankAccount account : accountsForChange) {
            account.changingTheBalance();
        }
    }

    /**
     * Метод, который пополняет счет клиента, при условии, что клиент не является сомнительным.
     * @param id - id транзакции.
     * @param account - объект "Счет".
     * @param newMoney - количество денег для пополнения.
     * @return объект "Транзакция".
     * @throws SuspiciousAccountException - исключение, которое вызывается при попытки совершения
     * транзакции с/на сомнительного счета.
     */

    public Transaction accountReplenishment(UUID id, BankAccount account, BigDecimal newMoney) throws SuspiciousAccountException {
        account.getOwner().checkingForASuspiciousAccount();
        var transaction = new Transaction(id, newMoney, account, "+");
        account.replenishmentOfTheBalance(newMoney);
        transactions.add(transaction);
        return transaction;
    }

    /**
     * Метод, который списывает деньги со счета клиента, при условии, что клиент не является сомнительным.
     * @param id - id транзакции.
     * @param account - объект "Счет".
     * @param minusMoney - количество денег для списания.
     * @return объект "Транзакция".
     * @throws AMonetaryTransactionIsNotPossibleException - исключение, которое вызывается при попытки списать деньги с депозитного счета.
     * @throws NotEnoughMoneyException - исключение, которое вызывается если на счету недостаточно средств.
     * @throws SuspiciousAccountException - исключение, которое вызывается при попытки совершения.
     * транзакции с/на сомнительного счета.
     */

    public Transaction debitingMoneyFromAnAccount(UUID id, BankAccount account, BigDecimal minusMoney) throws AMonetaryTransactionIsNotPossibleException, NotEnoughMoneyException, SuspiciousAccountException {
        account.getOwner().checkingForASuspiciousAccount();
        var transaction = new Transaction(id, minusMoney, account, "-");
        account.withdrawingMoneyFromTheBalance(minusMoney, false);
        transactions.add(transaction);
        return transaction;
    }

    /**
     * Метод, который выполняет денежный перевод.
     * @param account - объект "Счет" (отправитель).
     * @param money - количество денег.
     * @param accountTo - объект "Счет" (получатель).
     * @return объект "Транзакция".
     * @throws AMonetaryTransactionIsNotPossibleException - исключение, которое вызывается при попытки списать деньги с депозитного счета.
     * @throws NotEnoughMoneyException - исключение, которое вызывается если на счету недостаточно средств.
     * @throws SuspiciousAccountException - исключение, которое вызывается при попытки совершения.
     */

    public MoneyTransfer moneyTransfer(BankAccount account, BigDecimal money, BankAccount accountTo) throws AMonetaryTransactionIsNotPossibleException, NotEnoughMoneyException, SuspiciousAccountException {
        account.getOwner().checkingForASuspiciousAccount();
        accountTo.getOwner().checkingForASuspiciousAccount();
        UUID id = UUID.randomUUID();
        debitingMoneyFromAnAccount(id, account, money);
        accountReplenishment(id, accountTo, money);
        var transaction = new MoneyTransfer(id, money, account, accountTo, "-", "+");
        return transaction;
    }

    /**
     * Метод, который отменяет транзакцию.
     * @param transaction - объект "Транзакция".
     * @throws NoSuchTransactionException - исклюсение, которое вызывается если аргументом метода передается не существующая транзакция.
     */

    public void cancellationOfTheTransaction(Transaction transaction) throws NoSuchTransactionException {
        List<Transaction> allTransactions = transactions.stream().filter(i -> i.getId() == transaction.getId()).toList();
        if (allTransactions == null) throw new NoSuchTransactionException("Не сущетвует транзакции.");

        for (var suchTransaction : allTransactions) {
            String type = suchTransaction.getTypeOfTransactions();
            suchTransaction.getAccount().cancellationTransferOfTheTransaction(suchTransaction, type);
        }
    }

    /**
     * Метод, который изменяет условия банка.
     * @param bank - объект "Банк".
     * @param newCommission - новая комиссия банка.
     * @param newPercent - новые проценты банка.
     */

    public void changingConditionsBank(Bank bank, BigDecimal newCommission, BigDecimal newPercent) {
        bank.changingConditionsBank(newCommission, newPercent);
    }

    /**
     * Метод, который возвращает объект "Банк" по его имени.
     * @param bankName - имя банка.
     * @return - объект "Банк".
     * @throws NoSuchBankException - исключение, которое вызывается если объект "Банк" не найден.
     */

    public Optional<Bank> getBank(String bankName) throws NoSuchBankException {
        return Optional.ofNullable(banks.stream().filter(elem -> elem.getName().equals(bankName)).findFirst().orElseThrow(() -> new NoSuchBankException(bankName)));
    }

    /**
     * Метод, который возвращает объект счета по его имени и номера паспорта.
     * @param name - имя клиента.
     * @param passportNumber - номер паспорта.
     * @return - объект "Счет".
     * @throws NoSuchAccountException - исключение, которое вызывается если объект "Счет" не найден.
     */


    public Optional<BankAccount> findAccount(String name, String passportNumber) throws NoSuchAccountException {
        return Optional.ofNullable(accounts.stream().filter(i -> i.getOwner().getName() == name && i.getOwner().getPassport() == passportNumber).findFirst().orElseThrow(() -> new NoSuchAccountException(name)));
    }

    /**
     * Метод, который проверяет день месяца. Если последний день, то вызывается метод cashOperationsOfTheAccountLastDayOfTheMonthNotify()
     * который начисляет ежемесячно проценты на остаток.
     * @param value - объект "DataTime".
     * @throws InvalidNumberOfDayException - исключение, которое вызывается если аргумент метода невалидный.
     */


    public void checkTheDateOfTheMonth(DataTime value) throws InvalidNumberOfDayException {
        if (value.getMonthDay() < 0) throw new InvalidNumberOfDayException(value.getMonthDay());
        int сountDaysInMonth = 28 + ((value.getNumberOfMonth() + (value.getNumberOfMonth() / 8)) % 2) + (2 % value.getNumberOfMonth()) + (1 / value.getNumberOfMonth() * 2);

        if (value.getMonthDay() > сountDaysInMonth) throw new InvalidNumberOfDayException(value.getMonthDay());

        if (value.getMonthDay() == сountDaysInMonth) {
            cashOperationsOfTheAccountLastDayOfTheMonthNotify();
        }
    }
}
