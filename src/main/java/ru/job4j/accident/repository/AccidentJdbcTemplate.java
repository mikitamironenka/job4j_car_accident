package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.exception.AccidentException;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;
import java.util.Optional;

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
                try {
                    int id = rs.getInt("type_id");
                    accident.setType(getTypeById(id)
                        .orElseThrow(() -> new AccidentException("Не найдено типа с id " + id)));
                } catch (AccidentException e) {
                    e.printStackTrace();
                }
                return accident;
            });
    }

    public Optional<AccidentType> getTypeById(int id) {

        AccidentType result = jdbc.queryForObject("select id, name from types where id = ?",
            (resultSet, rowNum) -> AccidentType.of(
                resultSet.getInt("id"),
                resultSet.getString("name")), id);
        return Optional.ofNullable(result);
    }

    public Optional<Rule> getRuleById(int id) {
        Optional<Rule> result = Optional.ofNullable(jdbc.queryForObject("select id, name from rules where id = ?",
            (resultSet, rowNum) -> Rule.of(
                resultSet.getInt("id"),
                resultSet.getString("name")) ,
            id));
        return result;
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

    public Optional<Accident> findAccidentById(int id) {
        Optional<Accident> result = Optional.ofNullable(jdbc.queryForObject("select id, name, text, address, type_id from accident where id = ?", (rs, row) -> {
            Accident accident = new Accident();
            accident.setId(rs.getInt("id"));
            accident.setName(rs.getString("name"));
            accident.setText(rs.getString("text"));
            accident.setAddress(rs.getString("address"));
            try {
                accident.setType(getTypeById(rs.getInt("type_id"))
                    .orElseThrow(() -> new AccidentException("Не найдено типа с id - " + id)));
            } catch (AccidentException e) {
                e.printStackTrace();
            }
            return accident;
        }, id));
        return result;
    }
}
