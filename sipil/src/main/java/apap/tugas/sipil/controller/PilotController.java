package apap.tugas.sipil.controller;

import apap.tugas.sipil.model.PilotModel;
import apap.tugas.sipil.model.PilotPenerbanganModel;
import apap.tugas.sipil.model.AkademiModel;
import apap.tugas.sipil.model.MaskapaiModel;
import apap.tugas.sipil.service.PilotService;
import apap.tugas.sipil.service.AkademiService;
import apap.tugas.sipil.service.MaskapaiService;
import apap.tugas.sipil.service.PilotPenerbanganService;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/pilot/ubah/{nip}")
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

    @PostMapping("/pilot/ubah")
    public String changePilotFormSubmit(
        @ModelAttribute PilotModel pilot,
        Model model
    ){
        PilotModel pilotTarget = pilotService.updatePilot(pilot);
        pilotService.makeNip(pilotTarget);
        model.addAttribute("nip", pilotTarget.getNip());
        return "update-pilot";
    }

    @RequestMapping("/cari")
    public String cari(Model model){
        return "cari";
    }

    @GetMapping(value = "/cari/pilot")
    public String cariPilotMaskapaiDanAkademi(
        @RequestParam(value = "kodeMaskapai", required = false) String kodeMaskapai,
        @RequestParam(value = "idSekolah", required = false) Long idSekolah,
        Model model
    ){
        model.addAttribute("listMaskapai", maskapaiService.getMaskapaiList());
        model.addAttribute("listAkademi", akademiService.getAkademiList());

        List<PilotModel> listPilot;

        if(kodeMaskapai.equals("nothing")==false && idSekolah==0){
            MaskapaiModel mkp = maskapaiService.getMaskapaiByKode(kodeMaskapai);
            listPilot = pilotService.getPilotByMaskapai(mkp);

        } else if(kodeMaskapai.equals("nothing")==true && idSekolah!=0){
            AkademiModel aka = akademiService.getAkademiById(idSekolah);
            listPilot = pilotService.getPilotByAkademi(aka);

        } else if(kodeMaskapai.equals("nothing")==false && idSekolah!=0){
            MaskapaiModel mkp = maskapaiService.getMaskapaiByKode(kodeMaskapai);
            AkademiModel aka = akademiService.getAkademiById(idSekolah);
            listPilot = pilotService.getPilotByMaskapaiAndAkademi(mkp, aka);

        } else {
            listPilot = new ArrayList<PilotModel>();
        }
        model.addAttribute("listPilot", listPilot);
        return "cari-pilot-maskapai-akademi";
    }

    @GetMapping(value = "/cari/pilot/penerbangan-terbanyak")
    public String cariPilotPenerbanganTerbanyak(
        @RequestParam(value="kodeMaskapai", required = false) String kodeMaskapai,
        Model model
    ){
        List<Integer> hitung = new ArrayList<Integer>();
        List<PilotModel> listPilot = new ArrayList<PilotModel>();
        model.addAttribute("listMaskapai", maskapaiService.getMaskapaiList());
        
        if(kodeMaskapai.equals("nothing")==false){
            MaskapaiModel mas = maskapaiService.getMaskapaiByKode(kodeMaskapai);
            List<PilotModel> pilot = pilotService.getPilotByMaskapai(mas);
            if(pilot.size()>0){
                for(PilotModel pl : pilot){
                    Integer n = pilotPenerbanganService.getPilotPenerbanganByPilot(pl).size();
                    hitung.add(n);
                    listPilot.add(pl);
                }  
                if(pilot.size()>1){
                    for(int i =0; i< hitung.size()-1;i++){
                        if(hitung.get(i) < hitung.get(i+1)){
                            int temp = hitung.get(i);
                            PilotModel temp1 = listPilot.get(i);
                            //versi hitung
                            hitung.set(i, hitung.get(i+1));
                            hitung.set(i+1, temp);
                            //versi pilot models
                            listPilot.set(i, listPilot.get(i+1));
                            listPilot.set(i+1, temp1);
                            i = -1;
                        }
                    }
                }
                if(pilot.size()>=3){
                    List<PilotModel> finalPilot = new ArrayList<PilotModel>();
                    for(int i=0; i<3;i++){
                        finalPilot.add(listPilot.get(i));
                    }
                    model.addAttribute("listPilot", finalPilot);
                    model.addAttribute("hitung", hitung);
                } else {
                    model.addAttribute("listPilot", listPilot);
                    model.addAttribute("hitung", hitung);
                }
            } else {
                model.addAttribute("listPilot", listPilot);
            }
        } else {
            model.addAttribute("listPilot", listPilot);
        }
        return "cari-penerbangan-terbanyak";
    }
}
