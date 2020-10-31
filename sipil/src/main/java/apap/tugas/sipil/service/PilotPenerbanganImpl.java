package apap.tugas.sipil.service;
import apap.tugas.sipil.model.PilotPenerbanganModel;
import apap.tugas.sipil.model.PenerbanganModel;
import apap.tugas.sipil.model.PilotModel;
import apap.tugas.sipil.repository.PilotPenerbanganDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.transaction.Transactional;

@Service
@Transactional
public class PilotPenerbanganImpl implements PilotPenerbanganService{
    @Autowired
    PilotPenerbanganDb pilotPenerbanganDb;

    @Override
    public List<PilotPenerbanganModel> getPilotPenerbanganList(){
        return pilotPenerbanganDb.findAll();
    }

    @Override
    public List<PilotPenerbanganModel> getPilotPenerbanganByPilot(PilotModel pilot){
        return pilotPenerbanganDb.findByPilot(pilot);
    }

    @Override 
    public List<PilotPenerbanganModel> getPilotPenerbanganByPenerbangan(PenerbanganModel penerbangan){
        return pilotPenerbanganDb.findByPenerbangan(penerbangan);
    }

    @Override
    public List<PilotModel> getPilotByPenerbangan(PenerbanganModel penerbangan){
        return pilotPenerbanganDb.getPilotByPenerbangan(penerbangan);
    }

    @Override
    public void addPilotPenerbangan(PilotPenerbanganModel pilotPenerbangan){
        pilotPenerbanganDb.save(pilotPenerbangan);
    }
}
