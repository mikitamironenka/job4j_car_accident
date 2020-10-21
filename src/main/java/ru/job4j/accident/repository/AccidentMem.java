package ru.job4j.accident.repository;

import lombok.Data;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;


import java.util.HashMap;
import java.util.List;

@Data
@Repository
public class AccidentMem {

    private static AccidentMem ACCIDENT_MEM = new AccidentMem();

    private HashMap<Integer, Accident> accidents = new HashMap<Integer, Accident>();
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

    public void edit(Accident accident) {
        accidents.get(accident.getId()).setName(accident.getName());
        accidents.get(accident.getId()).setText(accident.getText());
        accidents.get(accident.getId()).setAddress(accident.getAddress());
        accidents.get(accident.getId()).setType(accident.getType());
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }
}
