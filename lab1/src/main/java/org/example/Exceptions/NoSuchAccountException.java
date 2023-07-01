package org.example.Exceptions;

public class NoSuchAccountException extends Exception{
    public NoSuchAccountException(String name) {
        super(name + "is not exist");
    }
}
