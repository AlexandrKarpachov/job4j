package ru.job4j.bank;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BankTest {

    @Before
    public void start() {
        Bank instance = Bank.INSTANCE;
        instance.clear();
    }

    @Test
    public void whenAdd2UsersThenGetAll() {
        Bank bank = Bank.INSTANCE;
        bank.addUser(new User("Ivan", "id_123"));
        bank.addUser(new User("Vasil", "id_124"));
        List<User> expected = Arrays.asList(
                new User("Ivan", "id_123"),
                new User("Vasil", "id_124")
        );
        List<User> actual = bank.getAllUsers();

        assertThat(expected, is(actual));
    }

    @Test
    public void whenAdd2ElementThenDelete() {
        Bank bank = Bank.INSTANCE;
        User vasya = new User("Vasiliy", "id_125");
        bank.addUser(vasya);
        bank.addUser(new User("Ivan", "id_126"));
        bank.deleteUser(vasya);
        bank.deleteUser(new User("Ivan", "id_126"));
        List<User> expected = new ArrayList<>();
        List<User> actual = bank.getAllUsers();
        assertThat(expected, is(actual));
    }

    @Test
    public void addAccountsToUserThenGetThem() {
        Bank bank = Bank.INSTANCE;
        User vasya = new User("Vasiliy", "id_127");
        bank.addUser(vasya);
        bank.addAccountToUser(vasya.getPassport(), new Account(1, "test1"));
        bank.addAccountToUser(vasya.getPassport(), new Account(2, "test2"));
        List<Account> actual = bank.getUserAccounts(vasya.getPassport());
        List<Account> expected = Arrays.asList(
                new Account(3, "test1"),
                new Account(5, "test2")
        );
        assertThat(expected, is(actual));
    }

    @Test
    public void whenAdd2UsersWithIdenticalPassport() {
        Bank bank = Bank.INSTANCE;
        bank.addUser(new User("Ivan", "id_128"));
        bank.addUser(new User("Ivan", "id_128"));
        List<User> actual = bank.getAllUsers();
        List<User> expected = Collections.singletonList(new User("Ivan", "id_128"));
        assertThat(actual, is(expected));
    }

    @Test
    public void whenDeleteAccountFromUser() {
        Bank bank = Bank.INSTANCE;
        User vanya = new User("Ivan", "id_128");
        bank.addUser(vanya);
        Account first = new Account(3, "test1");
        Account second = new Account(5, "test2");
        bank.addAccountToUser(vanya.getPassport(), first);
        bank.addAccountToUser(vanya.getPassport(), second);
        bank.deleteAccountFromUser(vanya.getPassport(), second);
        List<Account> expected = Collections.singletonList(first);
        assertThat(expected, is(bank.getUserAccounts(vanya.getPassport())));

    }

    @Test
    public void whenTransferMoney() {
        Bank bank = Bank.INSTANCE;
        User vasya = new User("Ivan", "id_132");
        User petr = new User("Petr", "id_131");
        bank.addUser(vasya);
        bank.addUser(petr);
        Account account1 = new Account(10, "test reqs");
        Account account2 = new Account(10, "test reqs");
        bank.addAccountToUser(vasya.getPassport(), account1);
        bank.addAccountToUser(petr.getPassport(), account2);
        boolean result = bank.transferMoney(
                vasya.getPassport(), account1.getRequisites(),
                petr.getPassport(), account2.getRequisites(), 5
        );
        assertThat(result, is(true));
        assertThat(account1.getValue(), is(5d));
        assertThat(account2.getValue(), is(15d));
    }

    @Test
    public void whenTransferNotEnoughMoney() {
        Bank bank = Bank.INSTANCE;
        User vasya = new User("Ivan", "id_132");
        User petr = new User("Petr", "id_131");
        bank.addUser(vasya);
        bank.addUser(petr);
        Account account1 = new Account(10, "test reqs");
        Account account2 = new Account(10, "test reqs");
        bank.addAccountToUser(vasya.getPassport(), account1);
        bank.addAccountToUser(petr.getPassport(), account2);
        boolean result = bank.transferMoney(
                vasya.getPassport(), account1.getRequisites(),
                petr.getPassport(), account2.getRequisites(), 15
        );
        assertThat(result, is(false));
        assertThat(account1.getValue(), is(10d));
        assertThat(account2.getValue(), is(10d));
    }

}
