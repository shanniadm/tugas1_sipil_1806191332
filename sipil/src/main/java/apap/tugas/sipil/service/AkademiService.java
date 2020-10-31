package apap.tugas.sipil.service;
import apap.tugas.sipil.model.AkademiModel;
import java.util.List;

public interface AkademiService {
    //Method untuk mendapatkan daftar Akademi
    List<AkademiModel> getAkademiList();

    AkademiModel getAkademiById(Long id);
}
