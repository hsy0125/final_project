package mobile.banking.account;

import java.math.BigDecimal;

public class AccountRequest {
    private String accountNumber;
    private BigDecimal balance;
    private Long customerId;

    public AccountRequest() {

    }



    public AccountRequest(String accountNumber, BigDecimal balance, Long customerId) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customerId = customerId;
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


    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
