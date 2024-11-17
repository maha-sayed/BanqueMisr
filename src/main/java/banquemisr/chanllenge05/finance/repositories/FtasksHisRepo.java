package banquemisr.chanllenge05.finance.repositories;

import banquemisr.chanllenge05.finance.entities.FtasksHisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FtasksHisRepo extends JpaRepository<FtasksHisEntity,Integer> {
}
