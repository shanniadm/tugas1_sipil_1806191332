package apap.tugas.sipil.service;
import apap.tugas.sipil.model.PilotModel;
import apap.tugas.sipil.repository.PilotDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import javax.transaction.Transactional;

@Service
@Transactional
public class PilotServiceImpl implements PilotService{
    @Autowired
    PilotDb pilotDb;

    @Override
    public List<PilotModel> getPilotList(){
        return pilotDb.findAll();
    }

    @Override
    public void addPilot(PilotModel pilot){
        pilotDb.save(pilot);
    }

    @Override
    public String makeNip(PilotModel pilot){
        String nip = "";
        //jenis kelamin
        nip += Integer.toString(pilot.getJenisKelamin());
        //2 huruf awal tempat lahir
        nip += pilot.getTempatLahir().toUpperCase().substring(0,2);
        //karakter terakhir nama pilot
        nip += pilot.getNama().substring(pilot.getNama().length()-1).toUpperCase();
        //tanggal ddmm
        DateFormat dateFormat = new SimpleDateFormat("ddMMYYYY");
        String strDate = dateFormat.format(pilot.getTanggalLahir());
        nip += strDate.substring(0,7);
        //2 kapital random
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rd = new Random();
        char letter1 = abc.charAt(rd.nextInt(abc.length()));
        nip += letter1;
        char letter2 = abc.charAt(rd.nextInt(abc.length()));
        nip += letter2;
        return nip;
    }

    @Override
    public PilotModel getPilotByNip(String nip){
        List<PilotModel> p = pilotDb.findByNip(nip);
        return p.get(0);
    }

    @Override
    public PilotModel updatePilot(PilotModel pilot){
        PilotModel target = pilotDb.findById(pilot.getId()).get();
        target.setNama(pilot.getNama());
        target.setNik(pilot.getNik());
        target.setTempatLahir(pilot.getTempatLahir());
        target.setTanggalLahir(pilot.getTanggalLahir());
        target.setAkademi(pilot.getAkademi());
        target.setMaskapai(pilot.getMaskapai());
        pilotDb.save(target);
        return target;
    }
}
