package apap.tugas.sipil.service;
import apap.tugas.sipil.model.PilotPenerbanganModel;
import apap.tugas.sipil.model.PenerbanganModel;
import apap.tugas.sipil.model.PilotModel;
import java.util.List;

public interface PilotPenerbanganService {
    List<PilotPenerbanganModel> getPilotPenerbanganList();

    List<PilotPenerbanganModel> getPilotPenerbanganByPilot(PilotModel pilot);

    List<PilotPenerbanganModel> getPilotPenerbanganByPenerbangan(PenerbanganModel penerbangan);

    List<PilotModel> getPilotByPenerbangan(PenerbanganModel penerbangan);

    void addPilotPenerbangan(PilotPenerbanganModel pilotPenerbangan);
}
