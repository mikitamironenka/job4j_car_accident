package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.exception.AccidentException;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import java.util.List;

@Service
public class AccidentService {

//    private AccidentMem accidentMem;
    private AccidentJdbcTemplate accidentJdbcTemplate;

//    public AccidentService(AccidentMem accidentMem) {
//        this.accidentMem = accidentMem;
//    }

    public AccidentService(AccidentJdbcTemplate accidentJdbcTemplate) {
        this.accidentJdbcTemplate = accidentJdbcTemplate;
    }

    public List<Accident> getAccidents() {
        return this.accidentJdbcTemplate.getAll();
    }

    public List<AccidentType> getTypes() {
        return this.accidentJdbcTemplate.getTypes();
    }

    public List<Rule> getRules() {
        return this.accidentJdbcTemplate.getRules();
    }

    public void create(Accident accident) {
        this.accidentJdbcTemplate.save(accident);
    }

    public void update(Accident accident) {
        this.accidentJdbcTemplate.update(accident);
    }

    public Accident findById(int id) throws AccidentException {
        return this.accidentJdbcTemplate.findAccidentById(id)
            .orElseThrow(() -> new AccidentException("Проишествие не найдено"));
    }

    public void addRulesToAccident(Accident accident, String[] rIds) throws Exception {
        for (String id : rIds) {
            accident.getRules()
                .add(accidentJdbcTemplate
                    .getRuleById(Integer.parseInt(id))
                    .orElseThrow(() -> new AccidentException("Статья не найдена")));
        }
    }
}
