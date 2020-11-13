package expensetracker.expensetracker.repository;
import java.util.Optional;

import expensetracker.expensetracker.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Long>{

    Optional<Users> findByUsername(String username);
    Optional<Users> findUsersByEmail(String email);
    Boolean existsByUsernameAndEmail(String username, String email);

}

