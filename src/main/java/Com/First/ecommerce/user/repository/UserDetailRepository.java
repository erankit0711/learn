package Com.First.ecommerce.user.repository;

import Com.First.ecommerce.user.domain.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Long>  {
    Optional<UserDetail> findByUserDetailId(String username);
}





