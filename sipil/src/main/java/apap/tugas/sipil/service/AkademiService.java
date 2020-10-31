package apap.tugas.sipil.service;
import apap.tugas.sipil.model.AkademiModel;
import java.util.List;

public interface AkademiService {
    //Method untuk mendapatkan daftar Akademi
    List<AkademiModel> getAkademiList();

    //Method untuk mendapatkan Akademi by Id.
    AkademiModel getAkademiById(Long id);
}
