package blackjack;

public class Account {
    private int balance = 0;

    public Account() {
        balance = GameConfig.getDefaultBalance();
    }

    /**
     * 將錢由a轉到b
     * 
     * @param a      轉出Account
     * @param b      轉入Account
     * @param amount 轉帳金額
     */
    public static void transfer(Account a, Account b, int amount) {
        a.withdraw(amount);
        b.deposit(amount);
    }

    /**
     * 提款
     * 
     * @param amount 提款金額
     */
    public void withdraw(int amount) {
        if (balance - amount >= 0) {
            balance -= amount;
        } else {
            System.out.println("哇哇哇! 錯誤! 沒有錢");
        }
        
    }

    /**
     * 存款
     * 
     * @param amount 存款金額
     */
    public void deposit(int amount) {
        balance += amount;
    }

    /**
     * 取得帳戶餘額
     * 
     * @return 帳戶餘額
     */
    public int getBalance() {
        return balance;
    }
}
