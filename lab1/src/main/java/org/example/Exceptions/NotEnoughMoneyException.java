package org.example.Exceptions;

import java.math.BigDecimal;

public class NotEnoughMoneyException extends Exception{
    public NotEnoughMoneyException(BigDecimal balance) {
        super("balance:" + balance);
    }
}
