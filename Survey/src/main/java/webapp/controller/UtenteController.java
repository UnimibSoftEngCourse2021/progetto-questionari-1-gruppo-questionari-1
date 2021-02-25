package webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.ui.Model;


@Controller
public class UtenteController {

    @RequestMapping(value="/")
    public String getHome(Model model) { //gestisce gli accessi alla pagina home
        System.out.println("Show index Page");
        return "index";
        /*@RequestMapping
        public String getHome() {
            
        }*/

    }
}