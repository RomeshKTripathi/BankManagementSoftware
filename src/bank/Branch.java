package bank;

import account.Account;
import account.AccountType;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Branch extends Bank{
    BankData bankData;

    public Branch(){
        bankData = BankData.getInstance();
    }
    public void startOperations() {
        String menu = """
                ------------Menu------------
                | Withdraw Money         1 |
                | Deposit Money          2 |
                | Open Account           3 |
                | Delete Account         4 |
                | Exit Branch            0 |
                ----------------------------
                """;
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println(menu);
            int option = scanner.nextInt();


            switch (option){
                case 0: return;
                case 1:
                {
                    System.out.println("Enter Account Number");
                    int accountNumber = scanner.nextInt();
                    System.out.println("Enter amount to withdraw");
                    double amount = scanner.nextDouble();
                    withdrawCash(accountNumber, amount);
                }
                break;
                case 2:
                {
                    System.out.println("Enter Account Number");
                    int accountNumber = scanner.nextInt();
                    System.out.println("Enter amount to deposit");
                    double amount = scanner.nextDouble();
                    depositCash(accountNumber, amount);
                }
                break;
                case 3:
                    createAccount();
                    break;
                case 4:
                    System.out.println("Enter Account number to Delete Account ");
                    int accNumber = scanner.nextInt();
                    Account account = bankData.getAccount(accNumber);
                    if(account != null)
                        deleteAccount(account);
                    else System.out.println("Invalid Account Number");
                default:
                    System.out.println("Please choose valid option from menu ...");


            }
        }
    }
    public void createAccount(){
        // lastAccount Number;
        int accountNumber = bankData.getAccountNumber();

        // get Name of the AccountHolder
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Your First Name : ");
        String firstName = scanner.nextLine().trim();
        System.out.println("Enter Your Last Name : ");
        String lastName = scanner.nextLine().trim();

        // Account Type
        AccountType accountType;
        System.out.println("Account Type : \nCurrent   1\nSavings   2");
        int option = Integer.parseInt(scanner.nextLine());
        if(option == 1){
            accountType = AccountType.CURRENT;
        }else{
            accountType = AccountType.SAVING;
        }

        // Create Pin
        int securityPin;
        System.out.println("Create a 4 Digit Security pin \nOnly Numeric Digits b/w 1 & 9 inclusive");
        securityPin = Integer.parseInt(scanner.nextLine());

        // creating account;
        String fullName = firstName + " " + lastName;
        Account account = new Account(fullName, accountNumber, securityPin, accountType);

        // add to accounts list.
        bankData.addAccount(account);

        System.out.println("Account Created Successfully\nYour Credentials are\n");
        account.getCredentials().showCredentials();
        bankData.saveData();
    }
    public void depositCash(int accountNumber, double amount){
        Account account = bankData.getAccount(accountNumber);
        if(account != null){
            account.depositMoney(amount);
            bankData.saveData();
            return;
        }
        System.out.println("Account Not Found !");
    }
    public void withdrawCash(int accountNumber, double amount){
        Account account = bankData.getAccount(accountNumber);
        if(account == null){
            System.out.println("Account Not Found !");
            return;
        }
        if(account.getAccountBalance() > amount){
            account.withdrawMoney(amount);
        }else{
            System.out.println("Insufficient Balance");
        }
        bankData.saveData();
    }


}
