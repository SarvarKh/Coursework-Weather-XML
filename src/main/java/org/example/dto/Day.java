package org.example.dto;

public class Day {
    private String name;
    private Temperature temperature;
    private Humidity humidity;
    private WindSpeed windSpeed;

    public Day(String name) {
        this.name = name;
        this.temperature = new Temperature();
        this.humidity = new Humidity();
        this.windSpeed = new WindSpeed();
    }

    public String getName() {
        return this.name;
    }

    public Temperature getTemperature() {
        return this.temperature;
    }

    public Humidity getHumidity() {
        return this.humidity;
    }

    public WindSpeed getWindSpeed() {
        return this.windSpeed;
    }
}
