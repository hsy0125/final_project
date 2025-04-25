package mobile.banking.account;

import java.math.BigDecimal;

public class Account {
    private Long id;
    private String accountNumber;
    private BigDecimal balance;
    private Long customerId;

    public Account() {

    }

    public Account(Long id, String accountNumber, BigDecimal balance, Long customerId) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customerId = customerId;
    }

    public Account(String accountNumber, BigDecimal balance, Long customerId) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", customerId=" + customerId +
                '}';
    }
}
