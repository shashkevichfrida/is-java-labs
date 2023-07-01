package org.example.Exceptions;

public class InvalidNumberOfDayException extends Exception{
    public InvalidNumberOfDayException(int day) {
        super("invalid data" + day + "is not exist");
    }
}
