package com.github.GabrielKuiawa.springarchitecture.manufacturer;

import java.awt.*;

public class Car {
    private String model;
    private Color color;
    private Engine engine;
    private Manufacturer manufacturer;

    public Car(Engine engine) {
        this.engine = engine;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public CarStatus ignition(Key key) {
        System.out.println(key.getManufacturer());
        System.out.println(this.manufacturer);
        if (key.getManufacturer() != this.manufacturer) {
            return new CarStatus("It is not possible to start the car with this key.");
        }
        return new CarStatus("Car running. Running with engine " + this.engine);
    }
}
