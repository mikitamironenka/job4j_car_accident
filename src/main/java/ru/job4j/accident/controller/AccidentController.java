package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class AccidentController {

    private HashMap<Integer, Accident> accidentsMap;

    public AccidentController() {
        accidentsMap = new AccidentMem().getAccidents();
    }

    public List<Accident> getAccidents(){
        return new ArrayList<Accident>(this.accidentsMap.values());
    }
}
