package org.example.Services.Implements;

import org.example.BankAccounts.BankAccount;
import org.example.Entities.Bank;
import org.example.Entities.Client;
import org.example.Entities.ClientBuilderImpl;
import org.example.Exceptions.InvalidNameException;


public class ClientService {
    /**
     * Метод, который создает объект "Клиент".
     * @param name - имя клиента.
     * @param address - адрес клиента.
     * @param passport - номер паспорта.
     * @param bank - объект "Банк".
     * @param notification - идентификатор (подписка на уведомления).
     * @return объект "Клиент".
     */

    public Client addClient(String name, String address, String passport, Bank bank, boolean notification) throws InvalidNameException {
        Client client = new ClientBuilderImpl()
                .setName(name)
                .setAddress(address)
                .setPassport(passport)
                .setBank(bank)
                .setNotification(notification)
                .build();
        bank.addClient(client);
        return client;
    }

    /**
     * Метод, который добавляет счет к клиенту.
     * @param client - объект "Клиент".
     * @param account - объект "Счет".
     */
    public void addAccountToClient(Client client, BankAccount account) {
        client.addAccounts(account);
    }

}
