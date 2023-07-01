package org.example.Entities;

import org.example.Exceptions.InvalidNameException;

import java.util.UUID;

public class ClientBuilderImpl implements ClientBuilder {
    private Client client = null;
    @Override
    public ClientBuilder setName(String name) throws InvalidNameException {
        client = new Client(UUID.randomUUID(), name);
        return this;
    }

    @Override
    public ClientBuilder setPassport(String passport) {
        client.setPassport(passport);
        return this;
    }

    @Override
    public ClientBuilder setAddress(String address) {
        client.setAddress(address);
        return this;
    }

    @Override
    public ClientBuilder setBank(Bank bank) {
        client.setBank(bank);
        return this;
    }

    @Override
    public ClientBuilder setNotification(boolean notification) {
        client.setNotification(notification);
        return this;
    }

    @Override
    public Client build() {
        return client;
    }
}
