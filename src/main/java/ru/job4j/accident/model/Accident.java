package ru.job4j.accident.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Table(name = "accident")
public class Accident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String text;
    private String address;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private AccidentType type;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    private List<Rule> rules = new ArrayList<>();

    public Accident() {

    }

    public Accident(String name, String text, String address, AccidentType type) {
        this.name = name;
        this.text = text;
        this.address = address;
        this.type = type;
    }

    public void addRule(Rule... rule) {
        rules.addAll(Arrays.asList(rule));
    }
}
