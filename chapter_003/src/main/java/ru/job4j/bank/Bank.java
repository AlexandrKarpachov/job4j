package ru.job4j.bank;

import java.util.*;

/**
 * The {@code Bank} class provides banking operations.
 *
 * @author Alexandr Karpachov
 * @version 1.0
 * @since 09.05.2019
 */
public enum  Bank {
    INSTANCE;
    private Map<User, ArrayList<Account>> users = new HashMap<>();

    /**
     * Method adds not null User in Map {@param users} adds to him a new list of
     * accounts, and adds User passport to another Map {@param passports}
     */
    public void addUser(User user) {
            this.users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Method deleting User from storage {@param users}
     * @param user for deleting.
     */
    public void deleteUser(User user) {
        this.users.remove(user);
    }

    /**
     * Method adds Account to User accounts list
     * @param passport of User whom will be added account
     * @param account that will be added
     */
    public void addAccountToUser(String passport, Account account) {
        User user = getUserByPassport(passport);
        if (user != null) {
            this.users.get(user).add(account);
        }
    }

    /**
     * Method delete Account from users list with accounts
     * @param passport of User whose account will be deleted
     * @param account for deleting
     */
    public void deleteAccountFromUser(String passport, Account account) {
        User user = getUserByPassport(passport);
        if (user != null) {
            this.users.get(user).remove(account);
        }
    }

    /**
     * Method searching User by passport
     * @param passport searching key
     * @return User with input passport or null if such user not
     * exists in {@param this.users}
     */
    public User getUserByPassport(String passport) {
        return this.users.keySet().stream()
                .filter(user -> user.getPassport().equals(passport))
                .findFirst().orElse(null);
    }

    /**
     * This method returns List with all users
     */
    public List<User> getAllUsers() {
        return new ArrayList<>(this.users.keySet());
    }

    /**
     * @return list with all user accounts, if User with such passport
     * does't exists return empty list.
     */
    public List<Account> getUserAccounts(String passport) {
        User user = getUserByPassport(passport);
        List<Account> accounts = new ArrayList<>();
        if (user != null) {
            accounts.addAll(this.users.get(user));
        }
        return accounts;
    }

    /**
     * transfer money from one account to another account if
     * the following conditions are met:
     * - account for withdraw {@param account1} belong to {@param user1}
     * - account for refill {@param user2} belong to {@param account2}
     * - first account store enough money for withdraw
     * @param amount of money for withdraw
     * @return true if operation was success and false in otherwise
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                  String destPassport, String dstRequisite, double amount) {
        Account scrAccount = this.getAccountByRequisite(srcPassport, srcRequisite);
        Account destAccount = this.getAccountByRequisite(destPassport, dstRequisite);
        return scrAccount != null && destAccount != null
                && scrAccount.transfer(destAccount, amount);
    }

    /**
     * Method search Accounts by User passport and requisites
     */
    private Account getAccountByRequisite(String passport, String requisite) {
        User user = getUserByPassport(passport);
        List<Account> userAccounts = this.getUserAccounts(user.getPassport());
        return userAccounts.stream()
                .filter(e -> e.getRequisites().equals(requisite))
                .findFirst().orElse(null);
    }


    public void clear() {
        this.users.clear();
    }

    @Override
    public String toString() {
        return String.format("Bank{users=%s}", users);
    }
}
