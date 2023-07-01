package org.example.Services.Implements;

import org.example.BankAccounts.CreditAccount;
import org.example.BankAccounts.DebitAccount;
import org.example.BankAccounts.DepositAccount;
import org.example.Entities.Bank;
import org.example.Entities.Client;
import org.example.Exceptions.InvalidNameException;
import org.example.Services.ServiceInterface;

import java.math.BigDecimal;
import java.util.UUID;

public class Service implements ServiceInterface {
    public CentralBank centralBank;
    public ClientService clientService;

    public Service() {
        centralBank = new CentralBank();
        clientService = new ClientService();
    }

    /**
     * Метод, который создает объект "Дебитовый счет".
     * @param bank - объект "Банк".
     * @param money - количество денег.
     * @param client - объект "Клиент".
     * @return объект "Дебитовый счет".
     */
    public DebitAccount addBankDebitAccount(Bank bank, BigDecimal money, Client client) throws InvalidNameException {
        DebitAccount debitAccount = new DebitAccount(UUID.randomUUID(), bank.getBankCommission(), money, client);
        centralBank.addAccountToBank(bank, debitAccount);

        clientService.addAccountToClient(client, debitAccount);

        return debitAccount;
    }

    /**
     * Метод, который создает объект "Депозитный счет".
     * @param bank - объект "Банк".
     * @param money - количество денег.
     * @param client - объект "Клиент".
     * @return объект "Депозитный счет".
     */

    public DepositAccount addBankDepositAccount(Bank bank, BigDecimal money, Client client) throws InvalidNameException {
        DepositAccount depositAccount = new DepositAccount(UUID.randomUUID(), bank.getBankPercent(), money, client);
        centralBank.addAccountToBank(bank, depositAccount);

        clientService.addAccountToClient(client, depositAccount);

        return depositAccount;
    }

    /**
     * Метод, который создает объект "Кредитный счет".
     * @param bank - объект "Банк".
     * @param money - количество денег.
     * @param client - объект "Клиент".
     * @return объект "Кредитный счет".
     */

    public CreditAccount addBankCreditAccount(Bank bank, BigDecimal money, Client client) throws InvalidNameException {
        CreditAccount creditAccount = new CreditAccount(UUID.randomUUID(), bank.getBankPercent(), money, client);
        centralBank.addAccountToBank(bank, creditAccount);

        clientService.addAccountToClient(client, creditAccount);

        return creditAccount;
    }
}
