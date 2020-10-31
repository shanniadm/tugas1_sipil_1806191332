package apap.tugas.sipil.controller;
import apap.tugas.sipil.model.PenerbanganModel;
import apap.tugas.sipil.model.PilotModel;
import apap.tugas.sipil.model.PilotPenerbanganModel;
import apap.tugas.sipil.service.PenerbanganService;
import apap.tugas.sipil.service.PilotService;
import apap.tugas.sipil.service.PilotPenerbanganService;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class PenerbanganController{

    @Qualifier("penerbanganServiceImpl")
    @Autowired
    PenerbanganService penerbanganService;

    @Autowired
    PilotPenerbanganService pilotPenerbanganService;

    @Autowired
    PilotService pilotService;

    @RequestMapping("/penerbangan")
    public String listPenerbangan(Model model){
        List<PenerbanganModel> listPenerbangan = penerbanganService.getPenerbanganList(); 
        model.addAttribute("listPenerbangan", listPenerbangan);
        return "view-all-penerbangan";
    }
    
    @GetMapping("/penerbangan/tambah")
    public String addPenerbanganFormPage(Model model){
        model.addAttribute("penerbangan", new PenerbanganModel());
        return "form-add-penerbangan";
    }

    @PostMapping("/penerbangan/tambah")
    public String addPenerbanganSubmit(
        @ModelAttribute PenerbanganModel penerbangan,
        Model model
    ){
        penerbanganService.addPenerbangan(penerbangan);
        model.addAttribute("kode", penerbangan.getKode());
        return "add-penerbangan";
    }

    @GetMapping("/penerbangan/detail/{id}")
    public String detailPenerbangan(
        @PathVariable Long id,
        Model model){
        PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(id);
        List<PilotPenerbanganModel> listJadwal = pilotPenerbanganService.getPilotPenerbanganByPenerbangan(penerbangan);
        model.addAttribute("listPilot", pilotService.getPilotList());
        model.addAttribute("jadwalBaru", new PilotPenerbanganModel());
        model.addAttribute("penerbangan", penerbangan);
        model.addAttribute("listJadwal", listJadwal);
        return "detail-penerbangan";
    }

    @GetMapping("/penerbangan/ubah/{id}")
    public String changePenerbanganFormPage(
        @PathVariable Long id, 
        Model model
    ){
        PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(id);
        model.addAttribute("penerbangan", penerbangan);
        return "form-update-penerbangan";
    }

    @PostMapping("/penerbangan/ubah")
    public String changePenerbanganFormSubmit(
        @ModelAttribute PenerbanganModel penerbangan,
        Model model
    ){
        PenerbanganModel penTarget = penerbanganService.updatePenerbangan(penerbangan);
        model.addAttribute("kode", penTarget.getKode());
        return "update-penerbangan";
    }

    @PostMapping("/penerbangan/hapus")
    public String deletePenerbangan(
        @ModelAttribute PenerbanganModel pen,
        Model model
    ){
        List<PilotPenerbanganModel> listPen = pilotPenerbanganService.getPilotPenerbanganByPenerbangan(pen);
        if(listPen.size()>0){
            model.addAttribute("kode", pen.getKode());
            return "cant-delete-penerbangan";
        } else {
            model.addAttribute("kode", pen.getKode());
            penerbanganService.deletePenerbangan(pen.getId());
            return "delete-penerbangan";
        }
    }

    @PostMapping("/penerbangan/{penerbanganId}/pilot/tambah")
    public String addNewPilotPenerbangan(
        @PathVariable Long penerbanganId,
        @ModelAttribute PilotPenerbanganModel jadwalBaru,
        Model model
    ){
        PenerbanganModel pen = penerbanganService.getPenerbanganById(penerbanganId);
        pilotPenerbanganService.addPilotPenerbangan(jadwalBaru);
        model.addAttribute("nama", jadwalBaru.getPilot().getNama());
        model.addAttribute("kode", pen.getKode());
        return "add-jadwal-baru";
    }


}