package bank;

import account.*;
import java.util.Scanner;

public class NormalUser extends Bank{
    Account account;
    Branch branch;
    BankData bankData;
    public NormalUser(Account account, Branch branch){
        this.account = account;
        this.branch = branch;
        bankData = BankData.getInstance();
    }

    public void startOperations(){
        String menu = "----------------MENU----------------\n"+
                "| Hello "+String.format("%-27s", this.account.getAccountHolderName())+"|\n"+
                "| account Enquiry             1    |\n"+
                "| Change Pin                  2    |\n"+
                "| Print Transaction History   3    |\n"+
                "| Transfer Money              4    |\n"+
                "| Exit                        0    |\n";

        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println(menu);
            int option = scanner.nextInt();
            if(option == 0){
                System.out.println("Exiting Account..");
                return;
            }
            switch (option){
                case 1:
                    accountEnquiry();
                    break;
                case 2:
                    changeSecurityPin();
                    break;
                case 3:
                    printTransactionHistory();
                    break;
                case 4:
                    transferMoney();
                    break;
                default:
                    System.out.println("Invalid Option ...\nChoose options from menu only\n");
            }
        }
    }

    public void accountEnquiry(){
        String data = "Name              : " + account.getAccountHolderName() + "\n" +
                "Account Number    : " + account.getAccountNumber() + "\n" +
                "Security Pin      : " + account.getSecurityPin() + "\n"+
                "Balance           : " + account.getAccountBalance() + "\n\n\n";
        System.out.println(data);
    }
    public void changeSecurityPin(){
        Credentials credentials = account.getCredentials();
        System.out.println("Enter New Security pin");
        Scanner scanner = new Scanner(System.in);
        int securityPin = scanner.nextInt();
        credentials.setSecurityPin(securityPin);
        account.setCredentials(credentials);
        credentials.showCredentials();
        System.out.println("Security Pin Changed Successfully !" + account.getSecurityPin());

    }
    public void printTransactionHistory(){

        String label = "| FROM               | TO                 | AMOUNT   | TYPE          | DATE                         | ";
        System.out.println(label);
        for(Transaction transaction : account.getTransactions()){
            String row = String.format("| %-19s",transaction.getBenefactorAccountNumber()) +
                    String.format("| %-19s", transaction.getBeneficiaryAccountNumber())+
                    String.format("| %-9s", transaction.getAmount())+
                    String.format("| %-14s", transaction.getTransactionType())+
                    String.format("| %-29s|",transaction.getDate().toString());
            System.out.println(row);

        }
    }
    public void transferMoney(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Beneficiary Account Number");
        int beneficiaryAccountNumber = scanner.nextInt();
        System.out.println("Amount to transfer : ");
        double amount = scanner.nextDouble();
        if(branch.transferMoney(account, account.getSecurityPin(), beneficiaryAccountNumber, amount)){
            System.out.println("Money Transferred Successfully !");
        }else{
            System.out.println("Transaction failed...");
        }
    }


}
