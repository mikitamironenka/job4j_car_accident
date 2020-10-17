package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.controller.AccidentController;
import ru.job4j.accident.repository.AccidentMem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
//        List<String> users =  Arrays.asList("Hello", "World!", "How", "Are", "You");;
//        model.addAttribute("users", users);

        model.addAttribute("accidents", new AccidentController().getAccidents());
        return "index";
    }


}
