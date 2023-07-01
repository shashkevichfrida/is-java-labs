package org.example;

import org.example.BankAccounts.BankAccount;
import org.example.BankAccounts.CreditAccount;
import org.example.BankAccounts.DebitAccount;
import org.example.BankAccounts.DepositAccount;
import org.example.Entities.Bank;
import org.example.Entities.Client;
import org.example.Exceptions.*;
import org.example.Services.Implements.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    private static Service _service = new Service();
    public static void main(String[] args) throws NoSuchBankException, SuspiciousAccountException, NotEnoughMoneyException, AMonetaryTransactionIsNotPossibleException, NoSuchAccountException, InvalidNameException {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Select an action");
            System.out.println("1) Create bank");
            System.out.println("2) Create client and account");
            System.out.println("3) log in to your account");
            System.out.println("4) exit");
            String action = in.nextLine();
            switch (action) {
                case "1":
                    System.out.println("Enter the name of the bank");
                    String bankName = in.nextLine();

                    System.out.println("Bank commission:");
                    String commission = in.nextLine();
                    System.out.println("Bank interest:");
                    String percent = in.nextLine();
                    Bank bank1 = _service.centralBank.addBank(bankName, BigDecimal.valueOf(Long.parseLong(commission)), BigDecimal.valueOf(Long.parseLong(percent)));
                    System.out.flush();
                    continue;

                case "2":
                    System.out.println("What's your name?:");
                    String nameClient = in.nextLine();
                    System.out.println("Input your address:");
                    String address = in.nextLine();
                    System.out.println("Input your passport number:");
                    String passportNumber =in.nextLine();
                    System.out.println("Select a bank");
                    bankName = in.nextLine();
                    Optional<Bank> bank = _service.centralBank.getBank(bankName);
                    System.out.println("Do you want to receive notifications from the bank?  (yes or no)");
                    String answer = in.nextLine();
                    boolean notification = !(answer != "yes");
                    Client client =
                            _service.clientService.addClient(nameClient, address, passportNumber, bank.get(), notification);
                    System.out.println("Выберите номер действия:");
                    System.out.println("1 Open a debit account");
                    System.out.println("2 open a credit account");
                    System.out.println("3 open a deposit account");
                    String choise = in.nextLine();
                    switch (choise) {
                        case "1":
                            DebitAccount acc1 = _service.addBankDebitAccount(client.getBank(), BigDecimal.valueOf(0), client);
                            System.out.println("Account created");
                            System.out.println("Выберите номер действия:");
                            System.out.println("1 top up your account");
                            System.out.println("2 withdraw money from the account");
                            System.out.println("3 transfer money");
                            System.out.println("4 exit");
                            choise = in.nextLine();
                            switch (choise) {
                                case "1":
                                    System.out.println("Deposit the amount");
                                    String money = in.nextLine();
                                    _service.centralBank.accountReplenishment(UUID.randomUUID(), acc1, BigDecimal.valueOf(Long.parseLong(money)));
                                    System.out.println("On your account now" + acc1.getBalance());
                                    System.out.flush();
                                    continue;
                                case "2":
                                    System.out.println("Enter the withdrawal amount");
                                    money = in.nextLine();
                                    _service.centralBank.debitingMoneyFromAnAccount(UUID.randomUUID(), acc1, BigDecimal.valueOf(Long.parseLong(money)));
                                    System.out.println("On your account now" + acc1.getBalance());
                                    System.out.flush();
                                    continue;
                                case "3":
                                    System.out.println("enter the recipient's details");
                                    System.out.println("Enter the recipient's name");
                                    String name = in.nextLine();
                                    System.out.println("Enter the recipient's passport number");
                                    passportNumber = in.nextLine();
                                    Optional<BankAccount> accToTransfer = _service.centralBank.findAccount(name, passportNumber);
                                    System.out.println("Enter the amount to transfer");
                                    money = in.nextLine();
                                    _service.centralBank.moneyTransfer(acc1, BigDecimal.valueOf(Long.parseLong(money)), accToTransfer.get());
                                    System.out.println("On your account now" + acc1.getBalance());
                                    System.out.flush();
                                    continue;
                                case "4":
                                    System.out.flush();
                                    break;
                            }

                            System.out.flush();
                            continue;
                        case "2":
                            CreditAccount acc2 = _service.addBankCreditAccount(client.getBank(), BigDecimal.valueOf(0), client);
                            System.out.println("Account created");
                            System.out.println("Выберите номер действия:");
                            System.out.println("1 top up your account");
                            System.out.println("2 withdraw money from the account");
                            System.out.println("3 transfer money");
                            System.out.println("4 exit");
                            choise = in.nextLine();
                            switch (choise) {
                                case "1":
                                    System.out.println("Deposit the amount");
                                    String money = in.nextLine();
                                    _service.centralBank.accountReplenishment(UUID.randomUUID(), acc2, BigDecimal.valueOf(Long.parseLong(money)));
                                    System.out.println("On your account now" + acc2.getBalance());
                                    continue;
                                case "2":
                                    System.out.println("Enter the withdrawal amount");
                                    money = in.nextLine();
                                    _service.centralBank.debitingMoneyFromAnAccount(UUID.randomUUID(), acc2, BigDecimal.valueOf(Long.parseLong(money)));
                                    System.out.println("On your account now" + acc2.getBalance());
                                    continue;
                                case "3":
                                    System.out.println("enter the recipient's details");
                                    System.out.println("Enter the recipient's name");
                                    String name = in.nextLine();
                                    System.out.println("Enter the recipient's passport number");
                                    passportNumber = in.nextLine();
                                    Optional<BankAccount> accToTransfer = _service.centralBank.findAccount(name, passportNumber);
                                    System.out.println("Enter the amount to transfer");
                                    money = in.nextLine();
                                    _service.centralBank.moneyTransfer(acc2, BigDecimal.valueOf(Long.parseLong(money)), accToTransfer.get());
                                    System.out.println("On your account now" + acc2.getBalance());
                                    continue;
                                case "4":
                                    break;
                            }

                            System.out.flush();
                            continue;
                        case "3":
                            DepositAccount acc3 = _service.addBankDepositAccount(client.getBank(), BigDecimal.valueOf(0), client);
                            System.out.println("Account created");
                            System.out.println("Выберите номер действия:");
                            System.out.println("1 top up your account");
                            System.out.println("2 exit");
                            choise = in.nextLine();
                            switch (choise) {
                                case "1":
                                    System.out.println("Deposit the amount");
                                    String money = in.nextLine();
                                    _service.centralBank.accountReplenishment(UUID.randomUUID(), acc3, BigDecimal.valueOf(Long.parseLong(money)));
                                    System.out.println("On your account now" + acc3.getBalance());
                                    continue;
                                case "2":
                                    break;
                            }

                            System.out.flush();
                            continue;
                    }

                    System.out.flush();
                    continue;
                case "3":
                    System.out.println("Input your bank");
                    bankName = in.nextLine();
                    bank = _service.centralBank.getBank(bankName);
                    System.out.println("What's your name?:");
                    nameClient = in.nextLine();
                    System.out.println("Your passport number:");
                    passportNumber = in.nextLine();
                    while (true) {
                        System.out.println("Input your type of account");
                        System.out.println("1 debit account");
                        System.out.println("2 deposit account");
                        System.out.println("3 credit account");
                        System.out.println("4 check transaction history");
                        System.out.println("5 check notification history");
                        System.out.println("6 exit");
                        action = in.nextLine();
                        Optional<BankAccount> acc = _service.centralBank.findAccount(nameClient, passportNumber);
                        switch (action) {
                            case "1":
                                if (acc.get().getClass() == DebitAccount.class) {
                                System.out.println("Выберите номер действия:");
                                System.out.println("1 top up your account");
                                System.out.println("2 withdraw money from the account");
                                System.out.println("3 transfer money");
                                System.out.println("4 exit");
                                choise = in.nextLine();
                                switch (choise) {
                                    case "1":
                                        System.out.println("Deposit the amount");
                                        String money = in.nextLine();
                                        _service.centralBank.accountReplenishment(UUID.randomUUID(), acc.get(), BigDecimal.valueOf(Long.parseLong(money)));
                                        System.out.println("On your account now" + acc.get().getBalance());
                                        System.out.flush();
                                        continue;
                                    case "2":
                                        System.out.println("Enter the withdrawal amount");
                                        money = in.nextLine();
                                        _service.centralBank.debitingMoneyFromAnAccount(UUID.randomUUID(), acc.get(), BigDecimal.valueOf(Long.parseLong(money)));
                                        System.out.println("On your account now" + acc.get().getBalance());
                                        System.out.flush();
                                        continue;
                                    case "3":
                                        System.out.println("enter the recipient's details");
                                        System.out.println("Enter the recipient's name");
                                        String name = in.nextLine();
                                        System.out.println("Enter the recipient's passport number");
                                        passportNumber = in.nextLine();
                                        Optional<BankAccount> accToTransfer = _service.centralBank.findAccount(name, passportNumber);
                                        System.out.println("Enter the amount to transfer");
                                        money = in.nextLine();
                                        _service.centralBank.moneyTransfer(acc.get(), BigDecimal.valueOf(Long.parseLong(money)), accToTransfer.get());
                                        System.out.println("On your account now" + acc.get().getBalance());
                                        System.out.flush();
                                        continue;
                                    case "4":
                                        break;
                                }
                            }
                                else {
                                System.out.println("you don't have a debit account(");
                            }

                                System.out.flush();
                            continue;
                            case "2":
                                if (acc.get().getClass() == DepositAccount.class) {
                                System.out.println("Выберите номер действия:");
                                System.out.println("1 top up your account");
                                System.out.println("2 exit");
                                choise = in.nextLine();
                                switch (choise) {
                                    case "1":
                                        System.out.println("Deposit the amount");
                                        String money = in.nextLine();
                                        _service.centralBank.accountReplenishment(UUID.randomUUID(), acc.get(), BigDecimal.valueOf(Long.parseLong(money)));
                                        System.out.println("On your account now" + acc.get().getBalance());
                                        System.out.flush();
                                        continue;
                                    case "2":
                                        break;
                                }
                            }
                                else {
                                System.out.println("you don't have a deposit account(");
                            }

                                System.out.flush();
                            continue;
                            case "3":
                                if (acc.get().getClass() == CreditAccount.class) {
                                System.out.println("Выберите номер действия:");
                                System.out.println("1 top up your account");
                                System.out.println("2 withdraw money from the account");
                                System.out.println("3 transfer money");
                                System.out.println("4 exit");
                                choise = in.nextLine();
                                switch (choise) {
                                    case "1":
                                        System.out.println("Deposit the amount");
                                        String money = in.nextLine();
                                        _service.centralBank.accountReplenishment(UUID.randomUUID(), acc.get(), BigDecimal.valueOf(Long.parseLong(money)));
                                        System.out.println("On your account now" + acc.get().getBalance());
                                        System.out.flush();
                                        continue;
                                    case "2":
                                        System.out.println("Enter the withdrawal amount");
                                        money = in.nextLine();
                                        _service.centralBank.debitingMoneyFromAnAccount(UUID.randomUUID(), acc.get(), BigDecimal.valueOf(Long.parseLong(money)));
                                        System.out.println("On your account now" + acc.get().getBalance());
                                        continue;
                                    case "3":
                                        System.out.println("enter the recipient's details");
                                        System.out.println("Enter the recipient's name");
                                        String name = in.nextLine();
                                        System.out.println("Enter the recipient's passport number");
                                        passportNumber = in.nextLine();
                                        Optional<BankAccount> accToTransfer = _service.centralBank.findAccount(name, passportNumber);
                                        System.out.println("Enter the amount to transfer");
                                        money = in.nextLine();
                                        _service.centralBank.moneyTransfer(acc.get(), BigDecimal.valueOf(Long.parseLong(money)), accToTransfer.get());
                                        System.out.println("On your account now" + acc.get().getBalance());
                                        continue;
                                    case "4":
                                        break;
                                }
                            }
                                else {
                                System.out.println("you don't have a credit account(");
                            }

                                System.out.flush();
                            continue;
                            case "4":
                                System.out.println("Your history of transactions");
                                for (var transaction : acc.get().getOwner().readHistoryOfTransactions) {
                                System.out.println(transaction);
                            }

                            continue;
                            case "5":
                                System.out.println("Your history of notifications");
                                for (var notificat : acc.get().getOwner().readBankNotifications) {
                                System.out.println(notificat);
                            }

                            continue;
                            case "6":
                                System.exit(0);
                                break;
                        }
                    }

                case "4":
                    System.exit(0);
                    break;
            }
        }
    }
}
