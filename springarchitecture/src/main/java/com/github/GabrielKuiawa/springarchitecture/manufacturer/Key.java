package com.github.GabrielKuiawa.springarchitecture.manufacturer;

public class Key {
    private Manufacturer manufacturer;
    private String type;

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
