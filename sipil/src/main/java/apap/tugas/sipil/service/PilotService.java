package apap.tugas.sipil.service;
import apap.tugas.sipil.model.PilotModel;
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
}
