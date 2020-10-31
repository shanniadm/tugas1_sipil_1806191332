package apap.tugas.sipil.repository;

import apap.tugas.sipil.model.MaskapaiModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaskapaiDb extends JpaRepository<MaskapaiModel,Long>{
    List<MaskapaiModel> findByKode(String kode);
}
