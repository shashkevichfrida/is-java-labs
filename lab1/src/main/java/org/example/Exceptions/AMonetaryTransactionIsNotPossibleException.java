package org.example.Exceptions;

public class AMonetaryTransactionIsNotPossibleException extends Exception{
    public AMonetaryTransactionIsNotPossibleException(String message) {
        super(message);
    }
}
