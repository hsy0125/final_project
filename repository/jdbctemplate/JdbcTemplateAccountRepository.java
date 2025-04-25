package mobile.banking.account.repository.jdbctemplate;

import mobile.banking.account.Account;
import mobile.banking.account.repository.AccountRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class JdbcTemplateAccountRepository implements AccountRepository {

    private static final Logger log = LoggerFactory.getLogger(JdbcTemplateAccountRepository.class);
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateAccountRepository(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Account save(Account account) {
        String sql = "INSERT INTO account(account_number, balance, customer_id) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection->{
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, account.getAccountNumber());
            ps.setBigDecimal(2, account.getBalance());
            ps.setLong(3, account.getCustomerId());
            return ps;
        }, keyHolder);

        long key = keyHolder.getKey().longValue();

        log.debug("key: {}", key);

        account.setId(key);
        return account;
    }

    @Override
    public List<Account> findAll() {
        String sql = "SELECT * FROM account";
        List<Account> accountList = jdbcTemplate.query(sql, rowMapper());
        log.debug("accountList: {}", accountList);
        return accountList;
    }

    @Override
    public Optional<Account> findById(final Long id) {
        String sql = "Select * FROM account where id = ?";
        Account account = jdbcTemplate.queryForObject(sql, rowMapper(), id);
        return Optional.of(account);
    }


    @Override
    public int update(final Account account) {
        String sql = "UPDATE account SET balance = ?, WHERE id = ?";
        return jdbcTemplate.update(sql, account.getBalance(), account.getId());
    }


    @Override
    public int delete(final Long id) {
        String sql = "DELETE FROM account WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    private RowMapper<Account> rowMapper() {
        return (rs, rowNum) -> new Account(
                rs.getLong("id"),
                rs.getString("account_number"),
                rs.getBigDecimal("balance"),
                rs.getLong("customer_id")
        );
    }
}
