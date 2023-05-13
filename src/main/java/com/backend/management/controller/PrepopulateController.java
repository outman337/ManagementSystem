package com.backend.management.controller;

import com.backend.management.service.PrepopulateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrepopulateController {

    private final PrepopulateService prepopulateService;

    @Autowired
    public PrepopulateController(PrepopulateService prepopulateService) {
        this.prepopulateService = prepopulateService;
    }

    @PostMapping ("prepopulate")
    public void prepopulate() {
        prepopulateService.populateDatabase();
    }
}
