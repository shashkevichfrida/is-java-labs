package org.example.Exceptions;

public class NoSuchBankException extends Exception{
    public NoSuchBankException(String bankName) {
        super(bankName + "is not exist");
    }
}
