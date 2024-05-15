package bank;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {
    TransactionType transactionType;
    int benefactorAccountNumber;
    Integer beneficiaryAccountNumber;
    double amount;

    Date date;


    public Transaction(Transaction transaction){
        this.benefactorAccountNumber = transaction.benefactorAccountNumber;
        this.beneficiaryAccountNumber = transaction.beneficiaryAccountNumber;
        this.amount = transaction.amount;
        this.transactionType = transaction.transactionType;
        this.date = transaction.date;
    }
    public Transaction(int benefactorAccountNumber, int beneficiaryAccountNumber, TransactionType transactionType, double amount, Date date){
        this.benefactorAccountNumber = benefactorAccountNumber;
        this.beneficiaryAccountNumber = beneficiaryAccountNumber;
        this.amount = amount;
        this.transactionType = transactionType;
        this.date = date;
    }

    public Transaction(int benefactorAccountNumber, Integer o, TransactionType transactionType, double amount, Date date) {
        this.benefactorAccountNumber = benefactorAccountNumber;
        this.beneficiaryAccountNumber = null;
        this.amount = amount;
        this.transactionType = transactionType;
        this.date = date;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Date getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public int getBenefactorAccountNumber() {
        return benefactorAccountNumber;
    }

    public int getBeneficiaryAccountNumber() {
        if(beneficiaryAccountNumber == null) return 0;
        return beneficiaryAccountNumber;

    }

    public TransactionType getTransactionType() {
        return transactionType;
    }
}
