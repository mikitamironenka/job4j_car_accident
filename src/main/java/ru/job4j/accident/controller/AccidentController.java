package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AccidentController {

    private AccidentService accidentService;

    public AccidentController(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    public List<Accident> getAccidents(){
        return new ArrayList<Accident>(this.accidentService.getAccidents().values());
    }
}
