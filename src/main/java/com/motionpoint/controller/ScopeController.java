package com.motionpoint.controller;

import com.motionpoint.entity.ScopeConfig;
import com.motionpoint.service.ScopeConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Matthew on 1/7/2017.
 */
@RestController
public class ScopeController {

    @Autowired
    private ScopeConfigService scopeConfigService;

    @RequestMapping(value = "/scope/create/", method = RequestMethod.POST)
    boolean createScope(@RequestBody ScopeConfig jsonScope){
        return scopeConfigService.createScopeConfig(jsonScope);
    }
    @RequestMapping(value = "/scope/delete/{configId}", method = RequestMethod.POST)
    public boolean deleteScopeConfig(@PathVariable long configId) {
        return scopeConfigService.deleteScopeConfig(configId);
    }

    @RequestMapping(value = "/scope/list/", method = RequestMethod.GET)
    public List<ScopeConfig> displayScopeConfigs() {
        return scopeConfigService.getAllScopeConfigs();
    }
}
