package org.example.Services;

import org.example.BankAccounts.BankAccount;
import org.example.Entities.Client;
import org.example.Entities.ClientBuilderImpl;

public interface ClientServiceInterface {
    public Client addClient(String name, String address, String passport, String phoneNumber);
    public void addAccountToClient(Client owner, BankAccount account);
}
