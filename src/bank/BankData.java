package bank;

import account.Account;
import account.Credentials;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Singleton class
public class BankData {
    static BankData instance = null;
    public ArrayList<Transaction> transactions = null;
    public ArrayList<Account> accounts = null;
    String accountsFile = "accounts.txt";
    String transactionsFile = "transactions.txt";


    private BankData(){
        // Load data from files
        try{
            FileInputStream file = new FileInputStream(accountsFile);
            ObjectInputStream in = new ObjectInputStream(file);

            accounts = (ArrayList<Account>) in.readObject();

            file.close();
            in.close();

        }catch(IOException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }finally {
            if(accounts == null)
                accounts = new ArrayList<>();
        }

        try{
            FileInputStream file = new FileInputStream(transactionsFile);
            ObjectInputStream in = new ObjectInputStream(file);

            transactions = (ArrayList<Transaction>) in.readObject();

            file.close();
            in.close();
        }catch(IOException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }finally {
            if(transactions == null)
                transactions = new ArrayList<>();
        }
    }

    public static BankData getInstance(){
        if(instance == null){
            instance = new BankData();
        }
        return instance;
    }

    public Account getAccount(int accountNumber){
        for(Account account : accounts)
        {
            if (account.getAccountNumber() == accountNumber) return account;
        }
        return null;
    }

    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }
    public void addAccount(Account account){
        accounts.add(account);
    }

    public int getAccountNumber(){
        if(accounts.isEmpty()) return 1000;
        return accounts.getLast().getAccountNumber() + 1;
    }
    public void saveData(){
        saveAccount();
        saveTransaction();
    }
    public void saveAccount(){
        try{
            FileOutputStream file = new FileOutputStream(accountsFile, false);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(accounts);

            file.close();
            out.close();
            System.out.println("Accounts Data Saved.");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    };
    public void saveTransaction(){
        try{
            FileOutputStream file = new FileOutputStream(transactionsFile, false);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(transactions);

            file.close();
            out.close();
            System.out.println("Transactions Data Saved.");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    };

    public Account getMyAccount(Credentials credentials) {
        Account account = getAccount(credentials.getAccountNumber());
        if(account == null) return null;
        if(account.getSecurityPin() == credentials.getSecurityPin()) return account;
        return null;
    }

    public ArrayList<Account> getAllAccounts() {
        return accounts;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
    // methods which modify all data of bank
}
