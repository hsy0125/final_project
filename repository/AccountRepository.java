package mobile.banking.account.repository;

import mobile.banking.account.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    Account save(final Account account);
    List<Account> findAll();
    Optional<Account> findById(final Long id);
    int update(final Account account);
    int delete(final Long id);
}
