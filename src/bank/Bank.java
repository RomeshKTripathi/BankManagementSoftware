package bank;

import account.Account;

import java.io.Serializable;
import java.util.Date;

public class Bank implements Serializable {
    public boolean transferMoney(Account benefactor, int benefactorPin, int beneficiaryAccountNumber, double amount){
        Transaction transaction ;
        BankData bankData = BankData.getInstance();
        Account benefactorAccount = bankData.getAccount(benefactor.getAccountNumber());
        Account beneficiaryAccount = bankData.getAccount(beneficiaryAccountNumber);

        if(benefactorAccount == null || beneficiaryAccount == null) return false;

        if(benefactorAccount.getSecurityPin() != benefactorPin || benefactorAccount.getAccountBalance() < amount) return false;

        // transferring money from benefactor's account ot beneficiary account
        {
            benefactorAccount.setAccountBalance(benefactor.getAccountBalance() - amount);
            beneficiaryAccount.setAccountBalance(beneficiaryAccount.getAccountBalance() + amount);
            transaction = new Transaction(benefactor.getAccountNumber(), beneficiaryAccount.getAccountNumber(), TransactionType.BANK_TRANSFER, amount, new Date());
            // Updating Transaction History of Bank
            bankData.addTransaction(transaction);
            bankData.saveTransaction();
        }

        // Update transaction of the benefactor's Account
        Transaction benefactorTransaction = new Transaction(transaction);
        benefactorTransaction.setTransactionType(TransactionType.CREDIT);
        benefactorAccount.addTransaction(benefactorTransaction);

        // Update transaction history of beneficiary's Account
        Transaction beneficiaryTransaction = new Transaction(transaction);
        beneficiaryTransaction.setTransactionType(TransactionType.DEBIT);
        beneficiaryAccount.addTransaction(beneficiaryTransaction);
        //
        return true;
    }

    public void deleteAccount(Account account){
        BankData bankData = BankData.getInstance();
        bankData.getAllAccounts().remove(account);
        System.out.println("Account Deleted");
        bankData.saveAccount();
    }


}
