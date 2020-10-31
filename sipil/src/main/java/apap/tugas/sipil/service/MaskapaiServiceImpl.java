package apap.tugas.sipil.service;
import apap.tugas.sipil.model.MaskapaiModel;
import apap.tugas.sipil.repository.MaskapaiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.transaction.Transactional;

@Service
@Transactional
public class MaskapaiServiceImpl implements MaskapaiService{
    @Autowired
    MaskapaiDb maskapaiDb;

    @Override
    public List<MaskapaiModel> getMaskapaiList(){
        return maskapaiDb.findAll();
    }

}
