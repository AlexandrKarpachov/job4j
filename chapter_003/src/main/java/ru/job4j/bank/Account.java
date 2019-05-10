package ru.job4j.bank;

/**
 * The {@code Account} class provides account operations.
 *
 * @author Alexandr Karpachov
 * @version 1.0
 * @since 09.05.2019
 */
public class Account {
    private double value;
    private String requisites;

    public Account(double values, String requisites) {
        this.value = values;
        this.requisites = requisites;
    }

    public double getValue() {
        return this.value;
    }


    public String getRequisites() {
        return this.requisites;
    }

    /**
     * method transfers money from this account to another if money enough
     * for transfer
     * @param destination account
     * @param amount of money for transfer
     * @return true if operation was success and false in otherwise
     */
    boolean transfer(Account destination, double amount) {
        boolean success = false;
        if (amount > 0 && destination != null && this.withdraw(amount)) {
            destination.refill(amount);
            success = true;
        }
        return success;
    }

    /**
     * Method adds money to this account
     * @param amount money for adding
     * @return true if operation was success and false if input value
     * was negative or zero.
     */
    public boolean refill(double amount) {
        boolean success = false;
        if (amount > 0) {
            this.value += amount;
            success = true;
        }
        return success;
    }

    /**
     * Method withdraws money from account.
     * @param amount of money for withdraw
     * @return true if operation was success and false if input value
     * was bigger then current balance or was negative, or zero.
     */
    public boolean withdraw(double amount) {
        boolean success = false;
        if (value >= amount && amount > 0) {
            this.value -= amount;
            success = true;
        }
        return success;
    }

    @Override
    public String toString() {
        return String.format("Account{value= %f, requisites='%s'}",
                this.value, this.requisites);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return this.requisites.equals(account.requisites);
    }

    @Override
    public int hashCode() {
        return this.requisites.hashCode();
    }
}
