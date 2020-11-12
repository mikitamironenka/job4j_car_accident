package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.exception.AccidentException;
import ru.job4j.accident.model.*;
import ru.job4j.accident.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccidentService {

    private final AccidentRepository accidentRepository;
    private final AccidentTypeRepository accidentTypeRepository;
    private final RuleRepository ruleRepository;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    public AccidentService(AccidentRepository accidentRepository, AccidentTypeRepository accidentTypeRepository, RuleRepository ruleRepository, UserRepository users, AuthorityRepository authorities) {
        this.accidentRepository = accidentRepository;
        this.accidentTypeRepository = accidentTypeRepository;
        this.ruleRepository = ruleRepository;
        this.userRepository = users;
        this.authorityRepository = authorities;
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
            try {
                accident.getRules()
                    .add(ruleRepository
                        .findById(Integer.parseInt(id))
                        .orElseThrow(() -> new AccidentException("Не найдено статьи с id ")));
            } catch (AccidentException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public Authority findByAuthority(String authority) {
        return authorityRepository.findByAuthority(authority);
    }
}
