package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;
import javax.servlet.http.HttpServletRequest;

@Controller
public class AccidentControl {

    private AccidentService accidentService;

    public AccidentControl(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping(value = "/create", produces = { "application/json", "application/xml" })
    public String create(Model model) {
        model.addAttribute("types", this.accidentService.getTypes());
        model.addAttribute("rules", this.accidentService.getRules());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] rIds = req.getParameterValues("rIds");
        accidentService.addRulesToAccident(accident, rIds);
        accidentService.create(accident);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidentService.findById(id));
        model.addAttribute("types", this.accidentService.getTypes());
        return "accident/update";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Accident accident) {
        accidentService.update(accident);
        return "redirect:/";
    }
}
