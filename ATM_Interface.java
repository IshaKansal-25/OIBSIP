import java.util.*;

class Account {
    String accountHolder;
    String userName;
    int pin;
    String accountNumber;
    float balance;
    int transaction=0;
    String transactionHistory="";

    public void register() {
        Scanner sc=new Scanner(System.in);
        System.out.print("\nEnter your name : ");
        accountHolder=sc.nextLine();
        System.out.print("Enter username : ");
        userName=sc.nextLine();
        System.out.print("Enter pin : ");
        pin=sc.nextInt();
        System.out.print("Enter your Account number : ");
        accountNumber=sc.nextLine();
        System.out.print("Enter initial balance (max limit is Rs.10,00,000) : ");
        balance=sc.nextFloat();
        System.out.println("\nRegistration completed...");
        System.out.println("Kindly login...");
    }

    public boolean login() {
        boolean isLogin=false;
        Scanner sc=new Scanner(System.in);
        while(!isLogin) {
            System.out.print("\nEnter username : ");
            String user_name=sc.nextLine();
            if(user_name.equals(userName)) {
                while(!isLogin) {
                    System.out.print("Enter your pin : ");
                    int pinNo=sc.nextInt();
                    if(pinNo==pin) {
                        System.out.print("\nLogin Successful !!!");
                        isLogin=true;
                    }
                    else {
                        System.out.println("Incorrct pin !!");
                    }
                }
            }
            else {
                System.out.print("User not found !!");
            }
        }
        return isLogin;
    }

    public void withdraw() {
        System.out.print("\nEnter amount to withdraw : ");
        Scanner sc=new Scanner(System.in);
        float amount=sc.nextFloat();
        try {
            if(balance >= amount) {
                transaction++;
                balance-=amount;
                System.out.println("\nWithdrawal Successful !!!");
                String str=amount + " Rs withdrawed\n";
                transactionHistory=transactionHistory.concat(str);
            }
            else {
                System.out.println("\nInsufficient balance...");
            }
        }
        catch (Exception e) {
        }
        System.out.println("--------------------------------------------------------------------");
    }

    public void deposit() {
        System.out.print("\nEnter amount to deposit : ");
        Scanner sc=new Scanner(System.in);
        float amount=sc.nextFloat();
        try {
            if((amount <= 1000000f) && (amount+balance <= 1000000f)) {
                transaction++;
                balance+=amount;
                System.out.println("\nDeposit Successful !!!");
                String str=amount + " Rs deposited\n";
                transactionHistory=transactionHistory.concat(str);
            }
            else {
                System.out.println("\nLimit excceded... Limit is Rs 10,00,000.00");
            }
        }
        catch (Exception e){
        }
        System.out.println("--------------------------------------------------------------------");
    }

    public void transfer() {
        Scanner sc=new Scanner(System.in);
        System.out.print("\nEnter receipent's name : ");
        String receipent=sc.nextLine();
        System.out.print("Enter amount to transfer (transfer limit is Rs 50,000) : ");
        float amount=sc.nextFloat();
        try {
            if(balance >= amount) {
                if(amount <= 50000f) {
                    transaction++;
                    balance-=amount;
                    System.out.println("\nTransfer Successful to " + receipent + " !!!");
                    String str=amount + " Rs transferred to " + receipent + "\n";
                    transactionHistory = transactionHistory.concat(str);
                }
                else {
                    System.out.println("\nTransfer limit exceeded... Transfer limit is Rs 50,000.00");
                }
            }
            else {
                System.out.println("\nInsufficient balance...");
            }
        }
        catch (Exception e){
        }
        System.out.println("--------------------------------------------------------------------");
    }

    public void checkBalance() {
        System.out.println("\nRs " + balance);
        System.out.println("--------------------------------------------------------------------");
    }

    public void accountTransactionHistory() {
        if(transaction == 0) {
            System.out.println("\nNo transactions...");
        }
        else {
            System.out.println("\n" + transactionHistory);
        }
        System.out.println("--------------------------------------------------------------------");
    }
}

public class ATM_Interface {
    public static int takeInput(int limit) {
        int input=0;
        boolean flag=false;
        while(!flag) {
            try {
                Scanner sc=new Scanner(System.in);
                input=sc.nextInt();
                flag=true;
                if(flag && input>limit || input<1) {
                    System.out.println("Choose number between 1 to " + limit);
                    flag=false;
                }
            }
            catch (Exception e) {
                System.out.println("Enter only integer value...");
                flag=false;
            }
        }
        return input;
    }

    public static void main(String[] args) {
        System.out.println("\n⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐ \uD835\uDD4E\uD835\uDD3C\uD835\uDD43ℂ\uD835\uDD46\uD835\uDD44\uD835\uDD3C \uD835\uDD4B\uD835\uDD46  \uD835\uDD38\uD835\uDD4B\uD835\uDD44 ℂ\uD835\uDD46ℕ\uD835\uDD4A\uD835\uDD46\uD835\uDD43\uD835\uDD3C \uD835\uDD40ℕ\uD835\uDD4B\uD835\uDD3Cℝ\uD835\uDD3D\uD835\uDD38ℂ\uD835\uDD3C ⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐");
        System.out.println("\n1.Register \n2.Quit");
        System.out.print("Enter your choice : ");
        int choice=takeInput(2);

        if(choice==1) {
            Account acc=new Account();
            acc.register();
            while (true) {
                System.out.println("\n1.Login \n2.Quit");
                System.out.print("Enter your choice : ");
                int secondChoice=takeInput(2);

                if(secondChoice==1) {
                    if(acc.login()) {
                        System.out.println("\n✦✦✦✦✦✦✦✦✦✦ Welcome Back " + acc.accountHolder + " ✦✦✦✦✦✦✦✦✦✦\n");
                        boolean isFinished=false;
                        while (!isFinished) {
                            System.out.println("\n1.Transaction History \n2.Withdraw \n3.Deposit \n4.Transfer \n5.Check Balance \n6.Quit");
                            System.out.print("Enter your choice : ");
                            int thirdChoice=takeInput(6);
                            switch (thirdChoice) {
                                case 1: {
                                    acc.accountTransactionHistory();
                                    break;
                                }
                                case 2: {
                                    acc.withdraw();
                                    break;
                                }
                                case 3: {
                                    acc.deposit();
                                    break;
                                }
                                case 4: {
                                    acc.transfer();
                                    break;
                                }
                                case 5: {
                                    acc.checkBalance();
                                    break;
                                }
                                case 6: {
                                    System.out.println("\nExitting console app...");
                                    System.exit(0);
                                }
                            }
                        }
                    }
                }
                else {
                    System.out.println("\nExitting console app...");
                    System.exit(0);
                }
            }
        }
        else {
            System.out.println("\nExitting console app...");
            System.exit(0);
        }
    }
}
