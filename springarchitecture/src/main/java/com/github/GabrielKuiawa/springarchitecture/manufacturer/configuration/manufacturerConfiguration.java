package com.github.GabrielKuiawa.springarchitecture.manufacturer.configuration;

import com.github.GabrielKuiawa.springarchitecture.manufacturer.Engine;
import com.github.GabrielKuiawa.springarchitecture.manufacturer.EngineType;
import com.github.GabrielKuiawa.springarchitecture.manufacturer.api.Aspirated;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class manufacturerConfiguration {

    @Bean(name = "engineAspirated")
    @Aspirated
    public Engine engineAspirated() {
        var engine = new Engine();
        engine.setHorsepower(120);
        engine.setCylinders(4);
        engine.setModel("XPTO-0");
        engine.setEngineDisplacement(2.0);
        engine.setEngineType(EngineType.ASPIRATED);
        return engine;
    }

    @Primary
    @Bean(name = "engineEletric")
    public Engine engineEletric() {
        var engine = new Engine();
        engine.setHorsepower(110);
        engine.setCylinders(3);
        engine.setModel("TH-10");
        engine.setEngineDisplacement(1.4);
        engine.setEngineType(EngineType.ELECTRIC);
        return engine;
    }

    @Bean(name = "engineTurbo")
    public Engine engineTurbo() {
        var engine = new Engine();
        engine.setHorsepower(180);
        engine.setCylinders(4);
        engine.setModel("XPTO-01");
        engine.setEngineDisplacement(1.5);
        engine.setEngineType(EngineType.TURBO);
        return engine;
    }

}
