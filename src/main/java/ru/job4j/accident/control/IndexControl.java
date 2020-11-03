package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.controller.AccidentController;
import ru.job4j.accident.service.AccidentService;

import ru.job4j.accident.controller.AccidentControl;
import ru.job4j.accident.repository.AccidentMem;

@Controller
public class IndexControl {

    private final AccidentController accidentController;

    public IndexControl(AccidentController accidentController) {
        this.accidentController = accidentController;
    }

    @GetMapping("/")
    public String index(Model model) {
//        List<String> users =  Arrays.asList("Hello", "World!", "How", "Are", "You");;
//        model.addAttribute("users", users);

        model.addAttribute("accidents", accidentController.getAccidents());
        return "index";
    }


}
