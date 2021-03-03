package webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;


@Controller
public class UtenteController {

    @RequestMapping(value="/")
    public String getHome(Model model) { //gestisce gli accessi alla pagina home
        System.out.println("Show Home Page");
        return "index";
    }
}