package apap.tugas.sipil.controller;
import apap.tugas.sipil.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PilotController {
    @GetMapping("/")
    private String home(){
        return "home";
    }
}
