package org.example.Entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.example.BankAccounts.BankAccount;
import org.example.Exceptions.InvalidNameException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Класс, описывающий объект "Банк".
 */
@Getter
@Setter(AccessLevel.PRIVATE)
public class Bank extends CheckNotNull{
    private List<BankAccount> acc = new ArrayList<>();
    private List<Client> owners = new ArrayList<>();
    private UUID id;
    private String name;
    private BigDecimal bankCommission;
    private BigDecimal bankPercent;

    public Bank(UUID id, String name, BigDecimal bankCommission, BigDecimal bankPercent) throws InvalidNameException {
        this.id = id;
        this.name = checkNotNullString(name);
        this.bankCommission = checkNotNullBigDecimal(bankCommission);
        this.bankPercent = checkNotNullBigDecimal(bankPercent);
    }

    public List<BankAccount> accounts = Collections.unmodifiableList(acc);
    public List<Client> clients = Collections.unmodifiableList(owners);

    /**
     * Метод, который добавляет объект "Счет" клиента в коллекцию.
     * @param account - объект "счет".
     */
    public void addAccountToBank(BankAccount account) {
        acc.add(account);
    }

    /**
     * Метод, который добавляет объект "Клиент" в коллекцию.
     * @param client - объект "клиент".
     */
    public void addClient(Client client) {
        owners.add(client);
    }

    /**
     * Метод, который изменяет условия банка.
     * @param newCommission - новая комиссия.
     * @param newPercent - новые проценты.
     */
    public void changingConditionsBank(BigDecimal newCommission, BigDecimal newPercent) {
        bankCommission = newCommission;
        bankPercent = newPercent;
        notifyOwners();
    }

    /**
     * Метод, в котором банк уведомляет всех своих клиентов об изменение условий банка.
     */

    public void notifyOwners() {
        for (Client client : owners)
        {
            client.addBankNotification("Changing the bank's terms and conditions. Commission: " + bankCommission + ", Percent: " + bankPercent);
        }

    }

}
