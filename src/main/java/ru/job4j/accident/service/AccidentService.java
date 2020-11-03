package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.HashMap;

@Service
public class AccidentService {

    private AccidentMem accidentMem;

    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    public HashMap<Integer, Accident> getAccidents() {
        return this.accidentMem.getAccidents();
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
}
