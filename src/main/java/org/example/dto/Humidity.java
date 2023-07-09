package org.example.dto;

public class Humidity implements MinMaxParams {
    private double min;
    private double max;

    public Humidity() {
        this.min = 0.0;
        this.max = 0.0;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMin() {
        return this.min;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMax() {
        return this.max;
    }
}
