package org.example.Entities;

import org.example.Exceptions.InvalidNameException;

import java.math.BigDecimal;

public abstract class CheckNotNull {
    /**
     * Метод, который проверяет аргумент на null или пустоту
     * @param name - строка
     * @return name
     * @throws InvalidNameException исключение вызывается если name невалидный
     */
    protected String checkNotNullString(String name) throws InvalidNameException {
        if (name.isBlank()) throw new InvalidNameException();
        return name;
    }

    /**
     * Метод, который проверяет аргумент на null или пустоту
     * @param val переменная типа BigDecimal
     * @return val
     * @throws InvalidNameException исключение вызывается если name невалидный
     */
    protected BigDecimal checkNotNullBigDecimal(BigDecimal val) throws InvalidNameException {
        if (val == null) throw new InvalidNameException();
        return val;
    }
}
