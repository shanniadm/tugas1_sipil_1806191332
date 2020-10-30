package apap.tugas.sipil.repository;
import apap.tugas.sipil.model.PilotPenerbanganModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PilotPenerbanganDb extends JpaRepository<PilotPenerbanganModel, Long> {
    
}
