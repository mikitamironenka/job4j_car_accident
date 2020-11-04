package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;
import java.util.HashMap;
import java.util.List;

@Service
public class AccidentService {

    private AccidentMem accidentMem;

    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    public HashMap<Integer, Accident> getAccidents() {
        return this.accidentMem.getAccidents();
    }

    public List<AccidentType> getTypes() {
        return this.accidentMem.getTypes();
    }

    public HashMap<Integer, Rule> getRules() {
        return this.accidentMem.getRules();
    }

    public void create(Accident accident) {
        this.accidentMem.create(accident);
    }

    public void update(Accident accident) {
        this.accidentMem.update(accident);
    }

    public Accident findById(int id) {
        return this.accidentMem.findById(id);
    }

    public void addRulesToAccident(Accident accident, String[] rIds) {
        for (String id : rIds) {
            accident.getRules().add(accidentMem.findRuleById(Integer.parseInt(id)));
        }
    }
}
