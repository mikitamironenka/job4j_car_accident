package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.controller.AccidentController;
import ru.job4j.accident.service.AccidentService;

@Controller
public class IndexControl {

    private final AccidentController accidentController;

    public IndexControl(AccidentController accidentController) {
        this.accidentController = accidentController;
    }

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("accidents", accidentController.getAccidents());
        return "index";
    }


}
