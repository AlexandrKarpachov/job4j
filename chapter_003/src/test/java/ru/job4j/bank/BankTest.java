package ru.job4j.bank;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BankTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @Test
    public void whenAdd2UsersThenGetAll() {
        Bank bank = new Bank();
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
        Bank bank = new Bank();
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
        Bank bank = new Bank();
        User vasya = new User("Vasiliy", "id_127");
        bank.addUser(vasya);
        bank.addAccountToUser(vasya.getPassport(), new Account(1, "test1"));
        bank.addAccountToUser(vasya.getPassport(), new Account(2, "test2"));
        List<Account> actual = bank.getAccounts(vasya);
        List<Account> expected = Arrays.asList(
                new Account(3, "test1"),
                new Account(5, "test2")
        );
        assertThat(expected, is(actual));
    }

    @Test
    public void whenAdd2UsersWithIdenticalPassport() {
        Bank bank = new Bank();
        bank.addUser(new User("Ivan", "id_128"));
        bank.addUser(new User("Vasil", "id_128"));
        List<User> actual = bank.getAllUsers();
        List<User> expected = Collections.singletonList(new User("Ivan", "id_128"));
        assertThat(actual, is(expected));
    }

    @Test
    public void whenAddsAccountToNonExistsUserThenException() {
        Bank bank = new Bank();
        expectedException.expect(NullPointerException.class);
        bank.addAccountToUser("Non exists user passport", new Account(1, "test1"));
    }

    @Test
    public void whenTransferMoney() {
        Bank bank = new Bank();
        User vasya = new User("Ivan", "id_132");
        User petr = new User("Petr", "id_131");
        bank.addUser(vasya);
        bank.addUser(petr);
        Account accaunt1 = new Account(10, "test reqs");
        Account accaunt2 = new Account(10, "test reqs");
        bank.addAccountToUser(vasya.getPassport(), accaunt1);
        bank.addAccountToUser(petr.getPassport(), accaunt2);
        boolean result =
                bank.transferMoney(vasya, accaunt1, petr, accaunt2, 5);
        assertThat(result, is(true));
        assertThat(accaunt1.getValue(), is(5d));
        assertThat(accaunt2.getValue(), is(15d));
    }

    @Test
    public void whenTransferNotEnoughMoney() {
        Bank bank = new Bank();
        User vasya = new User("Ivan", "id_132");
        User petr = new User("Petr", "id_131");
        bank.addUser(vasya);
        bank.addUser(petr);
        Account accaunt1 = new Account(10, "test reqs");
        Account accaunt2 = new Account(10, "test reqs");
        bank.addAccountToUser(vasya.getPassport(), accaunt1);
        bank.addAccountToUser(petr.getPassport(), accaunt2);
        boolean result =
                bank.transferMoney(vasya, accaunt1, petr, accaunt2, 12);
        assertThat(result, is(false));
        assertThat(accaunt1.getValue(), is(10d));
        assertThat(accaunt2.getValue(), is(10d));
    }

}
