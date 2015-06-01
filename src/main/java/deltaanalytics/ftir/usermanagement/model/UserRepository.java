package deltaanalytics.ftir.usermanagement.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByAccountAndPassword(String account, String password);
}
