import java.util.logging.Level;
import java.util.logging.Logger;

public class AndersonSemHierarchyMain {

    public static void main(String[] args) {
        
        final Account a = new Account(1000, 2);
        final Account b = new Account(4000, 1);
        
        new Thread (() -> {
            try {
                transfer(a, b, 300);
            } catch (InsuffientFundsException ex) {
                Logger.getLogger(AndersonSemHierarchyMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();  
        
        try {
            transfer(b, a, 500);
        } catch (InsuffientFundsException ex) {
            Logger.getLogger(AndersonSemHierarchyMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static void transfer(Account acc_src, Account acc_dst, int amount) 
            throws InsuffientFundsException{
        Account first_lock;
        Account second_lock;        
        
        if (acc_src.getBalance() < amount)
            throw new InsuffientFundsException();
        
        //set sequence of object locking
        if(acc_src.getPriority() > acc_dst.getPriority()){
            first_lock = acc_src;
            second_lock = acc_dst;           
        }
        else{
            first_lock = acc_dst;
            second_lock = acc_src;
        }
        
        synchronized(first_lock){
            try {
                Thread.sleep(1000);
            synchronized(second_lock){
            System.out.format("Balance before source = %d, balance destination = %d, amount = %d %n", acc_src.getBalance(), acc_dst.getBalance(), amount);
                
            acc_src.decBalance(amount);
            acc_dst.incBalance(amount);
        
            System.out.format("Balance after source = %d, balance destination = %d %n", acc_src.getBalance(), acc_dst.getBalance());        
            }
            } catch (InterruptedException ex) {
                Logger.getLogger(AndersonSemHierarchyMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }

private static class InsuffientFundsException extends Exception {

    public InsuffientFundsException() {
        }
    }
    
}
