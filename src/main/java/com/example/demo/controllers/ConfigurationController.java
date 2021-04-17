package com.example.demo.controllers;

import com.example.demo.config.biz.GlobalConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigurationController {

    @Autowired
    private GlobalConfiguration globalConfiguration;

    @GetMapping(value = "/config")
    public GlobalConfiguration getGlobalConfiguration() {
        return globalConfiguration;
    }

}
