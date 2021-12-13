public class Account {
    private int balance = 0;

    public Account() {
        balance = GameConfig.getDefaultBalance();
    }

    public static void transfer(Account a, Account b, int amount) {
        a.withdraw(amount);
        b.deposit(amount);
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public int getBalance() {
        return balance;
    }
}
