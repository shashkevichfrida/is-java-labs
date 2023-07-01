package org.example.Services;

import org.example.BankAccounts.CreditAccount;
import org.example.BankAccounts.DebitAccount;
import org.example.BankAccounts.DepositAccount;
import org.example.Entities.Bank;
import org.example.Entities.Client;
import org.example.Exceptions.InvalidNameException;

import java.math.BigDecimal;

public interface ServiceInterface {
    public DebitAccount addBankDebitAccount(Bank bank, BigDecimal money, Client owner) throws InvalidNameException;

    public DepositAccount addBankDepositAccount(Bank bank, BigDecimal money, Client owner) throws InvalidNameException;

    public CreditAccount addBankCreditAccount(Bank bank, BigDecimal money, Client owner) throws InvalidNameException;
}
