package org.example.Entities;

import org.example.Exceptions.InvalidNameException;

public interface ClientBuilder {
    ClientBuilder setName(String name) throws InvalidNameException;
    ClientBuilder setPassport(String passport);
    ClientBuilder setAddress(String address);
    ClientBuilder setBank(Bank bank);
    ClientBuilder setNotification(boolean notification);
    Client build();

}
