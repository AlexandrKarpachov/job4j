package ru.job4j.bank;

import java.util.*;

/**
 * The {@code Bank} class provides banking operations.
 *
 * @author Alexandr Karpachov
 * @version 1.0
 * @since 09.05.2019
 */
public class   Bank {
    private Map<User, ArrayList<Account>> users = new HashMap<>();
    private Map<String, User> passports = new HashMap<>();

    /**
     * Method adds not null User in Map {@param users} adds to him a new list of
     * accounts, and adds User passport to another Map {@param passports}
     */
    public void addUser(User user) {
        if (!passports.containsKey(user.getPassport())) {
            this.users.put(user, new ArrayList<>());
            this.passports.put(user.getPassport(), user);
        }

    }

    /**
     * Method deleting User from storage {@param users}
     * @param user for deleting.
     */
    public void deleteUser(User user) {
        this.users.remove(user);
        this.passports.remove(user.getPassport());
    }

    /**
     * Method adds Account to User accounts list
     * @param passport of User whom will be added account
     * @param account that will be added
     */
    public void addAccountToUser(String passport, Account account) {
        this.users.get(this.passports.get(passport)).add(account);
    }

    /**
     * Method delete Account from users list with accounts
     * @param user whose account will be deleted
     * @param account for deleting
     */
    public void deleteAccount(User user, Account account) {
        this.users.get(user).remove(account);
    }

    /**
     * This method returns List with all users
     */
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        for (Map.Entry<User, ArrayList<Account>> entry : users.entrySet()) {
            result.add(entry.getKey());
        }
        return result;
    }

    /**
     * @return list with all user accounts
     */
    public List<Account> getAccounts(User user) {
        return this.users.get(user);
    }

    /**
     * transfer money from one account to another account if
     * the following conditions are met:
     * account for withdraw {@param account1} belong to {@param user1},
     * account for refill {@param user2} belong to {@param account2} and
     * first account store enough money for withdraw
     * @param amount of money for withdraw
     * @return true if operation was success and false in otherwise
     */
    public boolean transferMoney(User user1, Account account1,
                            User user2, Account account2, double amount) {
        return account1.transfer(account2, amount);
    }

    /**
     * Method checks that user already has account with such requisite
     */
    private boolean userHasAccount(User user, Account account) {
        boolean result = false;
        for (Account acc : this.getAccounts(user)) {
            if (acc.equals(account)) {
                result = true;
            }
        }
        return result;
    }


    @Override
    public String toString() {
        return String.format("Bank{users=%s, passports=%s}", users, passports);
    }
}
