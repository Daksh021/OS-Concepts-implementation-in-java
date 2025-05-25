
class Account{

    protected int balance = 10000;

    public synchronized void withdraw(String name , int amount){

        if(balance >= amount){
            System.out.println("Withdrawing");
            balance -= amount;
            System.out.println(name + " withdrew " + amount + ". Remaining: " + balance);

        }

        else {
            System.out.println("Cannot withdraw");
        }

    }

}

class User extends Thread{

    private final Account account;
    private final int amount;


    public User(Account account, String name,int amount){
        super(name);
        this.account = account;
        this.amount = amount;
    }

    public void run(){
        account.withdraw(getName(),amount);
    }




}






public class BankDemo {

    public static void main(String[] args) {

        Account sharedAccount = new Account();

        User user1 = new User(sharedAccount, "Alice", 700);
        User user2 = new User(sharedAccount, "Bob", 500);

        user1.start();
        user2.start();
    }

}
