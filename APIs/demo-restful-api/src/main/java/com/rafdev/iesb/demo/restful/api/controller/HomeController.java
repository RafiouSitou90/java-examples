package com.rafdev.iesb.demo.restful.api.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HomeController {

    @RequestMapping("")
    public ResponseEntity<Object> homepage() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", "Welcome to IESB DEMO RESTful API Example");

        return new ResponseEntity<>(jsonObject.toMap(), HttpStatus.OK);
    }
}
