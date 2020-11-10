package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.exception.AccidentException;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentTypeRepository;
import ru.job4j.accident.repository.RuleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccidentService {

    private final AccidentRepository accidentRepository;
    private final AccidentTypeRepository accidentTypeRepository;
    private final RuleRepository ruleRepository;

    public AccidentService(AccidentRepository accidentRepository, AccidentTypeRepository accidentTypeRepository, RuleRepository ruleRepository) {
        this.accidentRepository = accidentRepository;
        this.accidentTypeRepository = accidentTypeRepository;
        this.ruleRepository = ruleRepository;
    }

    public List<Accident> getAccidents() {
        List<Accident> res = new ArrayList<>();
        this.accidentRepository.findAll().forEach(res :: add);
        return res;
    }

    public List<AccidentType> getTypes() {
        List<AccidentType> res = new ArrayList<>();
        this.accidentTypeRepository.findAll().forEach(res :: add);
        return res;
    }

    public List<Rule> getRules() {
        List<Rule> res = new ArrayList<>();
        this.ruleRepository.findAll().forEach(res :: add);
        return res;
    }

    public void create(Accident accident) {
        this.accidentRepository.save(accident);
    }

    public void update(Accident accident) {
        Optional<Accident> accdnt = accidentRepository.findById(accident.getId());
        if (accdnt.isPresent()){
            Accident accidentTemp = accdnt.get();
            if (accident.getName() != null)
                accidentTemp.setName(accident.getName());
            if (accident.getText() != null)
                accidentTemp.setText(accident.getText());
            if (accident.getAddress() != null)
                accidentTemp.setAddress(accident.getAddress());
            this.accidentRepository.save(accidentTemp);
        }
    }

    public Accident findById(int id) {
        Accident accident = new Accident();
        try {
            accident = this.accidentRepository.findById(id).orElseThrow(() -> new AccidentException("Не найдено инцидента с id - " + id));
        } catch (AccidentException e) {
            e.printStackTrace();
        }
        return accident;
    }

    public void addRulesToAccident(Accident accident, String[] rIds) {
        for (String id : rIds) {
            accident.getRules()
                .add(ruleRepository
                    .findById(Integer.parseInt(id))
                    .get());
        }
    }
}
