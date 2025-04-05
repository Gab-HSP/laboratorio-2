package com.example.aluguel.config;

import com.example.aluguel.model.Automovel;
import com.example.aluguel.repository.AutomovelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
    
    @Bean
    CommandLineRunner initDatabase(AutomovelRepository automovelRepository) {
        return args -> {
            automovelRepository.save(createAutomovel("1", 2022, "Toyota", "Corolla", "ABC-1234"));
            automovelRepository.save(createAutomovel("2", 2021, "Honda", "Civic", "DEF-5678"));
            automovelRepository.save(createAutomovel("3", 2020, "Volkswagen", "Golf", "GHI-9012"));
        };
    }
    
    private Automovel createAutomovel(String matricula, int ano, String marca, String modelo, String placa) {
        Automovel automovel = new Automovel();
        automovel.setMatricula(matricula);
        automovel.setAno(ano);
        automovel.setMarca(marca);
        automovel.setModelo(modelo);
        automovel.setPlaca(placa);
        return automovel;
    }
}