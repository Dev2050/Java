package sec.thousandaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sec.thousandaire.domain.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
    UserDetails findByName(String name);
}
