package com.models.lib.libraryofmodels.services.models.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.models.lib.libraryofmodels.services.models.model.Model;

@RestController
public class ModelController {


    @GetMapping("/api/v0/models")
    public Model greeting() {
        return Model.builder().name("someName").build();
    }
    
}