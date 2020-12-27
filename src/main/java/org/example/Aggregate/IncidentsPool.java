package org.example.Aggregate;

import org.example.value_object.Incident;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;

public class IncidentsPool {
    private final List<String> symptom;

    public IncidentsPool() {

        this.symptom = Arrays.asList("\"Животное не дышит\"", "\"Животное чешется\"", "\"Состояние животного: вялость\"",
                "\"Состояние животного: агрессия\"", "\"Животное отказывается от еды\"", "\"У животного рвота\"");
    }

    public ArrayDeque<Incident> getListIncidents() {
        ArrayDeque<Incident> resultList = new ArrayDeque<>();
        if (symptom != null) {
            int index = 1;
            for (String symptom : symptom) {
                resultList.add(new Incident(index, symptom));
                index++;
            }
        }
        return resultList;
    }
}
