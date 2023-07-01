package org.example.Entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.example.BankAccounts.BankAccount;
import org.example.Exceptions.InvalidNameException;
import org.example.Exceptions.SuspiciousAccountException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Класс, описывающий объект "Клиент".
 */
@Getter
@Setter(AccessLevel.PROTECTED)
public class Client extends CheckNotNull {
    private List<BankAccount> accounts = new ArrayList<>();
    private List<String> bankNotifications = new ArrayList<>();
    private List<String> historyOfTransactions = new ArrayList<>();
    private boolean notification;
    private UUID id;
    private String name;
    private String passport;
    private String address;
    private Bank bank;
    public Client(UUID id, String name) throws InvalidNameException {
        this.name = checkNotNullString(name);
        this.id = id;

    }

    public List<BankAccount> readAccounts = Collections.unmodifiableList(accounts);
    public List<String> readBankNotifications = Collections.unmodifiableList(bankNotifications);
    public List<String> readHistoryOfTransactions = Collections.unmodifiableList(historyOfTransactions);

    /**
     * Метод, в котором у клиента добавляются его счета.
     * @param account - объект "счет".
     */
    public void addAccounts(BankAccount account) {
        accounts.add(account);
    }

    /**
     * Метод, который проверяет на сомнительный счет клиента.
     * @throws SuspiciousAccountException - исключение, которое вызывается при попытки совершения
     * транзакции с/на сомнительного счета.
     */
    public void checkingForASuspiciousAccount() throws SuspiciousAccountException {
        if (address.isBlank() || passport.isBlank()) {
            throw new SuspiciousAccountException(name);
        }
    }

    /**
     * Метод, который добавляет совершенную транзакций в историю транзакций клиента
     * при условии что клиент подписан на уведомления.
     * @param transaction - строка, которая описывает текущий баланс.
     */

    public void addHistoryOfTransactions(String transaction) {
        if (notification) {
            historyOfTransactions.add(transaction);
        }

    }

    /**
     * Метод, который добавляет новые условия банка в историю банка клиента
     * при условии что клиент подписан на уведомления.
     * @param bankNotification - строка, котоорая описывает новые изменеия условий
     * банка.
     */
    public void addBankNotification(String bankNotification) {
        if (notification) {
            bankNotifications.add(bankNotification);
        }
    }
}
