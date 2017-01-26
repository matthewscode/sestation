package com.motionpoint.controller;

import com.motionpoint.dao.ScopeAnalysisDao;
import com.motionpoint.dao.ScopeConfigDao;
import com.motionpoint.entity.Machine;
import com.motionpoint.entity.ScopeAnalysis;
import com.motionpoint.entity.ScopeConfig;
import com.motionpoint.service.ScopeConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Matthew on 1/7/2017.
 */
@RestController
@SessionAttributes(value = "machine", types = {Machine.class})
public class ScopeController {

    @Autowired
    private ScopeConfigService scopeConfigService;
    @Autowired
    private ScopeAnalysisDao scopeAnalysisDao;

    @RequestMapping(value = "/scope/create/", method = RequestMethod.POST)
    public boolean createScope(@RequestBody ScopeConfig jsonScope){
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

    @RequestMapping(value = "/scope/get/{configId}/", method = RequestMethod.GET)
    public ScopeConfig getConfig(@PathVariable long configId) {
        return scopeConfigService.getScopeConfig(configId);
    }

    @RequestMapping(value = "/machine/current/", method = RequestMethod.POST)
    public Machine setCurrentMachine(@RequestBody Machine jsonMmachine) {
        return null;
    }
//    @RequestMapping(value = "/machine/current/", method = RequestMethod.GET)
//    public Machine getCurrentMachine(HttpSession session) {
//        return (Machine)session.getAttribute("machine");
//    }

//    ANALYSIS
    @RequestMapping(value = "/analysis/create/", method = RequestMethod.POST)
    public boolean createAnalysis(@RequestBody ScopeAnalysis json) {
        scopeAnalysisDao.save(json);
        return true;
    }
    @RequestMapping(value = "/analysis/list/all/", method = RequestMethod.GET)
    public List<ScopeAnalysis> displayAllAnalysis() {
        return (List<ScopeAnalysis>)scopeAnalysisDao.findAll();
    }
    @RequestMapping(value = "/analysis/get/{analysisId}/", method = RequestMethod.GET)
    public ScopeAnalysis getAnalysis(@PathVariable long analysisId) {
        return scopeAnalysisDao.findOne(analysisId);
    }


}
