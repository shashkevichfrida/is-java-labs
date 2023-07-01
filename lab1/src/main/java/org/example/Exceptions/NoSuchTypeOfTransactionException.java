package org.example.Exceptions;
public class NoSuchTypeOfTransactionException extends Exception{
    public NoSuchTypeOfTransactionException(String transaction) { super(transaction + "is not exist");}
}
