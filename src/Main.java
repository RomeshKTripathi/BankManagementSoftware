import account.Account;
import account.Credentials;
import bank.BankData;
import bank.Branch;
import bank.NormalUser;
import bank.Transaction;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BankData bankData = BankData.getInstance();
        Branch branch = new Branch();
        Scanner scanner = new Scanner(System.in);
        String menu = "---------------MENU---------------\n" +
                "|  USER LOGIN                  1 |\n" +
                "|  BRANCH LOGIN                2 |\n" +
                "|  ALL ACCOUNTS                3 |\n" +
                "|  TRANSACTIONS                4 |\n" +
                "|  EXIT                        0 |\n" +
                "----------------------------------";
        while(true){
            System.out.println(menu);
            int option = scanner.nextInt();

            switch (option){
                case 0:
                    bankData.saveData();
                    System.out.println("Thank you !");
                    return;

                case 1:
                    Credentials credentials = new Credentials();
                    credentials.getCredentials();
                    Account acc = bankData.getMyAccount(credentials);
                    if(acc != null){
                        NormalUser normalUser = new NormalUser(acc, branch);
                        normalUser.startOperations();
                    }else{
                        System.out.println("Account Not Found with provided credentials");
                    }
                    break;
                case 2:
                    branch.startOperations();
                    break;
                case 3:
                    for(Account account : bankData.getAllAccounts()){
                        System.out.println(account.getAccountHolderName() + "   " + account.getAccountNumber());
                    }
                    break;
                case 4:
                    String label = "| FROM               | TO                 | AMOUNT   | TYPE     | DATE    " +
                            "                     | ";
                    System.out.println(label);
                    for(Transaction transaction : bankData.getTransactions()){
                        String row = String.format("| %-19s",transaction.getBenefactorAccountNumber()) +
                                String.format("| %-19s", transaction.getBeneficiaryAccountNumber())+
                                String.format("| %-9s", transaction.getAmount())+
                                String.format("| %-9s", transaction.getTransactionType())+
                                String.format("| %-29s|",transaction.getDate().toString());
                        System.out.println(row);
                    }
                    break;
                default:
                    System.out.println("Please Choose valid option !");
            }
        }


    }
}