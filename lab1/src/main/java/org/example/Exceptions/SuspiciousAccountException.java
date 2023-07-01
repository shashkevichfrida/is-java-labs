package org.example.Exceptions;

public class SuspiciousAccountException extends Exception{
    public SuspiciousAccountException(String name) {
        super("Suspicious Account" + name);
    }
}
