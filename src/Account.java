
public class Account {
    
    //size of account
    private volatile int balance;
    
    //priority of account
    private volatile int priority;

    public Account(int balance, int priority) {
        this.balance = balance;
        this.priority = priority;
    }

    public int getBalance() {
        return balance;
    }
    
    public int getPriority() {
        return priority;
    }
    
    public Account(int balance) {
        this.balance = balance;
    }
    
    //deposite
    public void incBalance(int amount){
        balance += amount;
    }
    
    //with draw
    public void decBalance(int amount){
        balance -= amount;
    }
    
}
