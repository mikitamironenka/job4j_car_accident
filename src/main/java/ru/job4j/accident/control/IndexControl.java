package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.controller.AccidentControl;

@Controller
public class IndexControl {

    private final AccidentControl accidentController;

    public IndexControl(AccidentControl accidentController) {
        this.accidentController = accidentController;
    }

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("accidents", accidentController.getAccidents());
        return "index";
    }


}
