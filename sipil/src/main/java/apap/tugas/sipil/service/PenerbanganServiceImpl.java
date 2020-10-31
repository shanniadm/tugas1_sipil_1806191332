package apap.tugas.sipil.service;
import apap.tugas.sipil.model.PenerbanganModel;
import apap.tugas.sipil.repository.PenerbanganDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.transaction.Transactional;

@Service
@Transactional
public class PenerbanganServiceImpl implements PenerbanganService{
    @Autowired 
    PenerbanganDb penerbanganDb;

    @Override
    public List<PenerbanganModel> getPenerbanganList(){
        return penerbanganDb.findAll();
    }

    @Override
    public void addPenerbangan(PenerbanganModel penerbangan){
        penerbanganDb.save(penerbangan);
    }

    @Override
    public PenerbanganModel getPenerbanganById(Long id){
        return penerbanganDb.getOne(id);
    }
}