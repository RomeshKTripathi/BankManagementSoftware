package account;

import java.io.Serializable;
import java.util.Scanner;

public class Credentials implements Serializable {
    int accountNumber;
    int securityPin;

    public Credentials(){}
    public Credentials(int accountNumber, int securityPin){
        this.securityPin = securityPin;
        this.accountNumber = accountNumber;
    }
    public void getCredentials(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please Enter your Account number");
        this.accountNumber = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("Please Enter your security Pin");
        this.securityPin = Integer.parseInt(scanner.nextLine().trim());
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getSecurityPin() {
        return securityPin;
    }

    public void setSecurityPin(int securityPin) {
        this.securityPin = securityPin;
    }


    public void showCredentials(){
        System.out.println("Account Number   :   " + this.accountNumber);
        System.out.println("Security Pin     :   " + this.securityPin);
    }
}
