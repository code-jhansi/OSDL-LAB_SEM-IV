class BankAccount{
    private int balance=1000;
    void withdraw(int amount){
        System.out.println(Thread.currentThread().getName() + " is trying to withdraw " + amount);
        synchronized(this){
            if(balance>=amount){
                System.out.println(Thread.currentThread().getName() + "is processing withdrawal...");
                try{
                    Thread.sleep(5000);
                }catch(InterruptedException e){
                    System.out.println("Thread Interrupted");
                }
                balance-=amount;
                System.out.println(Thread.currentThread().getName() + "completed withdrawal. Reamining balance: " + balance);
            }else{
                System.out.println(Thread.currentThread().getName() + "-Insufficient balance!");
            }
        }
    }
}
//Thread class
class Customer extends Thread{
    private BankAccount account;
    private int amount;
    Customer(BankAccount account,int amount,String name){
        super(name);
        this.account=account;
        this.amount=amount;
    }
    public void run(){
        account.withdraw(amount);
    }
}
public class SynchronizedBlockDemo {
    public static void main(String[] args){
        BankAccount account=new BankAccount();

        //creating multiple threads accessing same resource
        Customer c1 = new Customer(account,700,"Customer-1");
        Customer c2 = new Customer(account,500,"Customer-2");

        c1.start();
        c2.start();
    }    
}
