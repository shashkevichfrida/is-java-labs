package org.example.Services;

import org.example.BankAccounts.BankAccount;
import org.example.DataTime.DataTime;
import org.example.Entities.Bank;
import org.example.Exceptions.*;
import org.example.OperationsAndTransactions.MoneyTransfer;
import org.example.OperationsAndTransactions.Transaction;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;



public interface CentralBankInterface {

    public Bank addBank(String name, BigDecimal commission, BigDecimal percent) throws InvalidNameException;
    public void addAccountToBank(Bank bank, BankAccount account);

    public Transaction accountReplenishment(UUID id, BankAccount account, BigDecimal newMoney) throws SuspiciousAccountException;

    public Transaction debitingMoneyFromAnAccount(UUID id, BankAccount account, BigDecimal minusMoney) throws AMonetaryTransactionIsNotPossibleException, NotEnoughMoneyException, SuspiciousAccountException;
    public MoneyTransfer moneyTransfer(BankAccount account, BigDecimal money, BankAccount accountTo) throws AMonetaryTransactionIsNotPossibleException, NotEnoughMoneyException, SuspiciousAccountException;
    public void cashOperationsOfTheAccountLastDayOfTheMonthNotify();

    public void cancellationOfTheTransaction(Transaction transaction) throws NoSuchTransactionException, NoSuchTransactionException;

    public void changingConditionsBank(Bank bank, BigDecimal newCommission, BigDecimal newPercent);

    public Optional<Bank> getBank(String bankName) throws NoSuchBankException;


    public Optional<BankAccount> findAccount(String name, String phoneNumber) throws NoSuchAccountException;
    public void checkTheDateOfTheMonth(DataTime value) throws InvalidNumberOfDayException;
}
