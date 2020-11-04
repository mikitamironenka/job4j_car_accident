package ru.job4j.accident.repository;

import lombok.Data;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
@Repository
public class AccidentMem {

    private static AccidentMem ACCIDENT_MEM = new AccidentMem();

    private HashMap<Integer, Accident> accidents = new HashMap<>();
    private List<AccidentType> types = new ArrayList<>();
    private HashMap<Integer, Rule> rules = new HashMap<>();

    private static int incId = 0;

    private AccidentMem() {
        Accident ac1 = new Accident(incId(), "Авария", "алоыд дфлоалфы влфоа ", "Советская 50");
        Accident ac2 =new Accident(incId(), "ДТП", "алоыд дфлоалфы влфоа ", "Кирова 10");
        Accident ac3 = new Accident(incId(), "Наезд", "алоыд дфлоалфы влфоа ", "Плеханова 15");
        Accident ac4 = new Accident(incId(), "Столкновение", "алоыд дфлоалфы влфоа ", "Пушкина 1");
        Accident ac5 = new Accident(incId(), "Авария", "алоыд дфлоалфы влфоа ", "Ленинский пр-кт 56");

        accidents.put(ac1.getId(), ac1);
        accidents.put(ac2.getId(), ac2);
        accidents.put(ac3.getId(), ac3);
        accidents.put(ac4.getId(), ac4);
        accidents.put(ac5.getId(), ac5);

        types.add(AccidentType.of(1, "Две машины"));
        types.add(AccidentType.of(2, "Машина и человек"));
        types.add(AccidentType.of(3, "Машина и велосипед"));

        Rule rule1 = Rule.of(1, "Статья. 1");
        Rule rule2 = Rule.of(2, "Статья. 2");
        Rule rule3 = Rule.of(3, "Статья. 3");
        rules.put(rule1.getId(), rule1);
        rules.put(rule2.getId(), rule2);
        rules.put(rule3.getId(), rule3);
    }

    private static int incId() {
        return incId++;
    }

    public static AccidentMem instOf() {
        return ACCIDENT_MEM;
    }

    public void create(Accident accident) {
        accident.setId(incId());
        accidents.put(accident.getId(), accident);
    }

    public void update(Accident accident) {
        findById(accident.getId()).setName(accident.getName());
        findById(accident.getId()).setText(accident.getText());
        findById(accident.getId()).setAddress(accident.getAddress());
        findById(accident.getId()).setType(accident.getType());
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    public Rule findRuleById(int ruleId) {
        return rules.get(ruleId);
    }

    public void addRulesToAccident(Accident accident, String[] iDs) {
        for (String id : iDs) {
            accident.getRules().add(rules.get(id));
        }
    }
}
