package Banks.Tests;

import org.example.BankAccounts.CreditAccount;
import org.example.BankAccounts.DebitAccount;
import org.example.BankAccounts.DepositAccount;
import org.example.DataTime.DataTime;
import org.example.Entities.Bank;
import org.example.Entities.Client;
import org.example.Exceptions.*;
import org.example.OperationsAndTransactions.MoneyTransfer;
import org.example.OperationsAndTransactions.Transaction;
import org.example.Services.Implements.Service;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.Assert.assertThrows;

public class BanksTest {

    private Service _service = new Service();
    private Bank _sber;
    private Client _client1;
    private Client _client2;
    private DebitAccount _acc1;
    private DepositAccount _acc2;
    private CreditAccount _acc3;

    public BanksTest() throws InvalidNameException {
        _sber = _service.centralBank.addBank("sber", BigDecimal.valueOf(4), BigDecimal.valueOf(3));
        _client1 = _service.clientService.addClient("hh", "address", "passport number", _sber, true);
        _client2 = _service.clientService.addClient("name", "address", "passport number", _sber, false);
        _acc1 = _service.addBankDebitAccount(_sber, BigDecimal.valueOf(3000), _client1);
        _acc2 = _service.addBankDepositAccount(_sber, BigDecimal.valueOf(3000), _client1);
        _acc3 = _service.addBankCreditAccount(_sber, BigDecimal.valueOf(3000), _client1);
    }

    @Test
    public void CreationOfBanksAndAccounts()
    {
        Assert.assertTrue(_service.centralBank.banksRead.contains(_sber));
        Assert.assertTrue(_client1.readAccounts.contains(_acc1));
        Assert.assertTrue(_client1.readAccounts.contains(_acc2));
        Assert.assertTrue(_client1.readAccounts.contains(_acc3));
    }

    @Test
    public void LastDayOfTheMonthBalanceChange()
    {
        DataTime dataTime = new DataTime(30, 6);
        try {
            _service.centralBank.checkTheDateOfTheMonth(dataTime);
        } catch (InvalidNumberOfDayException e) {
            throw new RuntimeException(e);
        }
        Assert.assertFalse(_acc1.getBalance().equals(BigDecimal.valueOf(3000)));
        Assert.assertFalse(_acc2.getBalance().equals(BigDecimal.valueOf(3000)));
        Assert.assertTrue(_acc3.getBalance().equals(BigDecimal.valueOf(3000)));
    }

    @Test
    public void Transactions() throws AMonetaryTransactionIsNotPossibleException, NotEnoughMoneyException, SuspiciousAccountException {
        _service.centralBank.accountReplenishment(UUID.randomUUID(), _acc1, BigDecimal.valueOf(100));
        _service.centralBank.debitingMoneyFromAnAccount(UUID.randomUUID(), _acc1, BigDecimal.valueOf(200));
        _service.centralBank.moneyTransfer(_acc1, BigDecimal.valueOf(400), _acc2);

        Assert.assertEquals(BigDecimal.valueOf(3400), _acc2.getBalance());
        Assert.assertEquals(BigDecimal.valueOf(2500), _acc1.getBalance());
    }

    @Test
    public void CancellationOfTheTransaction() throws NoSuchTransactionException, AMonetaryTransactionIsNotPossibleException, NotEnoughMoneyException, SuspiciousAccountException {
        Transaction transaction1 = _service.centralBank.accountReplenishment(UUID.randomUUID(), _acc1, BigDecimal.valueOf(500));
        Transaction transaction2 = _service.centralBank.accountReplenishment(UUID.randomUUID(), _acc2, BigDecimal.valueOf(10008));
        MoneyTransfer transaction3 = _service.centralBank.moneyTransfer(_acc1, BigDecimal.valueOf(400), _acc3);

        _service.centralBank.cancellationOfTheTransaction(transaction1);
        _service.centralBank.cancellationOfTheTransaction(transaction2);
        _service.centralBank.cancellationOfTheTransaction(transaction3);

        Assert.assertEquals(BigDecimal.valueOf(3000), _acc1.getBalance());
        Assert.assertEquals(BigDecimal.valueOf(3000), _acc2.getBalance());
        Assert.assertEquals(BigDecimal.valueOf(3000), _acc3.getBalance());
    }

    @Test
    public void Notification() throws SuspiciousAccountException {
        _service.centralBank.accountReplenishment(UUID.randomUUID(), _acc1, BigDecimal.valueOf(500));
        _service.centralBank.changingConditionsBank(_sber, BigDecimal.valueOf(1), BigDecimal.valueOf(9));

        Assert.assertEquals(1, _client1.readBankNotifications.size());
        Assert.assertEquals(1, _client1.readHistoryOfTransactions.size());
        Assert.assertEquals(0, _client2.readBankNotifications.size());
        Assert.assertEquals(0, _client2.readHistoryOfTransactions.size());
    }

    @Test
    public void AMonetaryTransactionIsNotPossibleException()
    {
        Throwable thrown = assertThrows(AMonetaryTransactionIsNotPossibleException.class, () -> {
            _service.centralBank.debitingMoneyFromAnAccount(UUID.randomUUID(), _acc2, BigDecimal.valueOf(200));
        });
        Assert.assertNotNull(thrown.getMessage());
    }

    @Test
    public void NotEnoughMoneyException() {
        Throwable thrown = assertThrows(NotEnoughMoneyException.class, () -> {
            _service.centralBank.debitingMoneyFromAnAccount(UUID.randomUUID(), _acc1, BigDecimal.valueOf(10000));
        });
        Assert.assertNotNull(thrown.getMessage());
    }



    @Test
    public void SuspiciousAccountException()
    {
        Throwable thrown = assertThrows(SuspiciousAccountException.class, () -> {
            Client stremniyClient = _service.clientService.addClient("name", "", "", _sber, true);
            DebitAccount acc = _service.addBankDebitAccount(_sber, BigDecimal.valueOf(300), stremniyClient);
            _service.centralBank.debitingMoneyFromAnAccount(UUID.randomUUID(), acc, BigDecimal.valueOf(100));
        });
        Assert.assertNotNull(thrown.getMessage());
    }
}
