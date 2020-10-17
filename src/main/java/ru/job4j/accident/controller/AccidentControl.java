package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AccidentControl {

    private final static AccidentMem accidents = AccidentMem.instOf();

    private final static AccidentControl ACCIDENT_CONTROL = new AccidentControl();

    public AccidentControl() {
    }

    public static AccidentControl instOfAccControl() {
        return ACCIDENT_CONTROL;
    }

    public static List<Accident> getAccidents(){
        return new ArrayList<Accident>(accidents.getAccidents().values());
    }

    @GetMapping("/create")
    public String create() {
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        accidents.create(accident);
        return "redirect:/";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Accident accident) {
        accidents.edit(accident);
        return "redirect:/";
    }
}
