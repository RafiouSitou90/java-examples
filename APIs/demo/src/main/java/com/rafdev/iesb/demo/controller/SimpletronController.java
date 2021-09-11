package com.rafdev.iesb.demo.controller;

import com.rafdev.iesb.demo.dto.SimpletronDTO;
import com.rafdev.iesb.demo.service.SimpletronService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/simpletrons")
public class SimpletronController {

    private final SimpletronService simpletronService;

    public SimpletronController() {
        this.simpletronService = new SimpletronService();
    }

    @GetMapping("")
    public List<SimpletronDTO> getSimpletrons() {
        return simpletronService.getSimpletrons();
    }
}
