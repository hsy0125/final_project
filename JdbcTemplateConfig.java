package mobile.banking.config;

import mobile.banking.account.AccountService;
import mobile.banking.account.repository.AccountRepository;
import mobile.banking.account.repository.jdbctemplate.JdbcTemplateAccountRepository;
import mobile.banking.customer.CustomerService;
import mobile.banking.customer.repository.CustomerRepository;
import mobile.banking.customer.repository.jdbctemplate.JdbcTemplateCustomerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcTemplateConfig {
    private final DataSource dataSource;

    public JdbcTemplateConfig(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public CustomerRepository customerRepository() {
        return new JdbcTemplateCustomerRepository(dataSource);
    }

    @Bean
    public CustomerService customerService(){ return new CustomerService(customerRepository());}

    @Bean
    public AccountService accountService(){
        return new AccountService(accountRepository());}

    @Bean
    public AccountRepository accountRepository(){
        return new JdbcTemplateAccountRepository(dataSource);}
}
