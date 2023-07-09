package org.example.dto;

import java.util.ArrayList;
import java.util.List;

public class City {
    private String name;
    private List<Day> days;

    public City(String name) {
        this.name = name;
        this.days = new ArrayList<>();
    }

    public void addDay(Day day) {
        this.days.add(day);
    }

    public List<Day> getDays() {
        return this.days;
    }
}
