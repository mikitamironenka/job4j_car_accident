package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernateTemplate;
import java.util.List;

@Service
public class AccidentService {

    private AccidentHibernateTemplate accidentTemplate;

    public AccidentService(AccidentHibernateTemplate accidentTemplate) {
        this.accidentTemplate = accidentTemplate;
    }

    public List<Accident> getAccidents() {
        return this.accidentTemplate.getAll();
    }

    public List<AccidentType> getTypes() {
        return this.accidentTemplate.getTypes();
    }

    public List<Rule> getRules() {
        return this.accidentTemplate.getRules();
    }

    public void create(Accident accident) {
        this.accidentTemplate.save(accident);
    }

    public void update(Accident accident) {
        this.accidentTemplate.update(accident);
    }

    public Accident findById(int id) {
        return this.accidentTemplate.findAccidentById(id);
    }

    public void addRulesToAccident(Accident accident, String[] rIds) {
        for (String id : rIds) {
            accident.getRules().add(accidentTemplate.getRuleById(Integer.parseInt(id)));
        }
    }
}
