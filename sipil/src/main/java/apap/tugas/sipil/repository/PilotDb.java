package apap.tugas.sipil.repository;
import apap.tugas.sipil.model.PilotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PilotDb extends JpaRepository<PilotModel, Long> {
    
}
