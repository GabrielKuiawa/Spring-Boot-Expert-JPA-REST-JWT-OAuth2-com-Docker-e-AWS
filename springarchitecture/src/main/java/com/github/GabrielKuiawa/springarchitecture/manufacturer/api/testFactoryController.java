package com.github.GabrielKuiawa.springarchitecture.manufacturer.api;

import com.github.GabrielKuiawa.springarchitecture.manufacturer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
public class testFactoryController {

    @Autowired
    @Aspirated
    private Engine engine;

    @PostMapping
    public CarStatus startCar(@RequestBody Key key) {
        var car = new HondaHRV(engine);
        return car.ignition(key);
    }
}
