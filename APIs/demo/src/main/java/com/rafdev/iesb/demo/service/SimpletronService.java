package com.rafdev.iesb.demo.service;

import com.rafdev.iesb.demo.dto.SimpletronDTO;
import com.rafdev.iesb.demo.entity.SimpletronEntity;
import com.rafdev.iesb.demo.repository.SimpletronRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SimpletronService {

    private final SimpletronRepository simpletronRepository;

    public SimpletronService() {
        this.simpletronRepository = new SimpletronRepository();
    }

    public List<SimpletronDTO> getSimpletrons() {
        List<SimpletronDTO> results = new ArrayList<>();
        List<SimpletronEntity> simpletrons = simpletronRepository.findAll();

        for (SimpletronEntity simpletron: simpletrons) {
            SimpletronDTO simpletronDTO = new SimpletronDTO();

            simpletronDTO.setName(simpletron.getName());
            simpletronDTO.setBrand(simpletron.getBrand());
            simpletronDTO.setDescription(simpletron.getDescription());
            simpletronDTO.setInstructions(simpletron.getInstructions());

            results.add(simpletronDTO);
        }

        return results;
    }
}
