package securityback.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import securityback.entities.User;

@Service
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
