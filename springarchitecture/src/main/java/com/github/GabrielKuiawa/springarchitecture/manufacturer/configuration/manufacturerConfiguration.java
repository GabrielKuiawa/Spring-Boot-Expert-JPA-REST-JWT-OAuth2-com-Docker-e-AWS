package com.github.GabrielKuiawa.springarchitecture.manufacturer.configuration;

import com.github.GabrielKuiawa.springarchitecture.manufacturer.Engine;
import com.github.GabrielKuiawa.springarchitecture.manufacturer.EngineType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class manufacturerConfiguration {

    @Bean
    public Engine engine() {
        var engine = new Engine();
        engine.setHorsepower(120);
        engine.setCylinders(4);
        engine.setModel("XPTO-0");
        engine.setEngineDisplacement(2.0);
        engine.setEngineType(EngineType.ASPIRATED);
        return engine;
    }
}
