package apap.tugas.sipil.service;

import apap.tugas.sipil.model.AkademiModel;
import apap.tugas.sipil.repository.AkademiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.transaction.Transactional;

@Service
@Transactional
public class AkademiServiceImpl implements AkademiService{
    @Autowired 
    AkademiDb akademiDb;

    @Override
    public List<AkademiModel> getAkademiList(){
        return akademiDb.findAll();
    }
}
