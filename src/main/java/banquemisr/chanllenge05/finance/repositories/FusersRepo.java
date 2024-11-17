package banquemisr.chanllenge05.finance.repositories;

import banquemisr.chanllenge05.finance.entities.FusersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FusersRepo extends JpaRepository<FusersEntity,Integer> {

    FusersEntity findByUsername(String username);
}
