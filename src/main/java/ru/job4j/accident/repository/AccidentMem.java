package ru.job4j.accident.repository;

import lombok.Data;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;

@Data
@Repository
public class AccidentMem {

    private HashMap<Integer, Accident> accidents = init();

    private HashMap<Integer, Accident> init() {
        HashMap<Integer, Accident> map = new HashMap<Integer, Accident>();
        map.put(1, new Accident(1, "Авария", "алоыд дфлоалфы влфоа ", "Советская 50"));
        map.put(2, new Accident(2, "ДТП", "алоыд дфлоалфы влфоа ", "Кирова 10"));
        map.put(3, new Accident(3, "Наезд", "алоыд дфлоалфы влфоа ", "Плеханова 15"));
        map.put(4, new Accident(4, "Столкновение", "алоыд дфлоалфы влфоа ", "Пушкина 1"));
        map.put(5, new Accident(5, "Авария", "алоыд дфлоалфы влфоа ", "Ленинский пр-кт 56"));
        return map;
    }


}
