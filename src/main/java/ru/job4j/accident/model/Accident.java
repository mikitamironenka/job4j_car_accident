package ru.job4j.accident.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Accident {

    private int id;
    private String name;
    private String text;
    private String address;

    public Accident(String name, String text, String address) {
        this.name = name;
        this.text = text;
        this.address = address;
    }

}
