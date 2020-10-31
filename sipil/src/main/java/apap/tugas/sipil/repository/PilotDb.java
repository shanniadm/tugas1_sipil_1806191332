package apap.tugas.sipil.repository;
import apap.tugas.sipil.model.AkademiModel;
import apap.tugas.sipil.model.MaskapaiModel;
import apap.tugas.sipil.model.PilotModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PilotDb extends JpaRepository<PilotModel, Long> {
    List<PilotModel> findByNip(String nip);

    List<PilotModel> findByMaskapaiAndAkademi(MaskapaiModel maskapai, AkademiModel akademi);

    List<PilotModel> findByAkademi(AkademiModel akademi);

    List<PilotModel> findByMaskapai(MaskapaiModel maskapai);
}
