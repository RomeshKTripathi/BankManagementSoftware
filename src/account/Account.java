package account;

import bank.Transaction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable {
    List<Transaction> transactions = new ArrayList<>();
    String accountHolderName;
    int accountNumber;
    double accountBalance = 1000;
    int securityPin; // 4 Digit Security pin
    AccountType accountType;
    Credentials credentials;

    public Account(String accountHolderName, int accountNumber, int securityPin, AccountType accountType){
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.securityPin = securityPin;
        this.accountType = accountType;
        this.credentials = new Credentials(accountNumber, securityPin);
    }

    public void depositMoney(double amount){
        this.accountBalance += amount;
    }
    public void withdrawMoney(double amount){
        if(this.accountBalance > amount){
            this.accountBalance -= amount;
        }
    }
    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }
    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public void setSecurityPin(int securityPin) {
        this.securityPin = securityPin;
        credentials.setSecurityPin(this.securityPin);
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
        this.setSecurityPin(credentials.securityPin);
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public int getSecurityPin() {
        return securityPin;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
