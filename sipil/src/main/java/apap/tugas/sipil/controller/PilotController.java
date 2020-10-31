package apap.tugas.sipil.controller;
import apap.tugas.sipil.model.PilotModel;
import apap.tugas.sipil.model.PilotPenerbanganModel;
import apap.tugas.sipil.model.AkademiModel;
import apap.tugas.sipil.model.MaskapaiModel;
import apap.tugas.sipil.service.PilotService;
import apap.tugas.sipil.service.AkademiService;
import apap.tugas.sipil.service.MaskapaiService;
import apap.tugas.sipil.service.PilotPenerbanganService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PilotController {

    @Qualifier("pilotServiceImpl")
    @Autowired
    private PilotService pilotService;

    @Autowired
    private AkademiService akademiService;

    @Autowired 
    private MaskapaiService maskapaiService;

    @Autowired
    private PilotPenerbanganService pilotPenerbanganService;

    @GetMapping("/")
    private String home(){
        return "home";
    }

    @RequestMapping("/pilot")
    public String listPilot(Model model){
        List<PilotModel> listPilot = pilotService.getPilotList();
        //Add variabel semua PilotModel ke "listHotel" untuk dirender pada thymeleaf.
        model.addAttribute("listPilot", listPilot);
        return "view-all-pilot";
    }

    @GetMapping("/pilot/tambah")
    public String addPilotFormPage(Model model){
        model.addAttribute("pilot", new PilotModel());
        model.addAttribute("listAkademi", akademiService.getAkademiList());
        model.addAttribute("listMaskapai", maskapaiService.getMaskapaiList());
        return "form-add-pilot";
    }

    @PostMapping("/pilot/tambah")
    public String addPilotSubmit(
        @ModelAttribute PilotModel pilot,
        Model model
    ){
        String nip = pilotService.makeNip(pilot);
        pilot.setNip(nip);
        pilotService.addPilot(pilot);
        model.addAttribute("nip", pilot.getNip());
        return "add-pilot";
    }

    @GetMapping("/pilot/{nip}")
    public String detailPilot(
        @PathVariable String nip,
        Model model
    ){
        PilotModel pilot = pilotService.getPilotByNip(nip);
        List<PilotPenerbanganModel> jadwal = pilotPenerbanganService.getPilotPenerbanganByPilot(pilot);
        model.addAttribute("pilot", pilot);
        model.addAttribute("listJadwal", jadwal);
        return "detail-pilot";
    }

    @GetMapping("pilot/ubah/{nip}")
    public String changePilotFormPage(
        @PathVariable String nip,
        Model model
    ){
        PilotModel pilotModel = pilotService.getPilotByNip(nip);
        model.addAttribute("listAkademi", akademiService.getAkademiList());
        model.addAttribute("listMaskapai", maskapaiService.getMaskapaiList());
        model.addAttribute("pilot", pilotModel);
        return "form-update-pilot";
    }

    @PostMapping("pilot/ubah")
    public String changePilotFormSubmit(
        @ModelAttribute PilotModel pilot,
        Model model
    ){
        PilotModel pilotTarget = pilotService.updatePilot(pilot);
        pilotService.makeNip(pilotTarget);
        model.addAttribute("nip", pilotTarget.getNip());
        return "update-pilot";
    }
}
