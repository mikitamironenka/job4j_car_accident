package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernateTemplate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccidentService {

    private final AccidentHibernateTemplate accidentRepository;

    public AccidentService(AccidentHibernateTemplate accidentRepository) {
        this.accidentRepository = accidentRepository;
    }

    public List<Accident> getAccidents() {
        List<Accident> res = new ArrayList<>();
        this.accidentRepository.getAll();
        return res;
    }

    public List<AccidentType> getTypes() {
        List<AccidentType> res = new ArrayList<>();
        this.accidentRepository.getTypes();
        return res;
    }

    public List<Rule> getRules() {
        List<Rule> res = new ArrayList<>();
        this.accidentRepository.getRules();
        return res;
    }

    public void create(Accident accident) {
        this.accidentRepository.save(accident);
    }

    public void update(Accident accident) {
        this.accidentRepository.update(accident);
    }

    public Accident findById(int id) {
        return this.accidentRepository.findAccidentById(id);
    }

    public void addRulesToAccident(Accident accident, String[] rIds) {
        for (String id : rIds) {
            accident.getRules()
                .add(accidentRepository
                    .getRuleById(Integer.parseInt(id)));
        }
    }
}
