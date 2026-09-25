import java.util.ArrayList;
import java.util.List;

public class Account {
    private String number;
    private float amount;
    private Client acHolder;
    private List<String> history = new ArrayList<>(); // To store history
    public static int count = 1000;

    public Account(float amount, Client acHolder) {
        this.number = "ACC-" + (++count);
        this.amount = amount;
        this.acHolder = acHolder;
        history.add("Account created with initial deposit: $" + amount);
    }

    public float withdraw(float amt) {
        if (this.amount >= amt) {
            this.amount -= amt;
            history.add("Withdrew: $" + amt + " | Balance: $" + this.amount);
        }
        return this.amount;
    }

    public float deposit(float amt) {
        this.amount += amt;
        history.add("Deposited: $" + amt + " | Balance: $" + this.amount);
        return this.amount;
    }

    public List<String> getHistory() { return history; }
    public String getNumber() { return number; }
    public float getAmount() { return amount; }
}