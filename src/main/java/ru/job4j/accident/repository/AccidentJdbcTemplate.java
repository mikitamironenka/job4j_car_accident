package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Accident save(Accident accident) {
        jdbc.update("insert into accident (name, text, address, type_id) values (?, ?, ?, ?)",
            accident.getName(),
            accident.getText(),
            accident.getAddress(),
            accident.getType().getId()
            );
        return accident;
    }

    public List<Accident> getAll() {
        return jdbc.query("select id, name, text, address, type_id from accident",
            (rs, row) -> {
                Accident accident = new Accident();
                accident.setId(rs.getInt("id"));
                accident.setName(rs.getString("name"));
                accident.setText(rs.getString("text"));
                accident.setAddress(rs.getString("address"));
                accident.setType(getTypeById(rs.getInt("type_id")));
                return accident;
            });
    }

    public AccidentType getTypeById(int id) {
        List<AccidentType> result =  jdbc.query("select id, name from types where id = ?",
            (resultSet, rowNum) -> AccidentType.of(
            resultSet.getInt("id"),
            resultSet.getString("name")) ,id);
        if (result.size() != 1) {
            return null;
        } else {
            return result.get(0);
        }
             //делаем запрос query, который возвращает лист и берем первое значение, чтобы не получить IncorrectResultSizeDataAccessException
                            //если в таблице не будет значений. Тк. queryForObject ожидает одно и только одно значение
    }

    public Rule getRuleById(int id) {
        List<Rule> result = jdbc.query("select id, name from rules where id = ?",
            (resultSet, rowNum) -> Rule.of(
                resultSet.getInt("id"),
                resultSet.getString("name")) ,
            id);
        if (result.size() != 1) {
            return null;  // поменять на лист
        } else {
            return result.get(0);
        }

    }

    public List<AccidentType> getTypes() {
        return jdbc.query("select id, name from types",
            (rs, row) -> {
                AccidentType accidentType = new AccidentType();
                accidentType.setId(rs.getInt("id"));
                accidentType.setName(rs.getString("name"));
                return accidentType;
            });
    }

    public List<Rule> getRules() {
        return jdbc.query("select id, name from rules",
            (rs, row) -> {
                Rule rule = new Rule();
                rule.setId(rs.getInt("id"));
                rule.setName(rs.getString("name"));
                return rule;
            });
    }

    public void update(Accident accident) {
        jdbc.update( "update accident set name = ? where id = ?",
                accident.getName(),
                accident.getId());
    }

    public Accident findAccidentById(int id) {
        List<Accident> result = jdbc.query("select id, name, text, address, type_id from accident where id = ?", (rs, row) -> {
            Accident accident = new Accident();
            accident.setId(rs.getInt("id"));
            accident.setName(rs.getString("name"));
            accident.setText(rs.getString("text"));
            accident.setAddress(rs.getString("address"));
            accident.setType(getTypeById(rs.getInt("type_id")));
            return accident;
        }, id);
        if (result.size() != 1) {
            return null;
        } else {
            return result.get(0);
        }
    }
}
