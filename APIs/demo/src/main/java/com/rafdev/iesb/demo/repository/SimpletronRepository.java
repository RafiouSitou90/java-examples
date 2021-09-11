package com.rafdev.iesb.demo.repository;

import com.rafdev.iesb.demo.entity.SimpletronEntity;

import java.util.ArrayList;
import java.util.List;

public class SimpletronRepository {

    private List<SimpletronEntity> listSimpletron;

    public SimpletronRepository() {
        this.listSimpletron = new ArrayList<>();
    }

    public List<SimpletronEntity> findAll() {
        SimpletronEntity simpletron = new SimpletronEntity();

        simpletron.setName("Computer name");
        simpletron.setDescription("Computer Description");
        simpletron.setBrand("Computer Brand");
        simpletron.setName("Computer name");

        List<String> instructions = new ArrayList<>();
        instructions.add("ADD");
        instructions.add("READ");
        instructions.add("STORE");
        instructions.add("WRITE");
        instructions.add("SUB");
        simpletron.setInstructions(instructions);

        listSimpletron.add(simpletron);

        return listSimpletron;
    }

    public void save(SimpletronEntity simpletron) {
    }

    public void update(SimpletronEntity simpletron) {
    }

    public void delete(long id) {
    }
}
