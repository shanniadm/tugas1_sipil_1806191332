package apap.tugas.sipil.service;
import apap.tugas.sipil.model.AkademiModel;
import apap.tugas.sipil.model.MaskapaiModel;
import apap.tugas.sipil.model.PilotModel;

import java.util.HashMap;
import java.util.List;

public interface PilotService {
    //Method untuk mendapatkan daftar pilot yang telah terdaftar
    List<PilotModel> getPilotList();

    //Method untuk menambah pilot
    void addPilot(PilotModel pilot);

    //Method membuat NIP
    String makeNip(PilotModel pilot);

    PilotModel getPilotByNip(String nip);

    PilotModel updatePilot(PilotModel pilotModel);

    List<PilotModel> getPilotByMaskapaiAndAkademi(MaskapaiModel maskapai, AkademiModel akademi);

    List<PilotModel> getPilotByMaskapai(MaskapaiModel maskapai);

    List<PilotModel> getPilotByAkademi(AkademiModel akademi);
}
