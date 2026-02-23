package BankWithdrawExercise;

public class Account {
    private final int accountNumber;
    private final String holder;
    private double balance;
    private double withdrawLimit;

    public Account(int accountNumber, String holder, double balance, double withdrawLimit) throws DomainException{
        if (balance < 0) {
            throw new DomainException("Balance cannot be negative");
        }
        if (accountNumber < 0) {
            throw new DomainException("Account number cannot be negative");
        }
        if (withdrawLimit < 0) {
            throw new DomainException("Withdraw limit cannot be negative");
        }
        this.accountNumber = accountNumber;
        this.holder = holder;
        this.balance = balance;
        this.withdrawLimit = withdrawLimit;
    }

    public void withdraw(double amount) throws DomainException {
        if (amount > withdrawLimit) {
            throw new DomainException("The amount exceeds withdraw limit");
        }
        if (amount > balance) {
            throw new DomainException("Not enough balance");
        }
        balance -= amount;
    }

    @Override
    public String toString() {
        return """
                Account number: %d
                Holder: %s
                Current balance: %.2f
                """.formatted(accountNumber, holder, balance);
    }
}