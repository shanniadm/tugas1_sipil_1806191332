package apap.tugas.sipil.repository;
import apap.tugas.sipil.model.PilotPenerbanganModel;
import apap.tugas.sipil.model.PenerbanganModel;
import apap.tugas.sipil.model.PilotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PilotPenerbanganDb extends JpaRepository<PilotPenerbanganModel, Long> {
    List<PilotPenerbanganModel> findByPilot(PilotModel pilot);
    
    List<PilotPenerbanganModel> findByPenerbangan(PenerbanganModel penerbangan);

    List<PilotModel> getPilotByPenerbangan(PenerbanganModel penerbangan);
}
