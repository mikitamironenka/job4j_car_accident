package ru.job4j.accident.model;

import lombok.Data;

@Data
public class AccidentType {
        
    private String name;
    private int id;


    public static AccidentType of(int id, String name) {
        AccidentType type = new AccidentType();
        type.id = id;
        type.name = name;
        return type;
    }

}
