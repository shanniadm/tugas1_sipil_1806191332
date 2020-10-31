package apap.tugas.sipil.controller;
import apap.tugas.sipil.model.PenerbanganModel;
import apap.tugas.sipil.model.PilotModel;
import apap.tugas.sipil.model.PilotPenerbanganModel;
import apap.tugas.sipil.service.PenerbanganService;
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
        model.addAttribute("penerbangan", penerbangan);
        model.addAttribute("listJadwal", listJadwal);
        return "detail-penerbangan";
    }
}